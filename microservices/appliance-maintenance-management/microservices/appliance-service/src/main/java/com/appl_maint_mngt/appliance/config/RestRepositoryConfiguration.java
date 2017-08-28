package com.appl_maint_mngt.appliance.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.appl_maint_mngt.appliance.models.Appliance;

@Configuration
public class RestRepositoryConfiguration extends RepositoryRestConfigurerAdapter {

	
    @Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Appliance.class);
    }
}
