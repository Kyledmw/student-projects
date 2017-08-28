package com.services.account.verticles;

import java.util.ArrayList;

import com.services.account.constants.IAuthConstants;
import com.services.account.constants.IDBConstants;
import com.services.account.constants.IEventBusAddresses;
import com.services.account.constants.IModelFieldConstants;
import com.services.account.constants.IRequestConstants;
import com.services.account.constants.IValidationConstants;
import com.services.account.http.utils.IRoutingUtils;
import com.services.account.http.utils.RoutingUtils;
import com.services.account.strings.IErrorMessages;
import com.services.account.validation.AccountRequestValidator;
import com.services.account.validation.AuthRequestValidator;
import com.services.account.validation.interfaces.IRequestValidator;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.mongo.MongoAuth;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;

public class HTTPVerticle extends AbstractVerticle {

    private final static int PORT_NUMBER = 8080;

    private final static String ACCOUNT_ROUTE = "/rest/account";
    private final static String AUTH_ROUTE = "/rest/auth";

    private MongoClient _mongo;

    private MongoAuth _auth;

    private IRoutingUtils _routingUtils;

    private EventBus _eb;

    @Override
    public void start() {
        _eb = vertx.eventBus();
        _routingUtils = new RoutingUtils();

        _mongo = MongoClient.createShared(vertx, new JsonObject().put(IDBConstants.DB_NAME_KEY, IDBConstants.USERS_DB_NAME));
        _auth = MongoAuth.create(_mongo, new JsonObject());
        _auth.setCollectionName(IDBConstants.USERS_COLLECTION_NAME);

        Router router = Router.router(vertx);

        router.route().handler(CorsHandler.create("*")
                .allowedMethod(HttpMethod.POST)
                .allowedMethod(HttpMethod.PUT)
                .allowedMethod(HttpMethod.DELETE)
                .allowedHeader(IRequestConstants.X_PINGARUNER_HEADER)
                .allowedHeader(HttpHeaders.CONTENT_TYPE.toString()));

        router.route(ACCOUNT_ROUTE + "*").handler(BodyHandler.create());
        router.post(ACCOUNT_ROUTE).handler(this::login);
        router.delete(ACCOUNT_ROUTE).handler(this::logout);
        router.put(ACCOUNT_ROUTE).handler(this::createAccount);

        router.route(AUTH_ROUTE + "*").handler(BodyHandler.create());
        router.post(AUTH_ROUTE).handler(this::auth);

        vertx.createHttpServer().requestHandler(router::accept).listen(PORT_NUMBER);
    }

    private void login(RoutingContext routingContext) {
        if (!accountRequestValidation(routingContext)) {
            return;
        }

        JsonObject requestData = routingContext.getBodyAsJson();
        JsonObject authInfo = new JsonObject();
        authInfo.put(IModelFieldConstants.USERNAME_KEY, requestData.getString(IRequestConstants.USERNAME_KEY));
        authInfo.put(IModelFieldConstants.PASSWORD_KEY, requestData.getString(IRequestConstants.PASSWORD_KEY));

        _auth.authenticate(authInfo, res -> {
            if (res.succeeded()) {
                JsonObject user = res.result().principal();
                JsonObject message = new JsonObject().put(IModelFieldConstants.USER_ID_KEY, user.getString(IModelFieldConstants.ID_KEY));
                generateTokenResponse(routingContext, message);
            } else {
                cannotAuthResponse(routingContext);
            }
        });
    }

