package com.appl_maint_mngt.report.repair;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableJpaRepositories
public class RepairReportServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RepairReportServiceApplication.class, args);
	}
}
