package com.appl_maint_mngt.diagnostic_request.views.utility;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.errors.DialogErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.common.views.dialogs.GenericDialog;
import com.appl_maint_mngt.diagnostic_request.models.constants.ResponseStatus;
import com.appl_maint_mngt.diagnostic_request.models.interfaces.IDiagnosticRequestReadable;
import com.appl_maint_mngt.diagnostic_request.models.web.DiagnosticRequestUpdatePayload;
import com.appl_maint_mngt.diagnostic_request.models.web.interfaces.IDiagnosticRequestUpdatePayload;
import com.appl_maint_mngt.pending_repair_report.models.interfaces.IPendingRepairReportReadable;
import com.appl_maint_mngt.pending_repair_report.views.PendingRepairReportDialog;

/**
 * Created by Kyle on 10/04/2017.
 */

public class PropertyManagerDiagnosticRequestListItemClickListener implements AdapterView.OnItemClickListener {

    private Activity parent;

    public PropertyManagerDiagnosticRequestListItemClickListener(Activity parent) {
        this.parent = parent;
    }

    @Override
    public void onItemClick(AdapterView<?> p, View view, int position, long id) {
        final IDiagnosticRequestReadable diagReq = (IDiagnosticRequestReadable) p.getItemAtPosition(position);
        switch(diagReq.getResponseStatus()) {
            case RESPONDED:
                IPendingRepairReportReadable pendingRepairReport = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getPendingRepairReportReadableRepository().getForDiagnosticRequestId(diagReq.getId());
                if(pendingRepairReport != null) {
                    PendingRepairReportDialog dialog = new PendingRepairReportDialog(parent, pendingRepairReport);
                    dialog.setOnPositiveButtonClickListener(new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface d, int which) {
                            d.cancel();
                        }
                    });
                    dialog.create();
                    dialog.show();
                }
                break;
            case PENDING:
                final GenericDialog cancelDialog = new GenericDialog(parent, R.string.diagnostic_request_title_cancel, R.string.diagnostic_request_action_confirm_cancel);
                cancelDialog.setOnPositiveButtonClickListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface d, int which) {
                        IDiagnosticRequestUpdatePayload payload = new DiagnosticRequestUpdatePayload();
                        payload.setMaintenanceOrganisationId(diagReq.getMaintenanceOrganisationId());
                        payload.setId(diagReq.getId());
                        payload.setDiagnosticReportId(diagReq.getDiagnosticReportId());
                        payload.setResponseStatus(ResponseStatus.CANCELLED);
                        IntegrationController.getInstance().getControllerFactory().createDiagnosticRequestController().updateDiagnosticRequest(payload, new DialogErrorCallback(parent));
                        cancelDialog.close();
                    }
                });
                cancelDialog.create();
                cancelDialog.show();
                break;
            case CANCELLED:
                final GenericDialog resendDialog = new GenericDialog(parent, R.string.diagnostic_request_title_resend, R.string.diagnostic_request_action_confirm_resend);
                resendDialog.setOnPositiveButtonClickListener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        IDiagnosticRequestUpdatePayload payload = new DiagnosticRequestUpdatePayload();
                        payload.setMaintenanceOrganisationId(diagReq.getMaintenanceOrganisationId());
                        payload.setId(diagReq.getId());
                        payload.setDiagnosticReportId(diagReq.getDiagnosticReportId());
                        payload.setResponseStatus(ResponseStatus.PENDING);
                        resendDialog.close();
                    }
                });
                break;
        }
    }
}
