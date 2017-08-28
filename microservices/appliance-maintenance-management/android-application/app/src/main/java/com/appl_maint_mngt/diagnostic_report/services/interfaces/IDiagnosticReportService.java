package com.appl_maint_mngt.diagnostic_report.services.interfaces;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.diagnostic_report.models.web.interfaces.IDiagnosticReportForm;

import java.util.List;

/**
 * Created by Kyle on 09/04/2017.
 */

public interface IDiagnosticReportService {

    void post(IDiagnosticReportForm diagRep, IErrorCallback callback);

    void getForPropertyApplianceId(Long propertyApplianceId, IErrorCallback errorCallback);
    void findByPropertyApplianceIdsIn(List<Long> propertyApplianceIds, IErrorCallback errorCallback);

    void findByIdsIn(List<Long> ids, IErrorCallback errorCallback);

    void get(Long id, IErrorCallback errorCallback);

}
