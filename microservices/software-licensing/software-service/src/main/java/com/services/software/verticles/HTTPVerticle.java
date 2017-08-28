package com.services.software.verticles;

import com.services.software.constants.IDBConstants;
import com.services.software.constants.IErrorMessages;
import com.services.software.constants.IModelFieldConstants;
import com.services.software.constants.IRequestConstants;
import com.services.software.constants.IValidationConstants;
import com.services.software.utils.IRoutingUtils;
import com.services.software.utils.RoutingUtils;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class HTTPVerticle extends AbstractVerticle {

    private final static int PORT_NUMBER = 8082;

    private final static String SOFTWARE_ROUTE = "/rest/software";

    private MongoClient _mongo;

    private IRoutingUtils _routingUtils;

    private final static String SOFTWARE_RESPONSE_KEY = "software";


    @Override
    public void start() {
        _routingUtils = new RoutingUtils();

        _mongo = MongoClient.createShared(vertx, new JsonObject().put(IDBConstants.DB_NAME_KEY, IDBConstants.SOFTWARE_DB_NAME));

        Router router = Router.router(vertx);

        router.route(SOFTWARE_ROUTE + "*").handler(BodyHandler.create());
        router.get(SOFTWARE_ROUTE).handler(this::getSoftware);
        router.get(SOFTWARE_ROUTE + "/:" + IRequestConstants.SOFTWARE_ID).handler(this::softwareForID);

        vertx.createHttpServer().requestHandler(router::accept).listen(PORT_NUMBER);
    }

    private void getSoftware(RoutingContext routingContext) {
        _mongo.find(IDBConstants.SOFTWARE_COLLECTION_NAME, new JsonObject(), lookup -> {
            if(lookup.failed() || lookup.result() == null) {
                dbErrorResponse(routingContext);
                return;
            }

            JsonArray arr = new JsonArray();
            lookup.result().forEach((v) -> arr.add(v));

            JsonObject success = new JsonObject().put(IValidationConstants.SUCCESS_KEY, true).put(SOFTWARE_RESPONSE_KEY, arr);
            _routingUtils.prepareContext(routingContext, success, IRequestConstants.SUCCESS_STATUS_CODE);
        });
    }

    private void softwareForID(RoutingContext routingContext) {
        JsonObject query = new JsonObject().put(IModelFieldConstants.ID_KEY, routingContext.request().getParam(IRequestConstants.SOFTWARE_ID));
        _mongo.findOne(IDBConstants.SOFTWARE_COLLECTION_NAME, query, new JsonObject(), lookup -> {
            if(lookup.failed() || lookup.result() == null) {
                dbErrorResponse(routingContext);
                return;
            }

            JsonObject success = new JsonObject().put(IValidationConstants.SUCCESS_KEY, true).put(SOFTWARE_RESPONSE_KEY, lookup.result());
            _routingUtils.prepareContext(routingContext, success, IRequestConstants.SUCCESS_STATUS_CODE);
        });
    }

    private void dbErrorResponse(RoutingContext routingContext) {
        JsonArray errMessages = new JsonArray().add(IErrorMessages.DB_ERROR);
        JsonObject errors = new JsonObject().put(IValidationConstants.SUCCESS_KEY, false).put(IValidationConstants.MESSAGES_KEY, errMessages);
        _routingUtils.prepareContext(routingContext, errors, IRequestConstants.ERROR_STATUS_CODE);
    }
}
