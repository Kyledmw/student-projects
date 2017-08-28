package com.appl_maint_mngt.maintenance_schedule.views;

import android.app.Activity;
import android.widget.ListView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.maintenance_schedule.models.interfaces.IMaintenanceScheduleReadable;
import com.appl_maint_mngt.maintenance_schedule.views.interfaces.IMaintenanceScheduleListView;
import com.appl_maint_mngt.maintenance_schedule.views.utility.MaintenanceScheduleListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 14/04/2017.
 */

public class MaintenanceScheduleListView implements IMaintenanceScheduleListView {

    private ListView maintenanceScheduleLV;
    private MaintenanceScheduleListAdapter listAdapter;

    public MaintenanceScheduleListView(Activity parent) {
        maintenanceScheduleLV = (ListView) parent.findViewById(R.id.list_maintenance_schedule_listview_items);
        listAdapter = new MaintenanceScheduleListAdapter(parent, new ArrayList<IMaintenanceScheduleReadable>());
        maintenanceScheduleLV.setAdapter(listAdapter);
    }

    @Override
    public void update(List<IMaintenanceScheduleReadable> list) {
        listAdapter.clear();
        listAdapter.addAll(list);
        listAdapter.notifyDataSetChanged();
    }
}
