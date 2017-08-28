package com.services.web_portal;

import io.vertx.core.Vertx;

public class App {
	public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new WebServiceVerticle());
	}
}
