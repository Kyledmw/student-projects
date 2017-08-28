package com.appl_maint_mngt.status.update.clients.http;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.appl_maint_mngt.status.notification.controllers.rest.IStatusNotificationRestApi;

@FeignClient(url="{services.property-appliance-status-notification-service.url}", name="property-appliance-status-notification-service", fallback=PropertyApplianceNotificationClientFallback.class)
public interface IPropertyApplianceNotificationClient extends IStatusNotificationRestApi {

}
