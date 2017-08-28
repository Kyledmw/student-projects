package com.appl_maint_mngt.tenant.pending.services;

import com.appl_maint_mngt.tenant.pending.models.web.IPendingResponsePayload;

public interface IPendingTenantResponseService {

	boolean postResponseToPropertyTenant(IPendingResponsePayload payload);
}
