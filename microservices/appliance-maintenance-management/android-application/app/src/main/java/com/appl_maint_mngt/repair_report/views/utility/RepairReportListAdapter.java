package com.appl_maint_mngt.repair_report.views.utility;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.views.ACommonListAdapter;
import com.appl_maint_mngt.repair_report.models.interfaces.IRepairReportReadable;

import java.util.List;

/**
 * Created by Kyle on 14/04/2017.
 */

public class RepairReportListAdapter extends ACommonListAdapter<IRepairReportReadable> {

    public RepairReportListAdapter(@NonNull Context context, @NonNull List<IRepairReportReadable> objects) {
        super(context, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IRepairReportReadable repairReport = getItem(position);

        convertView = prepareConvertView(convertView, parent, R.layout.listitem_repair_report);

        TextView titleTV = (TextView) convertView.findViewById(R.id.listitem_repair_report_title);
        titleTV.setText(repairReport.getTitle());

        return convertView;
    }
}
