package com.services.account.http.utils;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public interface IRoutingUtils {
    void prepareContext(RoutingContext routingContext, JsonObject content, int statusCode);
}
