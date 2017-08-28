package com.appl_maint_mngt.pending_repair_report.views.utility;

import android.app.Activity;
import android.content.Intent;

import com.appl_maint_mngt.pending_repair_report.views.PendingRepairReportActivity;
import com.appl_maint_mngt.pending_repair_report.views.PendingRepairReportListActivity;
import com.appl_maint_mngt.pending_repair_report.views.constants.IPendingRepairReportViewConstants;
import com.appl_maint_mngt.pending_repair_report.views.interfaces.IPendingRepairReportIntentBuilder;
import com.appl_maint_mngt.pending_repair_report.views.interfaces.IPendingRepairReportView;

/**
 * Created by Kyle on 12/04/2017.
 */

public class PendingRepairReportIntentBuilder implements IPendingRepairReportIntentBuilder {
    @Override
    public Intent build(Activity activity, Long id) {
        Intent intent = new Intent(activity, PendingRepairReportActivity.class);
        intent.putExtra(IPendingRepairReportViewConstants.ID_KEY, id);
        return intent;
    }

    @Override
    public Intent buildList(Activity activity) {
        return new Intent(activity, PendingRepairReportListActivity.class);
    }
}
