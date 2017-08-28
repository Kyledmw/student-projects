package com.appl_maint_mngt.status.update.clients.http;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.appl_maint_mgt.property_appliance.controllers.rest.IPropertyApplianceRestApi;

@FeignClient(url="http://localhost:8405", name="property-appliance-service", fallback=PropertyApplianceClientFallback.class)
public interface IPropertyApplianceClient extends IPropertyApplianceRestApi {

}
