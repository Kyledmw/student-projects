package com.appl_maint_mngt.diagnostic_request.utility;

import android.app.Activity;
import android.content.Intent;

import com.appl_maint_mngt.diagnostic_request.utility.interfaces.IDiagnosticRequestIntentBuilder;
import com.appl_maint_mngt.diagnostic_request.views.DiagnosticRequestsListActivity;

/**
 * Created by Kyle on 10/04/2017.
 */

public class DiagnosticRequestIntentBuilder implements IDiagnosticRequestIntentBuilder {
    @Override
    public Intent buildListIntent(Activity from) {
        Intent intent = new Intent(from, DiagnosticRequestsListActivity.class);
        return intent;
    }
}
