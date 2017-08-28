package com.appl_maint_mngt.status.update;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients		
public class PropertyApplianceStatusUpdateServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropertyApplianceStatusUpdateServiceApplication.class, args);
	}
}
