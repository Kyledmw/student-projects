package com.appl_maint_mngt.status.notification;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.appl_maint_mngt.status.notification.verticles.ServerVerticle;

import io.vertx.core.Vertx;

@SpringBootApplication
@EnableEurekaClient
public class PropertyApplianceStatusNotificationServiceApplication {

	@Autowired
	private ServerVerticle serverVerticle;

	public static void main(String[] args) {
		SpringApplication.run(PropertyApplianceStatusNotificationServiceApplication.class, args);
	}

	@PostConstruct
	public void deployVerticle() {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(serverVerticle);
	}
}
