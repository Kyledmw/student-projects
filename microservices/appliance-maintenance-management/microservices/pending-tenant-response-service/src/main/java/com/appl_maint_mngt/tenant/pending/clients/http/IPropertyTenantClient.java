package com.appl_maint_mngt.tenant.pending.clients.http;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.appl_maint_mngt.property.tenant.rest.controllers.IPropertyTenantRestApi;

@FeignClient(url="http://localhost:8410", name="property-tenant-service", fallback=PropertyTenantClientFallback.class)
public interface IPropertyTenantClient extends IPropertyTenantRestApi {

}
