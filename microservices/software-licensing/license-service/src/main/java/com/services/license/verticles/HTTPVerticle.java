package com.services.license.verticles;

import com.services.license.constants.IAuthConstants;
import com.services.license.constants.IDBConstants;
import com.services.license.constants.IErrorMessages;
import com.services.license.constants.IEventBusAddresses;
import com.services.license.constants.IModelFieldConstants;
import com.services.license.constants.IRequestConstants;
import com.services.license.constants.IValidationConstants;
import com.services.license.http.utils.IRoutingUtils;
import com.services.license.http.utils.RoutingUtils;
import com.services.license.validation.GetLicenseRequestValidator;
import com.services.license.validation.PutLicenseRequestValidator;
import com.services.license.validation.interfaces.IRequestValidator;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class HTTPVerticle extends AbstractVerticle {

    private final static int PORT_NUMBER = 8083;

    private final static String LICENSE_ROUTE = "/rest/license";

    private final static String LICENSES_RESPONSE_KEY = "licenses";

    private MongoClient _mongo;

    private EventBus _eb;

    private IRoutingUtils _routingUtils;


    @Override
    public void start() {
        _eb = vertx.eventBus();
        _routingUtils = new RoutingUtils();

        _mongo = MongoClient.createShared(vertx, new JsonObject().put(IDBConstants.DB_NAME_KEY, IDBConstants.LICENSES_DB_NAME));

        Router router = Router.router(vertx);

        router.route(LICENSE_ROUTE).handler(BodyHandler.create());

        router.get(LICENSE_ROUTE).handler(this::getLicense);
        router.put(LICENSE_ROUTE).handler(this::putLicense);

        vertx.createHttpServer().requestHandler(router::accept).listen(PORT_NUMBER);
    }

    private void getLicense(RoutingContext routingContext) {
        IRequestValidator validator = new GetLicenseRequestValidator();
        validator.validate(routingContext);

        if(!validator.isValid()) {
            invalidRequestResponse(routingContext, validator);
            return;
        }

        String token = routingContext.request().getParam(IAuthConstants.TOKEN_KEY);

        _eb.send(IEventBusAddresses.TOKEN_EB_ADDRESS_AUTH, token, reply -> {
            if(reply.succeeded()) {
                JsonObject message = (JsonObject) reply.result().body();
                if(message.getBoolean(IValidationConstants.SUCCESS_KEY)) {
                    JsonObject query = new JsonObject().put(IModelFieldConstants.USER_ID_KEY, message.getString(IModelFieldConstants.USER_ID_KEY));
                    _mongo.find(IDBConstants.LICENSES_COLLECTION_NAME, query, lookup -> {
                        if(lookup.failed() || lookup.result() == null) {
                            JsonArray errMessages = new JsonArray().add(IErrorMessages.NO_LICENSES);
                            JsonObject errors = new JsonObject().put(IValidationConstants.SUCCESS_KEY, false).put(IValidationConstants.MESSAGES_KEY, errMessages);
                            _routingUtils.prepareContext(routingContext, errors, IRequestConstants.ERROR_STATUS_CODE);
                            return;
                        }

                        JsonArray arr = new JsonArray();
                        lookup.result().forEach((v) -> arr.add(v));

                        JsonObject success = new JsonObject().put(IValidationConstants.SUCCESS_KEY, true).put(LICENSES_RESPONSE_KEY, arr);
                        _routingUtils.prepareContext(routingContext, success, IRequestConstants.SUCCESS_STATUS_CODE);

                    });
                } else {
                    _routingUtils.prepareContext(routingContext, message, IRequestConstants.ERROR_STATUS_CODE);
                }
            } else {
                cannotAuthResponse(routingContext);
            }
        });

    }

    private void putLicense(RoutingContext routingContext) {
        IRequestValidator validator = new PutLicenseRequestValidator();
        validator.validate(routingContext);

        if(!validator.isValid()) {
            invalidRequestResponse(routingContext, validator);
            return;
        }

        JsonObject requestBody = routingContext.getBodyAsJson();

        String token = requestBody.getString(IAuthConstants.TOKEN_KEY);

        _eb.send(IEventBusAddresses.TOKEN_EB_ADDRESS_AUTH, token, reply -> {
            if(reply.succeeded()) {
                JsonObject message = (JsonObject) reply.result().body();
                if(message.getBoolean(IValidationConstants.SUCCESS_KEY)) {
                    JsonObject license = new JsonObject();
                    license.put(IModelFieldConstants.USER_ID_KEY, message.getString(IModelFieldConstants.USER_ID_KEY));
                    license.put(IModelFieldConstants.SOFTWARE_ID_KEY, requestBody.getString(IRequestConstants.SOFTWARE_ID_KEY));
                    _mongo.insert(IDBConstants.LICENSES_COLLECTION_NAME, license, res -> {
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
