package com.services.web_portal;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;

public class WebServiceVerticle extends AbstractVerticle {

    private final static int PORT_NUMBER = 8085;

    @Override
    public void start(Future<Void> startFuture) {
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.route().handler(StaticHandler.create());

        vertx.createHttpServer().requestHandler(router::accept).listen(PORT_NUMBER);
    }
}
