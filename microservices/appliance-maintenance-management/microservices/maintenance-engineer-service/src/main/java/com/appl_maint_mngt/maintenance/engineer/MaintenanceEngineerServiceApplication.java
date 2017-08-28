package com.appl_maint_mngt.maintenance.engineer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableJpaRepositories
public class MaintenanceEngineerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaintenanceEngineerServiceApplication.class, args);
	}
}
