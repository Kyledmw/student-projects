package com.appl_maint_mngt.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MaintenanceScheduleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaintenanceScheduleServiceApplication.class, args);
	}
}
