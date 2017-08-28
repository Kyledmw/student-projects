package com.appl_maint_mngt.repair_report.views;

import android.app.Activity;
import android.widget.AdapterView;
import android.widget.ListView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.repair_report.models.interfaces.IRepairReportReadable;
import com.appl_maint_mngt.repair_report.views.interfaces.IRepairReportListView;
import com.appl_maint_mngt.repair_report.views.utility.RepairReportListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 14/04/2017.
 */
public class RepairReportListView implements IRepairReportListView {

    private ListView repairReportLV;
    private RepairReportListAdapter listAdapter;


    public RepairReportListView(Activity parent) {
        repairReportLV = (ListView) parent.findViewById(R.id.list_repair_report_listview_items);
        listAdapter = new RepairReportListAdapter(parent, new ArrayList<IRepairReportReadable>());
        repairReportLV.setAdapter(listAdapter);
    }

    @Override
    public void update(List<IRepairReportReadable> list) {
        listAdapter.clear();
        listAdapter.addAll(list);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        repairReportLV.setOnItemClickListener(listener);
    }
}
