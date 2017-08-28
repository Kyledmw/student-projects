package com.appl_maint_mngt.diagnostic_report.views.utility;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.utility.TimestampFormatter;
import com.appl_maint_mngt.common.views.ACommonListAdapter;
import com.appl_maint_mngt.diagnostic_report.models.interfaces.IDiagnosticReportReadable;

import java.util.List;

/**
 * Created by Kyle on 09/04/2017.
 */

public class DiagnosticReportListAdapter extends ACommonListAdapter<IDiagnosticReportReadable> {
    public DiagnosticReportListAdapter(@NonNull Context context, @NonNull List<IDiagnosticReportReadable> objects) {
        super(context, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IDiagnosticReportReadable diagnosticReport = getItem(position);

        convertView = prepareConvertView(convertView, parent, R.layout.listitem_diagnostic_report);

        TextView titleTV = (TextView) convertView.findViewById(R.id.listitem_diagnostic_report_title);
        TextView timeTV = (TextView) convertView.findViewById(R.id.listitem_diagnostic_report_time);

        titleTV.setText(diagnosticReport.getTitle());
        timeTV.setText(new TimestampFormatter().format(diagnosticReport.getIssuedTime()));

        return convertView;
    }
}
