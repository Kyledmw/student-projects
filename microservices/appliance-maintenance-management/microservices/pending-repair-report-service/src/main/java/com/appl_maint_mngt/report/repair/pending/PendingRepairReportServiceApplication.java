package com.appl_maint_mngt.report.repair.pending;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableJpaRepositories
public class PendingRepairReportServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PendingRepairReportServiceApplication.class, args);
	}
}
