package com.appl_maint_mngt.diagnostic_request.views.utility;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;

import com.appl_maint_mngt.diagnostic_request.models.interfaces.IDiagnosticRequestReadable;

/**
 * Created by Kyle on 10/04/2017.
 */

public class MaintenanceEngineerDiagnosticRequestListitemClickListener implements AdapterView.OnItemClickListener {

    private Activity parent;

    public MaintenanceEngineerDiagnosticRequestListitemClickListener(Activity parent) {
        this.parent = parent;
    }

    @Override
    public void onItemClick(AdapterView<?> p, View view, int position, long id) {
        final IDiagnosticRequestReadable diagReq = (IDiagnosticRequestReadable) p.getItemAtPosition(position);
        switch(diagReq.getResponseStatus()) {
            case PENDING:
                break;
        }
    }
}
