package com.appl_maint_mngt.report.repair.pending.services;

import com.appl_maint_mngt.report.repair.pending.models.PendingRepairReport;

public interface IPendingRepairReportService {
	boolean doesPendingRepairReportExist(Long id);
	PendingRepairReport accept(Long id);
	PendingRepairReport decline(Long id);
	PendingRepairReport create(PendingRepairReport pendingRepairReport);
	boolean hasRepairReportBeenAcceptedForDiagRep(Long diagRepId);
	PendingRepairReport get(Long id);
}
