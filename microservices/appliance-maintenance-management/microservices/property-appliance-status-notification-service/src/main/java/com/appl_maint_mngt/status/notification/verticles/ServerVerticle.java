package com.appl_maint_mngt.status.notification.verticles;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.appl_maint_mngt.status.notification.web.constants.IEntryPoints;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;

@Component
public class ServerVerticle extends AbstractVerticle {
	
	@Value("${vertx.server.port}")
	private int SERVER_PORT;

	@Override
	public void start() throws Exception {
	    Router router = Router.router(vertx);
	    BridgeOptions opts = new BridgeOptions()
	  	      .addOutboundPermitted(new PermittedOptions().setAddress(IEntryPoints.EB_NOTIFICATION_ADDRESS));
	    
	    SockJSHandler ebHandler = SockJSHandler.create(vertx).bridge(opts);
	    router.route(IEntryPoints.EB_ENDPOINT).handler(ebHandler);
	    
	    router.route(IEntryPoints.REST_NOTIF_ENDPOINT + "*").handler(BodyHandler.create());
	    router.post(IEntryPoints.REST_NOTIF_ENDPOINT).handler(this::notify);
	    
	    vertx.createHttpServer().requestHandler(router::accept).listen(SERVER_PORT);
	}
	
	private void notify(RoutingContext routingContext) {
		JsonObject requestBody = routingContext.getBodyAsJson();
		routingContext.response().setStatusCode(HttpStatus.OK.value());
		routingContext.response().end();
	    EventBus eb = vertx.eventBus();
	    eb.publish(IEntryPoints.EB_NOTIFICATION_ADDRESS, requestBody);
	}
	
}
