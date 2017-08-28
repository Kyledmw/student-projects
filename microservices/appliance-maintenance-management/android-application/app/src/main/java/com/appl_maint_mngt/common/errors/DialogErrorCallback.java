package com.appl_maint_mngt.common.errors;

import android.content.Context;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.errors.interfaces.IErrorPayload;
import com.appl_maint_mngt.common.views.dialogs.error.ErrorAlertDialogBuilder;

/**
 * Created by Kyle on 07/04/2017.
 */

public class DialogErrorCallback implements IErrorCallback {

    private Context context;

    public DialogErrorCallback(Context parent) {
        this.context = parent;
    }

    @Override
    public void callback(IErrorPayload payload) {
        new ErrorAlertDialogBuilder().build(context, payload.getErrors()).show();
    }
}
