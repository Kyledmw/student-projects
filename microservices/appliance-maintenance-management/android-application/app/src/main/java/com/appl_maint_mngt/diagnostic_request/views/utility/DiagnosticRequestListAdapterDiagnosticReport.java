package com.appl_maint_mngt.diagnostic_request.views.utility;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.common.views.ACommonListAdapter;
import com.appl_maint_mngt.diagnostic_report.models.interfaces.IDiagnosticReportReadable;
import com.appl_maint_mngt.diagnostic_report.repositories.interfaces.IDiagnosticReportReadableRepository;
import com.appl_maint_mngt.diagnostic_request.models.interfaces.IDiagnosticRequestReadable;

import java.util.List;

/**
 * Created by Kyle on 10/04/2017.
 */

public class DiagnosticRequestListAdapterDiagnosticReport extends ACommonListAdapter<IDiagnosticRequestReadable> {

    private IDiagnosticReportReadableRepository diagnosticReportRepository;

    public DiagnosticRequestListAdapterDiagnosticReport(@NonNull Context context, @NonNull List<IDiagnosticRequestReadable> objects) {
        super(context, objects);
        diagnosticReportRepository = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getDiagnosticReportRepository();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IDiagnosticRequestReadable diagnosticRequest = getItem(position);

        convertView = prepareConvertView(convertView, parent, R.layout.listitem_diagnostic_request_diagnostic_report);

        TextView diagnosticReportTV = (TextView) convertView.findViewById(R.id.listitem_diagnostic_request_diag_report);
        TextView statusTV = (TextView) convertView.findViewById(R.id.listitem_diagnostic_request_diag_rep_status);

        IDiagnosticReportReadable diagnosticReport = diagnosticReportRepository.get(diagnosticRequest.getDiagnosticReportId());

        if(diagnosticReport != null) {
            diagnosticReportTV.setText(diagnosticReport.getTitle());
        }

        statusTV.setText(diagnosticRequest.getResponseStatus().toString());

        return convertView;
    }
}
