package com.appl_maint_mngt.maintenance.schedule.pending;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableJpaRepositories
@EnableFeignClients
public class PendingMaintenanceSchedulingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PendingMaintenanceSchedulingServiceApplication.class, args);
	}
}
