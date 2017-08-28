package com.appl_maint_mngt.diagnostic_report.views.interfaces;

import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;

import com.appl_maint_mngt.diagnostic_report.models.interfaces.IDiagnosticReportReadable;
import com.appl_maint_mngt.diagnostic_request.models.interfaces.IDiagnosticRequestReadable;

import java.util.List;

/**
 * Created by Kyle on 09/04/2017.
 */

public interface IDiagnosticReportView {

    void update(IDiagnosticReportReadable diagnosticReport);

    void updateDiagnosticRequests(List<IDiagnosticRequestReadable> diagnosticRequests);

    void setOnDiagnosticRequestsItemClickListener(AdapterView.OnItemClickListener listener);

    void setViewRelatedActivityClickListener(View.OnClickListener listener);

    void setSendDiagnosticRequestsOnClickListener(View.OnClickListener listener);

    void setRelatedActivityBtnText(String string);
    void setRelatedActivityBtnText(int id);

    void displayViewRelatedActivityBtn();
    void hideViewRelatedActivityBtn();

    void hideDiagnosticRequests();
    void displayDiagnosticRequests();

    void resetView();
}
