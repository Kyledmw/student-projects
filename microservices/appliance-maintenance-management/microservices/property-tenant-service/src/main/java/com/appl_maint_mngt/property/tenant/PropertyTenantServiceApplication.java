package com.appl_maint_mngt.property.tenant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableJpaRepositories
public class PropertyTenantServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropertyTenantServiceApplication.class, args);
	}
}
