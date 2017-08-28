package com.appl_maint_mngt.common.views.dialogs.error.interfaces;

import android.app.AlertDialog;
import android.content.Context;

import java.util.List;

/**
 * Created by Kyle on 15/03/2017.
 */
public interface IErrorAlertDialogBuilder {

    AlertDialog build(Context context, List<String> errors);
}
