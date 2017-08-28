package com.appl_maint_mngt.appliance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableMongoRepositories
public class ApplianceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplianceServiceApplication.class, args);
	}
}
