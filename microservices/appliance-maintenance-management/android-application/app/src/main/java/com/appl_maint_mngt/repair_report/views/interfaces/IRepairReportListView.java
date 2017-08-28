package com.appl_maint_mngt.repair_report.views.interfaces;

import android.widget.AdapterView;

import com.appl_maint_mngt.repair_report.models.interfaces.IRepairReportReadable;

import java.util.List;

/**
 * Created by Kyle on 14/04/2017.
 */

public interface IRepairReportListView {

    void update(List<IRepairReportReadable> list);
    void setOnItemClickListener(AdapterView.OnItemClickListener listener);
}
