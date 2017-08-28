package com.appl_maint_mngt.diagnostic_request.views;

import android.app.Activity;
import android.widget.AdapterView;
import android.widget.ListView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.views.ACommonListAdapter;
import com.appl_maint_mngt.diagnostic_request.models.interfaces.IDiagnosticRequestReadable;
import com.appl_maint_mngt.diagnostic_request.views.interfaces.IDiagnosticRequestListView;

import java.util.List;

/**
 * Created by Kyle on 10/04/2017.
 */

public class DiagnosticRequestListView implements IDiagnosticRequestListView {

    private ListView diagnosticRequestsLV;
    private ACommonListAdapter<IDiagnosticRequestReadable> diagnosticRequestListAdapter;

    public DiagnosticRequestListView(Activity parent, ACommonListAdapter<IDiagnosticRequestReadable> listAdapter) {
        diagnosticRequestsLV = (ListView) parent.findViewById(R.id.diagnosticrequest_list_listview_items);
        this.diagnosticRequestListAdapter = listAdapter;
        diagnosticRequestsLV.setAdapter(diagnosticRequestListAdapter);
    }

    @Override
    public void update(List<IDiagnosticRequestReadable> diagnosticRequests) {
        diagnosticRequestListAdapter.clear();
        diagnosticRequestListAdapter.addAll(diagnosticRequests);
        diagnosticRequestListAdapter.notifyDataSetChanged();
    }

    @Override
    public void setOnDiagnosticRequestSelelectListener(AdapterView.OnItemClickListener listener) {
        diagnosticRequestsLV.setOnItemClickListener(listener);
    }
}
