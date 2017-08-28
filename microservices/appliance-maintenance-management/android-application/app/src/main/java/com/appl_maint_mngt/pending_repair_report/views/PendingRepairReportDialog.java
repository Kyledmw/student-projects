package com.appl_maint_mngt.pending_repair_report.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.errors.DialogErrorCallback;
import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.errors.interfaces.IErrorPayload;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.common.views.interfaces.ICommonDialog;
import com.appl_maint_mngt.pending_repair_report.models.constants.ResponseStatus;
import com.appl_maint_mngt.pending_repair_report.models.interfaces.IPendingRepairReportReadable;
import com.appl_maint_mngt.pending_repair_report.views.interfaces.IPendingRepairReportView;

/**
 * Created by Kyle on 11/04/2017.
 */

public class PendingRepairReportDialog implements ICommonDialog {

    private Activity parent;
    private IPendingRepairReportReadable pendingRepairReport;

    private IPendingRepairReportView pendingRepairReportView;

    private DialogInterface.OnClickListener okListener;
    private AlertDialog dialog;

    public PendingRepairReportDialog(Activity parent, IPendingRepairReportReadable pendingRepairReport) {
        this.parent = parent;
        this.pendingRepairReport = pendingRepairReport;
    }

    @Override
    public void setOnPositiveButtonClickListener(DialogInterface.OnClickListener listener) {
        this.okListener = listener;
    }

    @Override
    public void create() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(parent);
        LayoutInflater inflater = parent.getLayoutInflater();
        View convertView = inflater.inflate(R.layout.dialog_pending_repair_report, null);
        alertDialog.setView(convertView);
        alertDialog.setTitle(R.string.pending_repair_report_title);
        alertDialog.setPositiveButton(R.string.common_ok, okListener);

        pendingRepairReportView = new PendingRepairReportDialogView(convertView);
        pendingRepairReportView.update(pendingRepairReport);
        setupViewListeners();
        if(pendingRepairReport.getResponseStatus().equals(ResponseStatus.ACCEPTED)) {
            pendingRepairReportView.hideResposneOptions();
        } else {
            pendingRepairReportView.displayResponseOptions();
        }

        dialog = alertDialog.create();
    }

    @Override
    public void show() {
        dialog.show();
    }

    @Override
    public void close() {
        dialog.cancel();
    }

    private void setupViewListeners() {
        pendingRepairReportView.setOnAcceptListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntegrationController.getInstance().getControllerFactory().createPendingRepairReportController().acceptPendingRepairReport(pendingRepairReport, new PendingRepairReportErrorCallback());
            }
        });
        pendingRepairReportView.setOnDeclineListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                IntegrationController.getInstance().getControllerFactory().createPendingRepairReportController().declinePendingRepairReport(pendingRepairReport, new PendingRepairReportErrorCallback());
            }
        });
    }

    private class PendingRepairReportErrorCallback implements IErrorCallback {

        @Override
        public void callback(IErrorPayload payload) {
            DialogErrorCallback errorDialog = new DialogErrorCallback(parent);
            close();
            errorDialog.callback(payload);
        }
    }
}
