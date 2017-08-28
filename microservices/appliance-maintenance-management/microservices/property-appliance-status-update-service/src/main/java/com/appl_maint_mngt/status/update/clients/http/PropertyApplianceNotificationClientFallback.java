package com.appl_maint_mngt.status.update.clients.http;

import org.springframework.stereotype.Component;

import com.appl_maint_mngt.status.notification.models.StatusNotification;

@Component
public class PropertyApplianceNotificationClientFallback implements IPropertyApplianceNotificationClient {

	@Override
	public String notify(StatusNotification notification) {
		return null;
	}

}
