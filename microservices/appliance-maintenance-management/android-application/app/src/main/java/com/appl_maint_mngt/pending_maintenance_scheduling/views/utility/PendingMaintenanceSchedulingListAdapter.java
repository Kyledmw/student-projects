package com.appl_maint_mngt.pending_maintenance_scheduling.views.utility;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.utility.TimestampFormatter;
import com.appl_maint_mngt.common.views.ACommonListAdapter;
import com.appl_maint_mngt.pending_maintenance_scheduling.models.interfaces.IPendingMaintenanceScheduleReadable;

import java.util.List;

/**
 * Created by Kyle on 15/04/2017.
 */

public class PendingMaintenanceSchedulingListAdapter extends ACommonListAdapter<IPendingMaintenanceScheduleReadable> {

    public PendingMaintenanceSchedulingListAdapter(@NonNull Context context, @NonNull List<IPendingMaintenanceScheduleReadable> objects) {
        super(context, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IPendingMaintenanceScheduleReadable pendingMaintenanceSchedule = getItem(position);

        convertView = prepareConvertView(convertView, parent, R.layout.listitem_pending_maintenance_scheduling);

        TextView startTimeTV = (TextView)convertView.findViewById(R.id.list_pending_maintenance_schedule_textview_value_start_time);
        TextView endTiemTV= (TextView)convertView.findViewById(R.id.list_pending_maintenance_schedule_textview_value_end_time);
        TextView statusTV= (TextView)convertView.findViewById(R.id.list_pending_maintenance_schedule_textview_value_status);
        TextView typeTV = (TextView) convertView.findViewById(R.id.list_pending_maintenance_schedule_textview_value_type);

        startTimeTV.setText(new TimestampFormatter().format(pendingMaintenanceSchedule.getStartTime()));
        endTiemTV.setText(new TimestampFormatter().format(pendingMaintenanceSchedule.getEndTime()));
        statusTV.setText(pendingMaintenanceSchedule.getScheduleStatus().toString());
        typeTV.setText(pendingMaintenanceSchedule.getSchedulerType().toString());

        return convertView;
    }
}
