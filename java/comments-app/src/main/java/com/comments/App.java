package com.comments;

import io.vertx.core.Vertx;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new HttpSockJSVerticle());
    }
}
