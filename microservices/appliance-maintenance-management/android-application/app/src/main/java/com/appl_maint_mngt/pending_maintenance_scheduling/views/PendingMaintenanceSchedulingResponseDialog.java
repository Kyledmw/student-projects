package com.appl_maint_mngt.pending_maintenance_scheduling.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.errors.DialogErrorCallback;
import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.errors.interfaces.IErrorPayload;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.pending_maintenance_scheduling.models.interfaces.IPendingMaintenanceScheduleReadable;

/**
 * Created by Kyle on 15/04/2017.
 */

public class PendingMaintenanceSchedulingResponseDialog {

    private Activity parent;
    private IPendingMaintenanceScheduleReadable pendingMaintenanceSchedule;

    private AlertDialog dialog;

    public PendingMaintenanceSchedulingResponseDialog(Activity parent, IPendingMaintenanceScheduleReadable pendingMaintenanceSchedule) {
        this.parent = parent;
        this.pendingMaintenanceSchedule = pendingMaintenanceSchedule;
    }

    public void create() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(parent);
        alertDialog.setTitle(R.string.pending_maintenance_schedule_label_respond);
        alertDialog.setPositiveButton(R.string.common_action_accept, new AcceptPendingMaintenanceSchedule());
        alertDialog.setNegativeButton(R.string.common_action_decline, new DeclinePendingMaintenanceSchedule());
        dialog = alertDialog.create();
    }

    public void show() {
        dialog.show();
    }

    private class AcceptPendingMaintenanceSchedule implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface d, int which) {
            IntegrationController.getInstance().getControllerFactory().createPendingMaintenanceSchedulingController().acceptPendingSchedule(pendingMaintenanceSchedule.getId(), new IErrorCallback() {
                @Override
                public void callback(IErrorPayload payload) {
                    dialog.dismiss();
                    new DialogErrorCallback(parent).callback(payload);
                }
            });
        }
    }

    private class DeclinePendingMaintenanceSchedule implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface d, int which) {
            IntegrationController.getInstance().getControllerFactory().createPendingMaintenanceSchedulingController().declinePendingSchedule(pendingMaintenanceSchedule.getId(), new IErrorCallback() {
                @Override
                public void callback(IErrorPayload payload) {
                    dialog.dismiss();
                    new DialogErrorCallback(parent).callback(payload);
                }
            });
        }
    }
}
