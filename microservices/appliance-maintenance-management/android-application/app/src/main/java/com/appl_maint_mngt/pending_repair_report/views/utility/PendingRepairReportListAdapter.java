package com.appl_maint_mngt.pending_repair_report.views.utility;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.views.ACommonListAdapter;
import com.appl_maint_mngt.pending_repair_report.models.interfaces.IPendingRepairReportReadable;

import java.util.List;

/**
 * Created by Kyle on 13/04/2017.
 */

public class PendingRepairReportListAdapter extends ACommonListAdapter<IPendingRepairReportReadable> {
    public PendingRepairReportListAdapter(@NonNull Context context, @NonNull List<IPendingRepairReportReadable> objects) {
        super(context, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IPendingRepairReportReadable pendingRepairReport = getItem(position);
        convertView = prepareConvertView(convertView, parent, R.layout.listitem_pending_repair_report);

        TextView titleTV = (TextView) convertView.findViewById(R.id.listitem_pending_repair_report_title);
        titleTV.setText(pendingRepairReport.getTitle());

        return convertView;
    }
}
