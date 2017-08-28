package com.appl_maint_mngt.property_appliance.views;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.appliance.models.interfaces.IApplianceReadable;
import com.appl_maint_mngt.appliance_status.models.interfaces.IApplianceStatusReadable;
import com.appl_maint_mngt.diagnostic_report.models.interfaces.IDiagnosticReportReadable;
import com.appl_maint_mngt.diagnostic_report.views.utility.DiagnosticReportListAdapter;
import com.appl_maint_mngt.property_appliance.models.interfaces.IPropertyApplianceReadable;
import com.appl_maint_mngt.property_appliance.views.interfaces.IPropertyApplianceView;
import com.appl_maint_mngt.status_history.models.interfaces.IStatusHistoryReadable;
import com.appl_maint_mngt.status_history.views.utility.StatusHistoryListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Kyle on 08/04/2017.
 */

public class PropertyApplianceView implements IPropertyApplianceView {

    private Button setupTagBtn;
    private Button genDiagnosticReportBtn;

    private TextView nameTV;
    private TextView ageTV;
    private TextView statusTV;

    private TextView productNoTV;
    private TextView applianceNameTV;
    private TextView applianceTypeTV;
    private TextView brandTV;

    private ListView statusHistoryLV;
    private StatusHistoryListAdapter statusHistoryListAdapter;

    private ListView diagnosticReportLV;
    private DiagnosticReportListAdapter diagnosticReportListAdapter;

    private CardView diagnosticReportCV;

    public PropertyApplianceView(Activity parent) {
        setupTagBtn = (Button) parent.findViewById(R.id.diagnosticreport_button_relatedactivity);
        genDiagnosticReportBtn = (Button) parent.findViewById(R.id.propertyappliance_button_gendiagrep);

        nameTV = (TextView) parent.findViewById(R.id.diagnosticreport_textview_title);
        ageTV = (TextView) parent.findViewById(R.id.propertyappliance_textview_age_value);
        statusTV = (TextView) parent.findViewById(R.id.propertyappliance_textview_status_value);

        productNoTV = (TextView) parent.findViewById(R.id.propertyappliance_textview_appl_productno_value);
        applianceNameTV = (TextView) parent.findViewById(R.id.propertyappliance_textview_appl_name_value);
        applianceTypeTV = (TextView) parent.findViewById(R.id.propertyappliance_textview_appl_type_value);
        brandTV = (TextView) parent.findViewById(R.id.propertyappliance_textview_appl_brand_value);

        statusHistoryLV = (ListView) parent.findViewById(R.id.propertyappliance_listview_stathistory_list);
        statusHistoryListAdapter = new StatusHistoryListAdapter(parent, new ArrayList<IStatusHistoryReadable>());
        statusHistoryLV.setAdapter(statusHistoryListAdapter);

        diagnosticReportLV = (ListView) parent.findViewById(R.id.propertyappliance_diagreport_listview);
        diagnosticReportListAdapter = new DiagnosticReportListAdapter(parent, new ArrayList<IDiagnosticReportReadable>());
        diagnosticReportLV.setAdapter(diagnosticReportListAdapter);

        diagnosticReportCV = (CardView) parent.findViewById(R.id.propertyappliance_cardview_diagnostic_reports);
    }

    @Override
    public void displaySetupTagButton() {
        setupTagBtn.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideSetupTagButton() {
        setupTagBtn.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setSetupTagOnClickListener(View.OnClickListener listener) {
        setupTagBtn.setOnClickListener(listener);
    }

    @Override
    public void displayGenerateDiagnosticReportButton() {
        genDiagnosticReportBtn.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideGenerateDiagnosticReportButton() {
        genDiagnosticReportBtn.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setGenerateDiagnosticReportOnClickListener(View.OnClickListener listener) {
        genDiagnosticReportBtn.setOnClickListener(listener);
    }

    @Override
    public void update(IPropertyApplianceReadable propertyAppliance) {
        nameTV.setText(propertyAppliance.getName());
        ageTV.setText(String.format(Locale.ENGLISH, "%d", propertyAppliance.getAge()));
        statusHistoryListAdapter.clear();
        statusHistoryListAdapter.addAll(propertyAppliance.getStatusHistory());
        statusHistoryListAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateStatus(IApplianceStatusReadable applianceStatus) {
        statusTV.setText(applianceStatus.getType().toString());
    }

    @Override
    public void updateAppliance(IApplianceReadable appliance) {
        productNoTV.setText(appliance.getProductNo());
        applianceNameTV.setText(appliance.getName());
        applianceTypeTV.setText(appliance.getType().toString());
        brandTV.setText(appliance.getBrand());
    }

    @Override
    public void updateDiagnosticReports(List<IDiagnosticReportReadable> diagnosticReports) {
        diagnosticReportListAdapter.clear();
        diagnosticReportListAdapter.addAll(diagnosticReports);
        diagnosticReportListAdapter.notifyDataSetChanged();
    }

    @Override
    public void setStatusHistoryOnItemClickListener(AdapterView.OnItemClickListener listener) {
        statusHistoryLV.setOnItemClickListener(listener);
    }

    @Override
    public void setDiagnosticReportsOnItemClickListener(AdapterView.OnItemClickListener listener) {
        diagnosticReportLV.setOnItemClickListener(listener);
    }

    @Override
    public void displayDiagnosticReports() {
        diagnosticReportCV.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideDiagnosticReports() {
        diagnosticReportCV.setVisibility(View.INVISIBLE);
    }

    @Override
    public void resetView() {
        hideGenerateDiagnosticReportButton();
        hideSetupTagButton();
        hideDiagnosticReports();
    }
}
