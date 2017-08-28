package com.appl_maint_mngt.status.notification.controllers.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appl_maint_mngt.status.notification.models.StatusNotification;

public interface IStatusNotificationRestApi {

	@RequestMapping(value="/notification", method=RequestMethod.POST)
	@ResponseBody String notify(@RequestBody StatusNotification notification);
}
