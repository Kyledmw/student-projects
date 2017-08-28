package com.appl_maint_mngt.property_appliance.views.interfaces;

import android.view.View;
import android.widget.AdapterView;

import com.appl_maint_mngt.appliance.models.interfaces.IApplianceReadable;
import com.appl_maint_mngt.appliance_status.models.interfaces.IApplianceStatusReadable;
import com.appl_maint_mngt.diagnostic_report.models.interfaces.IDiagnosticReportReadable;
import com.appl_maint_mngt.property_appliance.models.interfaces.IPropertyApplianceReadable;

import java.util.List;

/**
 * Created by Kyle on 08/04/2017.
 */

public interface IPropertyApplianceView {
    void displaySetupTagButton();
    void hideSetupTagButton();
    void setSetupTagOnClickListener(View.OnClickListener listener);

    void displayGenerateDiagnosticReportButton();
    void hideGenerateDiagnosticReportButton();
    void setGenerateDiagnosticReportOnClickListener(View.OnClickListener listener);

    void update(IPropertyApplianceReadable propertyAppliance);
    void updateStatus(IApplianceStatusReadable applianceStatus);
    void updateAppliance(IApplianceReadable appliance);
    void updateDiagnosticReports(List<IDiagnosticReportReadable> diagnosticReports);

    void setStatusHistoryOnItemClickListener(AdapterView.OnItemClickListener listener);
    void setDiagnosticReportsOnItemClickListener(AdapterView.OnItemClickListener listener);

    void displayDiagnosticReports();
    void hideDiagnosticReports();

    void resetView();
}
