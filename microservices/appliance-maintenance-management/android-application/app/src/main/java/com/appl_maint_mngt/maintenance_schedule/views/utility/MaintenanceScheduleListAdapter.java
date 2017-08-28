package com.appl_maint_mngt.maintenance_schedule.views.utility;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.utility.TimestampFormatter;
import com.appl_maint_mngt.common.views.ACommonListAdapter;
import com.appl_maint_mngt.maintenance_schedule.models.interfaces.IMaintenanceScheduleReadable;

import java.util.List;

/**
 * Created by Kyle on 14/04/2017.
 */

public class MaintenanceScheduleListAdapter extends ACommonListAdapter<IMaintenanceScheduleReadable> {

    public MaintenanceScheduleListAdapter(@NonNull Context context, @NonNull List<IMaintenanceScheduleReadable> objects) {
        super(context, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IMaintenanceScheduleReadable maintenanceSchedule = getItem(position);

        convertView = prepareConvertView(convertView, parent, R.layout.listitem_maintenance_schedule);

        TextView startTimeTV = (TextView)convertView.findViewById(R.id.list_maintenance_schedule_textview_value_start_time);
        TextView endTiemTV= (TextView)convertView.findViewById(R.id.list_maintenance_schedule_textview_value_end_time);
        TextView statusTV= (TextView)convertView.findViewById(R.id.list_maintenance_schedule_textview_value_status);

        startTimeTV.setText(new TimestampFormatter().format(maintenanceSchedule.getStartTime()));
        endTiemTV.setText(new TimestampFormatter().format(maintenanceSchedule.getEndTime()));
        statusTV.setText(maintenanceSchedule.getScheduleStatus().toString());

        return convertView;
    }
}
