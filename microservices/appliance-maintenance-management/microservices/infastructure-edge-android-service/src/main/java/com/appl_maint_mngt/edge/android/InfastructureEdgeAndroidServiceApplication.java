package com.appl_maint_mngt.edge.android;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class InfastructureEdgeAndroidServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfastructureEdgeAndroidServiceApplication.class, args);
	}
}
