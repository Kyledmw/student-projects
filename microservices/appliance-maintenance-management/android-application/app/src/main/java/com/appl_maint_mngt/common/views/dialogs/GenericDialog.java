package com.appl_maint_mngt.common.views.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.views.interfaces.ICommonDialog;

/**
 * Created by Kyle on 10/04/2017.
 */

public class GenericDialog implements ICommonDialog {

    private int titleId;
    private int bodyId;

    private Activity context;

    private DialogInterface.OnClickListener okListener;
    private AlertDialog dialog;

    public GenericDialog(Activity context, int titleId, int bodyId) {
        this.context = context;
        this.titleId = titleId;
        this.bodyId = bodyId;
    }

    @Override
    public void setOnPositiveButtonClickListener(DialogInterface.OnClickListener listener) {
        okListener = listener;
    }

    @Override
    public void create() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(titleId);
        alertDialog.setMessage(bodyId);
        alertDialog.setPositiveButton(R.string.common_ok, okListener);
        alertDialog.setNegativeButton(R.string.common_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
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
}
