package com.appl_maint_mngt.diagnostic_report.controllers.interfaces;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.diagnostic_report.models.web.interfaces.IDiagnosticReportForm;

import java.util.List;

/**
 * Created by Kyle on 09/04/2017.
 */

public interface IDiagnosticReportController {
    void generateDiagnosticReport(IDiagnosticReportForm form, IErrorCallback errorCallback);

    void getForPropertyAppliance(Long propertyApplianceId, IErrorCallback errorCallback);

    void getForPropertyAppliances(List<Long> propertyApplianceIds, IErrorCallback errorCallback);

    void getForDiagnosticReportIds(List<Long> ids, IErrorCallback errorCallback);

    void getForDiagnosticReportId(Long id, IErrorCallback errorCallback);
}
