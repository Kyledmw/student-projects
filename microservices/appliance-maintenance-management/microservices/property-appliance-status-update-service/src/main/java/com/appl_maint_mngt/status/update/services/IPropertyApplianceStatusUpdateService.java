package com.appl_maint_mngt.status.update.services;

import com.appl_maint_mngt.status.update.models.web.IUpdatePayload;

public interface IPropertyApplianceStatusUpdateService {

	boolean updatePropertyApplianceStatus(IUpdatePayload payload);
}
