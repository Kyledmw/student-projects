package com.appl_maint_mngt.pending_repair_report.views.interfaces;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by Kyle on 12/04/2017.
 */

public interface IPendingRepairReportIntentBuilder {

    Intent build(Activity activity, Long id);

    Intent buildList(Activity activity);
}
