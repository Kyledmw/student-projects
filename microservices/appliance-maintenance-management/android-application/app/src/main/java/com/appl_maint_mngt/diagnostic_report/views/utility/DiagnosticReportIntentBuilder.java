package com.appl_maint_mngt.diagnostic_report.views.utility;

import android.content.Intent;

import com.appl_maint_mngt.common.views.ACommonActivity;
import com.appl_maint_mngt.diagnostic_report.views.DiagnosticReportActivity;
import com.appl_maint_mngt.diagnostic_report.views.constants.IDiagnosticReportViewConstants;
import com.appl_maint_mngt.diagnostic_report.views.interfaces.IDiagnosticReportIntentBuilder;

/**
 * Created by Kyle on 09/04/2017.
 */

public class DiagnosticReportIntentBuilder implements IDiagnosticReportIntentBuilder {
    @Override
    public Intent build(ACommonActivity activity, Long diagnosticReportId) {
        Intent intent = new Intent(activity, DiagnosticReportActivity.class);
        intent.putExtra(IDiagnosticReportViewConstants.ID_KEY, diagnosticReportId);
        return intent;
    }
}
