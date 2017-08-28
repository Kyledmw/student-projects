package com.appl_maint_mngt.diagnostic_report.views;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.utility.TimestampFormatter;
import com.appl_maint_mngt.diagnostic_report.models.interfaces.IDiagnosticReportReadable;
import com.appl_maint_mngt.diagnostic_report.views.interfaces.IDiagnosticReportView;
import com.appl_maint_mngt.diagnostic_request.models.interfaces.IDiagnosticRequestReadable;
import com.appl_maint_mngt.diagnostic_request.views.utility.DiagnosticRequestListAdapterMaintenanceOrganisation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 09/04/2017.
 */

public class DiagnosticReportView implements IDiagnosticReportView {

    private TextView titleTV;

    private Button relatedActivityBtn;

    private TextView timeTV;
    private TextView descTV;

    private CardView diagnosticRequestCV;
    private Button sendDiagnosticRequestBtn;

    private ListView diagnosticRequestLV;
    private DiagnosticRequestListAdapterMaintenanceOrganisation listAdapter;

    public DiagnosticReportView(Activity parent) {
        titleTV = (TextView) parent.findViewById(R.id.diagnosticreport_textview_title);
        relatedActivityBtn = (Button) parent.findViewById(R.id.diagnosticreport_button_relatedactivity);
        timeTV = (TextView) parent.findViewById(R.id.diagnosticreport_textview_time);
        descTV = (TextView) parent.findViewById(R.id.diagnosticreport_textview_description);

        sendDiagnosticRequestBtn = (Button) parent.findViewById(R.id.diagnosticreport_diagnosticrequest_button_send_requests);

        diagnosticRequestCV = (CardView) parent.findViewById(R.id.diagnosticreport_cardview_diagnostic_requests);
        diagnosticRequestLV = (ListView) parent.findViewById(R.id.diagnosticreport_diagnosticrequest_listview);

        listAdapter = new DiagnosticRequestListAdapterMaintenanceOrganisation(parent, new ArrayList<IDiagnosticRequestReadable>());
        diagnosticRequestLV.setAdapter(listAdapter);

        resetView();
    }

    @Override
    public void update(IDiagnosticReportReadable diagnosticReport) {
        titleTV.setText(diagnosticReport.getTitle());
        timeTV.setText(new TimestampFormatter().format(diagnosticReport.getIssuedTime()));
        descTV.setText(diagnosticReport.getDescription());
    }

    @Override
    public void updateDiagnosticRequests(List<IDiagnosticRequestReadable> diagnosticRequests) {
        listAdapter.clear();
        listAdapter.addAll(diagnosticRequests);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void setOnDiagnosticRequestsItemClickListener(AdapterView.OnItemClickListener listener) {
        diagnosticRequestLV.setOnItemClickListener(listener);
    }

    @Override
    public void setViewRelatedActivityClickListener(View.OnClickListener listener) {
        relatedActivityBtn.setOnClickListener(listener);
    }

    @Override
    public void setSendDiagnosticRequestsOnClickListener(View.OnClickListener listener) {
        sendDiagnosticRequestBtn.setOnClickListener(listener);
    }

    @Override
    public void setRelatedActivityBtnText(String string) {
        relatedActivityBtn.setText(string);
    }

    @Override
    public void setRelatedActivityBtnText(int id) {
        relatedActivityBtn.setText(id);
    }

    @Override
    public void displayViewRelatedActivityBtn() {
        relatedActivityBtn.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideViewRelatedActivityBtn() {
        relatedActivityBtn.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideDiagnosticRequests() {
        diagnosticRequestCV.setVisibility(View.INVISIBLE);
    }

    @Override
    public void displayDiagnosticRequests() {
        diagnosticRequestCV.setVisibility(View.VISIBLE);
    }

    @Override
    public void resetView() {
        hideDiagnosticRequests();
        hideViewRelatedActivityBtn();
    }
}
