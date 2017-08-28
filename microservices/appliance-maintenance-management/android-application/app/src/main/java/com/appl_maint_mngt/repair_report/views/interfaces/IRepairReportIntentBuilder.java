package com.appl_maint_mngt.repair_report.views.interfaces;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by Kyle on 13/04/2017.
 */

public interface IRepairReportIntentBuilder {

    Intent build(Activity activity, Long id);

    Intent buildList(Activity activity);
}
