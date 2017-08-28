package com.appl_maint_mngt.diagnostic_request.views.utility;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.common.views.ACommonListAdapter;
import com.appl_maint_mngt.diagnostic_request.models.interfaces.IDiagnosticRequestReadable;
import com.appl_maint_mngt.maintenance_organisation.models.interfaces.IMaintenanceOrganisationReadable;
import com.appl_maint_mngt.maintenance_organisation.repositories.interfaces.IMaintenanceOrganisationReadableRepository;

import java.util.List;

/**
 * Created by Kyle on 10/04/2017.
 */

public class DiagnosticRequestListAdapterMaintenanceOrganisation extends ACommonListAdapter<IDiagnosticRequestReadable> {

    private IMaintenanceOrganisationReadableRepository maintenanceOrganisationRepository;

    public DiagnosticRequestListAdapterMaintenanceOrganisation(@NonNull Context context, @NonNull List<IDiagnosticRequestReadable> objects) {
        super(context, objects);
        maintenanceOrganisationRepository = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getMaintenanceOrganisationRepository();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IDiagnosticRequestReadable diagnosticRequest = getItem(position);

        convertView = prepareConvertView(convertView, parent, R.layout.listitem_diagnostic_request_maint_org);

        TextView maintOrgTV = (TextView) convertView.findViewById(R.id.listitem_diagnostic_request_maint_org);
        TextView statusTV = (TextView) convertView.findViewById(R.id.listitem_diagnostic_request_maint_org_status);

        IMaintenanceOrganisationReadable maintOrg = maintenanceOrganisationRepository.getForId(diagnosticRequest.getMaintenanceOrganisationId());
        if(maintOrg != null) {
            maintOrgTV.setText(maintOrg.getName());
        }
        statusTV.setText(diagnosticRequest.getResponseStatus().toString());

        return convertView;
    }
}
