package com.services.profile.verticles;

import com.services.profile.constants.IAuthConstants;
import com.services.profile.constants.IDBConstants;
import com.services.profile.constants.IErrorMessages;
import com.services.profile.constants.IEventBusAddress;
import com.services.profile.constants.IModelFieldConstants;
import com.services.profile.constants.IRequestConstants;
import com.services.profile.constants.IValidationConstants;
import com.services.profile.utils.IRoutingUtils;
import com.services.profile.utils.RoutingUtils;
import com.services.profile.validation.GetProfileRequestValidator;
import com.services.profile.validation.SaveProfileRequestValidator;
import com.services.profile.validation.interfaces.IRequestValidator;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;

public class HTTPVerticle extends AbstractVerticle {

    private final static int PORT_NUMBER = 8081;

    private final static String PROFILE_ROUTE = "/rest/profile";

    private MongoClient _mongo;

    private IRoutingUtils _routingUtils;

    private EventBus _eb;


    @Override
    public void start() {
        _eb = vertx.eventBus();
        _routingUtils = new RoutingUtils();

        _mongo = MongoClient.createShared(vertx, new JsonObject().put(IDBConstants.DB_NAME_KEY, IDBConstants.PROFILES_DB_NAME));

        Router router = Router.router(vertx);

        router.route().handler(CorsHandler.create("*")
                .allowedMethod(HttpMethod.POST)
                .allowedMethod(HttpMethod.GET)
                .allowedHeader(IRequestConstants.X_PINGARUNER_HEADER)
                .allowedHeader(HttpHeaders.CONTENT_TYPE.toString()));

        router.route(PROFILE_ROUTE + "*").handler(BodyHandler.create());
        router.post(PROFILE_ROUTE).handler(this::saveProfile);
        router.get(PROFILE_ROUTE).handler(this::getProfile);

        vertx.createHttpServer().requestHandler(router::accept).listen(PORT_NUMBER);
    }

    private void saveProfile(RoutingContext routingContext) {
        IRequestValidator validator = new SaveProfileRequestValidator();
        validator.validate(routingContext);

        if(!validator.isValid()) {
            invalidRequestResponse(routingContext, validator);
            return;
        }

        JsonObject requestBody = routingContext.getBodyAsJson();

        String token = requestBody.getString(IAuthConstants.TOKEN_KEY);
        _eb.send(IEventBusAddress.TOKEN_EB_ADDRESS_AUTH, token, reply -> {
            if(reply.succeeded()) {
                JsonObject message = (JsonObject) reply.result().body();
                if(message.getBoolean(IValidationConstants.SUCCESS_KEY)) {
                    JsonObject profile = new JsonObject();
                    profile.put(IModelFieldConstants.USER_ID_KEY, message.getString(IModelFieldConstants.USER_ID_KEY));
                    profile.put(IModelFieldConstants.COMPANY_NAME_KEY, requestBody.getString(IRequestConstants.COMPANY_NAME));
                    profile.put(IModelFieldConstants.PROFILE_NAME_KEY, requestBody.getString(IRequestConstants.PROFILE_NAME));

                    String id = requestBody.getString(IRequestConstants.ID_FIELD);
                    if(id != null) {
                        profile.put(IModelFieldConstants.ID_KEY, id);
                    }

                    _mongo.save(IDBConstants.PROFILES_COLLECTION_NAME, profile, res -> {
                        if(res.succeeded()) {
                            JsonObject success = new JsonObject().put(IValidationConstants.SUCCESS_KEY, true);
                            _routingUtils.prepareContext(routingContext, success, IRequestConstants.SUCCESS_STATUS_CODE);
                        } else {
                            cannotAuthResponse(routingContext);
                        }
                    });
                } else {
                    _routingUtils.prepareContext(routingContext, message, IRequestConstants.ERROR_STATUS_CODE);
                }
            } else {
                cannotAuthResponse(routingContext);
            }
        });
    }

    private void getProfile(RoutingContext routingContext) {
        IRequestValidator validator = new GetProfileRequestValidator();
        validator.validate(routingContext);

        if (!validator.isValid()) {
            invalidRequestResponse(routingContext, validator);
            return;
        }

        String token = routingContext.request().getParam(IAuthConstants.TOKEN_KEY);

        _eb.send(IEventBusAddress.TOKEN_EB_ADDRESS_AUTH, token, reply -> {
            if (reply.succeeded()) {
                JsonObject message = (JsonObject) reply.result().body();
                if (message.getBoolean(IValidationConstants.SUCCESS_KEY)) {
                    JsonObject query = new JsonObject().put(IModelFieldConstants.USER_ID_KEY, message.getString(IModelFieldConstants.USER_ID_KEY));
                    _mongo.findOne(IDBConstants.PROFILES_COLLECTION_NAME, query, new JsonObject(), lookup -> {
                        if(lookup.failed() || lookup.result() == null) {
                            JsonArray errMessages = new JsonArray().add(IErrorMessages.NO_PROFILE);
                            JsonObject errors = new JsonObject().put(IValidationConstants.SUCCESS_KEY, false).put(IValidationConstants.MESSAGES_KEY, errMessages);
                            _routingUtils.prepareContext(routingContext, errors, IRequestConstants.ERROR_STATUS_CODE);
                            return;
                        }

                        JsonObject profile = lookup.result();
                        _routingUtils.prepareContext(routingContext, profile, IRequestConstants.SUCCESS_STATUS_CODE);
                    });
                } else {
                    _routingUtils.prepareContext(routingContext, message, IRequestConstants.ERROR_STATUS_CODE);
                }
            } else {
                cannotAuthResponse(routingContext);
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
        JsonArray errMessages = new JsonArray().add(IErrorMessages.CANNOT_AUTH);
        JsonObject errors = new JsonObject().put(IValidationConstants.SUCCESS_KEY, false).put(IValidationConstants.MESSAGES_KEY, errMessages);
        _routingUtils.prepareContext(routingContext, errors, IRequestConstants.ERROR_STATUS_CODE);
    }
}
