package com.appl_maint_mngt.repair_report.views.utility;

import android.app.Activity;
import android.content.Intent;

import com.appl_maint_mngt.repair_report.views.RepairReportActivity;
import com.appl_maint_mngt.repair_report.views.RepairReportListActivity;
import com.appl_maint_mngt.repair_report.views.constants.IRepairReportViewConstants;
import com.appl_maint_mngt.repair_report.views.interfaces.IRepairReportIntentBuilder;

/**
 * Created by Kyle on 13/04/2017.
 */

public class RepairReportIntentBuilder implements IRepairReportIntentBuilder {
    @Override
    public Intent build(Activity activity, Long id) {
        Intent intent = new Intent(activity, RepairReportActivity.class);
        intent.putExtra(IRepairReportViewConstants.ID_KEY, id);
        return intent;
    }

    @Override
    public Intent buildList(Activity activity) {
        return new Intent(activity, RepairReportListActivity.class);
    }
}
