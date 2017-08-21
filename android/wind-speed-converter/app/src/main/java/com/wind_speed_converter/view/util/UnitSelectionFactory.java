package com.wind_speed_converter.view.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.wind_speed_converter.R;

/**
 ********************************************************************
 * A factory that is responsible for building Alert Dialogs for Unit Selection
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class UnitSelectionFactory implements IAlertDialogFactory {

    @Override
    public AlertDialog createDialog(Activity activity) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setMessage(R.string.select_unit_desc).setTitle(R.string.select_unit);

        return builder.create();
    }
}
