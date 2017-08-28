package com.appl_maint_mngt.status.update.services;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appl_maint_mgt.property_appliance.models.PropertyAppliance;
import com.appl_maint_mgt.property_appliance.models.StatusHistory;
import com.appl_maint_mngt.status.notification.models.StatusNotification;
import com.appl_maint_mngt.status.update.clients.http.IPropertyApplianceClient;
import com.appl_maint_mngt.status.update.clients.http.IPropertyApplianceNotificationClient;
import com.appl_maint_mngt.status.update.models.web.IUpdatePayload;

@Service
public class PropertyApplianceStatusUpdateService implements IPropertyApplianceStatusUpdateService {
	
	@Autowired
	private IPropertyApplianceClient pApplClient;
	
	@Autowired
	private IPropertyApplianceNotificationClient notifClient;

	@Override
	public boolean updatePropertyApplianceStatus(IUpdatePayload payload) {
		
		PropertyAppliance pAppl = pApplClient.get(payload.getPropertyApplianceId());
		if(pAppl == null) {
			return false;
		}
		
		StatusNotification notif = new StatusNotification();
		notif.setPropertyApplianceId(payload.getPropertyApplianceId());
		notif.setPreviousApplianceStatusId(pAppl.getStatusId());
		notif.setNewApplianceStatusId(payload.getNewApplianceStatusId());
		
		StatusHistory oldStat = new StatusHistory();
		oldStat.setLoggedTime(new Timestamp(System.currentTimeMillis()));
		oldStat.setStatusId(pAppl.getStatusId());
		pAppl.getStatusHistory().add(oldStat);
		pAppl.setStatusId(payload.getNewApplianceStatusId());
		
		pAppl = pApplClient.update(payload.getPropertyApplianceId(), pAppl);
		if(pAppl == null) {
			return false;
		}
		return true;
		//return notifClient.notify(notif) != null;
	}

}
