package com.appl_maint_mngt.pending_repair_report.views.interfaces;

import android.view.View;

import com.appl_maint_mngt.pending_repair_report.models.interfaces.IPendingRepairReportReadable;

/**
 * Created by Kyle on 11/04/2017.
 */

public interface IPendingRepairReportView {

    void update(IPendingRepairReportReadable repairReport);

    void setOnAcceptListener(View.OnClickListener listener);
    void setOnDeclineListener(View.OnClickListener listener);

    void displayResponseOptions();
    void hideResposneOptions();
}
