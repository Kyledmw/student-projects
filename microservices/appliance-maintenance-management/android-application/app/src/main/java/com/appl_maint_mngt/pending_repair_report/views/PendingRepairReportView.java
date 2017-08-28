package com.appl_maint_mngt.pending_repair_report.views;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.pending_repair_report.models.interfaces.IPendingRepairReportReadable;
import com.appl_maint_mngt.pending_repair_report.views.interfaces.IPendingRepairReportView;

import java.util.Locale;

/**
 * Created by Kyle on 11/04/2017.
 */

public class PendingRepairReportView implements IPendingRepairReportView {

    private TextView titleTV;

    private TextView statusTV;

    private TextView durationTV;
    private TextView onsiteTV;
    private TextView costTV;
    private TextView descriptionTV;

    private CardView repsonseCV;
    private Button acceptBtn;
    private Button declineBtn;

    public PendingRepairReportView(Activity parent) {
        titleTV = (TextView) parent.findViewById(R.id.pending_repair_report_textview_title);
        statusTV = (TextView) parent.findViewById(R.id.pending_repair_report_textview_value_status);
        durationTV = (TextView) parent.findViewById(R.id.dialog_pending_repair_report_textview_value_duration);
        onsiteTV = (TextView) parent.findViewById(R.id.dialog_pending_repair_report_textview_value_onsite);
        costTV = (TextView) parent.findViewById(R.id.pending_repair_report_textview_value_cost);
        descriptionTV = (TextView) parent.findViewById(R.id.pending_repair_report_textview_value_description);
        acceptBtn = (Button) parent.findViewById(R.id.pending_repair_report_button_accept);
        declineBtn = (Button) parent.findViewById(R.id.pending_repair_report_button_decline);
        repsonseCV = (CardView) parent.findViewById(R.id.pending_repair_report_cardview_response);
    }


    @Override
    public void update(IPendingRepairReportReadable repairReport) {
        titleTV.setText(repairReport.getTitle());
        statusTV.setText(repairReport.getResponseStatus().toString());
        durationTV.setText(String.format(Locale.ENGLISH, "%d", repairReport.getEstimatedDurationHours()));
        onsiteTV.setText(String.format(Locale.ENGLISH, "%b", repairReport.isOnSite()));
        costTV.setText(repairReport.getCost().toString());
        descriptionTV.setText(repairReport.getDescription());
    }

    @Override
    public void setOnAcceptListener(View.OnClickListener listener) {
        acceptBtn.setOnClickListener(listener);
    }

    @Override
    public void setOnDeclineListener(View.OnClickListener listener) {
        declineBtn.setOnClickListener(listener);
    }

    @Override
    public void displayResponseOptions() {
        repsonseCV.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideResposneOptions() {
        repsonseCV.setVisibility(View.INVISIBLE);
    }
}
