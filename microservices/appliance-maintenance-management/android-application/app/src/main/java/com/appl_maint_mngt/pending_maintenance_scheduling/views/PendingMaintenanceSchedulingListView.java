package com.appl_maint_mngt.pending_maintenance_scheduling.views;

import android.app.Activity;
import android.widget.AdapterView;
import android.widget.ListView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.pending_maintenance_scheduling.models.interfaces.IPendingMaintenanceScheduleReadable;
import com.appl_maint_mngt.pending_maintenance_scheduling.views.interfaces.IPendingMaintenanceSchedulingListView;
import com.appl_maint_mngt.pending_maintenance_scheduling.views.utility.PendingMaintenanceSchedulingListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 15/04/2017.
 */

public class PendingMaintenanceSchedulingListView implements IPendingMaintenanceSchedulingListView {

    private ListView pendingMaintenanceSchedulingLV;
    private PendingMaintenanceSchedulingListAdapter listAdapter;

    public PendingMaintenanceSchedulingListView(Activity parent) {
        pendingMaintenanceSchedulingLV = (ListView) parent.findViewById(R.id.list_pending_maintenance_scheduling_listview_items);
        listAdapter = new PendingMaintenanceSchedulingListAdapter(parent, new ArrayList<IPendingMaintenanceScheduleReadable>());
        pendingMaintenanceSchedulingLV.setAdapter(listAdapter);

    }

    @Override
    public void update(List<IPendingMaintenanceScheduleReadable> list) {
        listAdapter.clear();
        listAdapter.addAll(list);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void setOnItemClickLsitener(AdapterView.OnItemClickListener listener) {
        pendingMaintenanceSchedulingLV.setOnItemClickListener(listener);
    }
}
