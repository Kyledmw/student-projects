package com.appl_maint_mngt.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@EnableConfigServer
@ComponentScan
public class InfastructureConfigServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfastructureConfigServiceApplication.class, args);
	}
}
