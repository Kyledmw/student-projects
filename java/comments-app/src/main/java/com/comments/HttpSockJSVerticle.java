package com.comments;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;

public class HttpSockJSVerticle extends AbstractVerticle {
	
	private List<Comment> comments;
	
	public HttpSockJSVerticle() {
		super();
		comments = new ArrayList<Comment>();
		comments.add(new Comment("Joe Bloggs", "Sample message"));
	}

	@Override
	public void start(Future<Void> startFuture) {
		Router router = Router.router(vertx);
		
		BridgeOptions opts = new BridgeOptions()
	      .addInboundPermitted(new PermittedOptions().setAddress("comments.create_comment"))
	      .addInboundPermitted(new PermittedOptions().setAddress("comments.get_comments"))
	      .addOutboundPermitted(new PermittedOptions().setAddress("comments.all_comments"));
		
		SockJSHandler ebHandler = SockJSHandler.create(vertx).bridge(opts);

	    router.route("/eventbus/*").handler(ebHandler);

	    router.route().handler(StaticHandler.create());
	    
		HttpServer server = vertx.createHttpServer();
		server.requestHandler(router::accept).listen(8080);
	    
		EventBus eb = vertx.eventBus();
		
		eb.consumer("comments.create_comment").handler(message -> {
			JSONObject mappedComment = new JSONObject(message.body());
			JSONObject comment = mappedComment.getJSONObject("map");
			String author = comment.getString("author");
			String text = comment.getString("message");
			comments.add(new Comment(author, text));
			eb.publish("comments.all_comments", new JSONArray(comments).toString());
		});
		
		eb.consumer("comments.get_comments").handler(message -> {
			message.reply(new JSONArray(comments).toString());
		});
	}
}
