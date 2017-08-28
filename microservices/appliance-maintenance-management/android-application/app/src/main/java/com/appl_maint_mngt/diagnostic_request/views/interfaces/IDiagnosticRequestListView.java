package com.appl_maint_mngt.diagnostic_request.views.interfaces;

import android.view.View;
import android.widget.AdapterView;

import com.appl_maint_mngt.diagnostic_request.models.interfaces.IDiagnosticRequestReadable;

import java.util.List;

/**
 * Created by Kyle on 10/04/2017.
 */

public interface IDiagnosticRequestListView {

    void update(List<IDiagnosticRequestReadable> diagnosticRequests);

    void setOnDiagnosticRequestSelelectListener(AdapterView.OnItemClickListener listener);
}
