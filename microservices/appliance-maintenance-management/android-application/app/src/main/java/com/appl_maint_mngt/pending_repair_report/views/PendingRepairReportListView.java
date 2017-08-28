package com.appl_maint_mngt.pending_repair_report.views;

import android.app.Activity;
import android.widget.AdapterView;
import android.widget.ListView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.pending_repair_report.models.interfaces.IPendingRepairReportReadable;
import com.appl_maint_mngt.pending_repair_report.views.interfaces.IPendingRepairReportListView;
import com.appl_maint_mngt.pending_repair_report.views.utility.PendingRepairReportListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 13/04/2017.
 */

public class PendingRepairReportListView implements IPendingRepairReportListView {

    private ListView itemsLV;
    private PendingRepairReportListAdapter listAdapter;

    public PendingRepairReportListView(Activity activity) {
        itemsLV = (ListView) activity.findViewById(R.id.list_pending_repair_report_listview_items);
        listAdapter = new PendingRepairReportListAdapter(activity, new ArrayList<IPendingRepairReportReadable>());
        itemsLV.setAdapter(listAdapter);
    }

    @Override
    public void update(List<IPendingRepairReportReadable> list) {
        listAdapter.clear();
        listAdapter.addAll(list);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        itemsLV.setOnItemClickListener(listener);
    }
}
