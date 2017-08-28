package com.services.license.http.utils;

import com.services.license.constants.IHTTPConstants;

import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class RoutingUtils implements IRoutingUtils {
	
	public void prepareContext(RoutingContext routingContext, JsonObject content, int statusCode) {
		routingContext.response().setStatusCode(statusCode);
		routingContext.response().putHeader(HttpHeaders.CONTENT_TYPE, IHTTPConstants.APPL_JSON);
		routingContext.response().end(content.encode());
	}
}
