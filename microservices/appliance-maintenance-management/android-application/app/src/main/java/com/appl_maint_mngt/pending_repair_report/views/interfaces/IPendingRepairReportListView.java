package com.appl_maint_mngt.pending_repair_report.views.interfaces;

import android.widget.AdapterView;

import com.appl_maint_mngt.pending_repair_report.models.interfaces.IPendingRepairReportReadable;

import java.util.List;

/**
 * Created by Kyle on 13/04/2017.
 */

public interface IPendingRepairReportListView {

    void update(List<IPendingRepairReportReadable> list);

    void setOnItemClickListener(AdapterView.OnItemClickListener listener);
}
