package com.appl_maint_mngt.repair_report.views;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.utility.TimestampFormatter;
import com.appl_maint_mngt.maintenance_schedule.models.interfaces.IMaintenanceScheduleReadable;
import com.appl_maint_mngt.pending_maintenance_scheduling.models.interfaces.IPendingMaintenanceScheduleReadable;
import com.appl_maint_mngt.pending_maintenance_scheduling.views.utility.PendingMaintenanceSchedulingListAdapter;
import com.appl_maint_mngt.repair_report.models.RepairReport;
import com.appl_maint_mngt.repair_report.models.interfaces.IRepairReportReadable;
import com.appl_maint_mngt.repair_report.views.interfaces.IRepairReportView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Kyle on 11/04/2017.
 */

public class RepairReportView implements IRepairReportView {

    private TextView titleTV;
    private TextView descriptionValueTV;
    private TextView durationValueTV;
    private TextView onsiteTV;
    private TextView costTV;

    private Button diagnosticReportBtn;

    private CardView maintenanceScheduleCV;

    private TextView maintSchedStartTimeTV;
    private TextView maintSchedEndTimeTV;
    private TextView maintSchedStatusTV;

    private CardView pendingMaintSchedCV;
    private ListView pendingMaintSchedLV;
    private PendingMaintenanceSchedulingListAdapter listAdapter;

    public RepairReportView(Activity parent) {
        titleTV = (TextView) parent.findViewById(R.id.repair_report_textview_title);
        descriptionValueTV = (TextView) parent.findViewById(R.id.repair_report_textview_value_description);
        durationValueTV = (TextView) parent.findViewById(R.id.repair_report_textview_value_duration);
        onsiteTV = (TextView) parent.findViewById(R.id.repair_report_textview_value_onsite);
        costTV = (TextView) parent.findViewById(R.id.repair_report_textview_value_cost);
        diagnosticReportBtn = (Button) parent.findViewById(R.id.repair_report_button_diagnostic_report);

        maintenanceScheduleCV = (CardView) parent.findViewById(R.id.repair_report_cardview_maint_sched);
        maintSchedStartTimeTV = (TextView) parent.findViewById(R.id.repair_report_textview_maint_sched_value_start_time);
        maintSchedEndTimeTV = (TextView) parent.findViewById(R.id.repair_report_textview_maint_sched_value_end_time);
        maintSchedStatusTV = (TextView) parent.findViewById(R.id.repair_report_textview_maint_sched_value_status);

        pendingMaintSchedCV = (CardView) parent.findViewById(R.id.repair_report_cardview_pending_maint_sched);
        pendingMaintSchedLV = (ListView) parent.findViewById(R.id.repair_report_listview_pending_maint_sched);
        listAdapter = new PendingMaintenanceSchedulingListAdapter(parent, new ArrayList<IPendingMaintenanceScheduleReadable>());
        pendingMaintSchedLV.setAdapter(listAdapter);
    }


    @Override
    public void update(IRepairReportReadable repairReport) {
        titleTV.setText(repairReport.getTitle());
        descriptionValueTV.setText(repairReport.getDescription());
        durationValueTV.setText(String.format(Locale.ENGLISH, "%d", repairReport.getEstimatedDurationHours()));
        onsiteTV.setText(String.format(Locale.ENGLISH, "%b", repairReport.isOnSite()));
        costTV.setText(repairReport.getCost().toString());
    }

    @Override
    public void setDiagnosticReportOnClickListener(View.OnClickListener listener) {
        diagnosticReportBtn.setOnClickListener(listener);
    }

    @Override
    public void enableDiagnosticReportButton() {
        diagnosticReportBtn.setEnabled(true);
    }

    @Override
    public void disableDiagnosticReportButton() {
        diagnosticReportBtn.setEnabled(false);
    }

    @Override
    public void updateMaintenanceSchedule(IMaintenanceScheduleReadable maintenanceSchedule) {
        maintSchedStartTimeTV.setText(new TimestampFormatter().format(maintenanceSchedule.getStartTime()));
        maintSchedEndTimeTV.setText(new TimestampFormatter().format(maintenanceSchedule.getEndTime()));
        maintSchedStatusTV.setText(maintenanceSchedule.getScheduleStatus().toString());
    }

    @Override
    public void showMaintenanceSchedule() {
        maintenanceScheduleCV.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideMaintenanceSchedule() {
        maintenanceScheduleCV.setVisibility(View.INVISIBLE);
    }

    @Override
    public void updatePendingMaintenanceSchedules(List<IPendingMaintenanceScheduleReadable> list) {
        listAdapter.clear();
        listAdapter.addAll(list);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void showPendingMaintenanceSchedules() {
        pendingMaintSchedCV.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePendingMaintenanceSchedules() {
        pendingMaintSchedCV.setVisibility(View.INVISIBLE);
    }
}
