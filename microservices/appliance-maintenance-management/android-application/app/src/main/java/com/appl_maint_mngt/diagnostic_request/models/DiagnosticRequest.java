package com.appl_maint_mngt.diagnostic_request.models;

import com.appl_maint_mngt.diagnostic_request.models.constants.ResponseStatus;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kyle on 21/03/2017.
 */

public class DiagnosticRequest extends ADiagnosticRequest {

    private Long id;

    private Long diagnosticReportId;

    private Long maintenanceOrganisationId;

    private ResponseStatus responseStatus;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getDiagnosticReportId() {
        return diagnosticReportId;
    }

    @Override
    public void setDiagnosticReportId(Long diagnosticReportId) {
        this.diagnosticReportId = diagnosticReportId;
    }

    @Override
    public Long getMaintenanceOrganisationId() {
        return maintenanceOrganisationId;
    }

    @Override
    public void setMaintenanceOrganisationId(Long maintenanceOrganisationId) {
        this.maintenanceOrganisationId = maintenanceOrganisationId;
    }

    @Override
    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    @Override
    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
}
