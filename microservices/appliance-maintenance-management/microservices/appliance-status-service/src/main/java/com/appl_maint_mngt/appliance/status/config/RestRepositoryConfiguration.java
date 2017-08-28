package com.appl_maint_mngt.appliance.status.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.appl_maint_mngt.appliance.status.models.ApplianceStatus;

@Configuration
public class RestRepositoryConfiguration extends RepositoryRestConfigurerAdapter {

	
    @Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(ApplianceStatus.class);
    }
}
