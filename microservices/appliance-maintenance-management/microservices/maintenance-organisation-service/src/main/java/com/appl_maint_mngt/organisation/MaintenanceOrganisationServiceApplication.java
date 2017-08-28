package com.appl_maint_mngt.organisation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MaintenanceOrganisationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaintenanceOrganisationServiceApplication.class, args);
	}
}
