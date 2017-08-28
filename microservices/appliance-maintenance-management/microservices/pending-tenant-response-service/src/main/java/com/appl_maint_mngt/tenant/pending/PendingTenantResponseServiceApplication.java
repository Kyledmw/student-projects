package com.appl_maint_mngt.tenant.pending;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class PendingTenantResponseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PendingTenantResponseServiceApplication.class, args);
	}
}
