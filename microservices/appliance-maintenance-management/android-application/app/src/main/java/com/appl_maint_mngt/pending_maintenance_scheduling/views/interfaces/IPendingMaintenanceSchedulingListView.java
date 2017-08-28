package com.appl_maint_mngt.pending_maintenance_scheduling.views.interfaces;

import android.widget.AdapterView;

import com.appl_maint_mngt.pending_maintenance_scheduling.models.interfaces.IPendingMaintenanceScheduleReadable;

import java.util.List;

/**
 * Created by Kyle on 15/04/2017.
 */

public interface IPendingMaintenanceSchedulingListView {

    void update(List<IPendingMaintenanceScheduleReadable> list);

    void setOnItemClickLsitener(AdapterView.OnItemClickListener listener);
}