    private void createAccount(RoutingContext routingContext) {
        if (!accountRequestValidation(routingContext)) {
            return;
        }
        JsonObject requestData = routingContext.getBodyAsJson();
        String username = requestData.getString(IRequestConstants.USERNAME_KEY);
        String password = requestData.getString(IRequestConstants.PASSWORD_KEY);

        JsonObject query = new JsonObject().put(IModelFieldConstants.USERNAME_KEY, username);

        _mongo.count(IDBConstants.USERS_COLLECTION_NAME, query, lookup -> {
            if (lookup.failed()) {
                cannotAuthResponse(routingContext);
            }

            if (lookup.result() == 0) {
                _auth.insertUser(username, password, new ArrayList<>(), new ArrayList<>(), res -> {
                    if (res.succeeded()) {
                        JsonObject message = new JsonObject().put(IModelFieldConstants.USER_ID_KEY, res.result());
                        generateTokenResponse(routingContext, message);
                    } else {
                        cannotAuthResponse(routingContext);
                    }
                });
            } else {
                JsonArray errMessages = new JsonArray().add(IErrorMessages.USER_EXISTS);
                JsonObject errors = new JsonObject().put(IValidationConstants.SUCCESS_KEY, false).put(IValidationConstants.MESSAGES_KEY, errMessages);
                _routingUtils.prepareContext(routingContext, errors, IRequestConstants.ERROR_STATUS_CODE);
            }
        });

    }

    private void auth(RoutingContext routingContext) {
        IRequestValidator validator = new AuthRequestValidator();
        validator.validate(routingContext);

        if (!validator.isValid()) {
            invalidRequestResponse(routingContext, validator);
            return;
        }

        String token = routingContext.getBodyAsJson().getString(IAuthConstants.TOKEN_KEY);

        _eb.send(IEventBusAddresses.TOKEN_EB_ADDRESS_AUTH, token, reply -> {
            if (reply.succeeded()) {
                JsonObject message = (JsonObject) reply.result().body();
                int statusCode = (message.getBoolean(IValidationConstants.SUCCESS_KEY)) ? IRequestConstants.SUCCESS_STATUS_CODE : IRequestConstants.ERROR_STATUS_CODE;
                message.remove(IModelFieldConstants.USER_ID_KEY);
                _routingUtils.prepareContext(routingContext, message, statusCode);
            } else {
                cannotAuthResponse(routingContext);
            }
        });

    }

    private void logout(RoutingContext routingContext) {
        routingContext.clearUser();
        JsonObject success = new JsonObject().put(IValidationConstants.SUCCESS_KEY, true);
        _routingUtils.prepareContext(routingContext, success, IRequestConstants.SUCCESS_STATUS_CODE);
    }

    private boolean accountRequestValidation(RoutingContext routingContext) {
        IRequestValidator validator = new AccountRequestValidator();
        validator.validate(routingContext);

        if (!validator.isValid()) {
            invalidRequestResponse(routingContext, validator);
        }
        return (validator.isValid());
    }

    private void generateTokenResponse(RoutingContext routingContext, JsonObject message) {
        _eb.send(IEventBusAddresses.TOKEN_EB_ADDRESS_GENERATE, message, reply -> {
            if (reply.succeeded()) {
                String token = ((JsonObject) reply.result().body()).getString(IAuthConstants.TOKEN_KEY);
                JsonObject success = new JsonObject().put(IValidationConstants.SUCCESS_KEY, true).put(IAuthConstants.TOKEN_KEY, token);
                _routingUtils.prepareContext(routingContext, success, IRequestConstants.SUCCESS_STATUS_CODE);
            }
        });
    }

    private void invalidRequestResponse(RoutingContext routingContext, IRequestValidator validator) {
        JsonArray errMessages = new JsonArray();
        validator.getErrorMessages().forEach((v) -> errMessages.add(v));
        JsonObject errors = new JsonObject().put(IValidationConstants.SUCCESS_KEY, false).put(IValidationConstants.MESSAGES_KEY, errMessages);
        _routingUtils.prepareContext(routingContext, errors, IRequestConstants.ERROR_STATUS_CODE);
    }

    private void cannotAuthResponse(RoutingContext routingContext) {
        JsonObject errors = new JsonObject().put(IValidationConstants.SUCCESS_KEY, false).put(IValidationConstants.MESSAGES_KEY, IErrorMessages.CANNOT_AUTH);
        _routingUtils.prepareContext(routingContext, errors, IRequestConstants.ERROR_STATUS_CODE);
    }
}
