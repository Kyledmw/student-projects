package com.appl_maint_mngt.diagnostic_report.views.interfaces;

import android.content.Intent;

import com.appl_maint_mngt.common.views.ACommonActivity;

/**
 * Created by Kyle on 09/04/2017.
 */
public interface IDiagnosticReportIntentBuilder {

    Intent build(ACommonActivity activity, Long diagnosticReportId);
}
