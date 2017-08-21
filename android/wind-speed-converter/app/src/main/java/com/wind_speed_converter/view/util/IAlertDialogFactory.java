package com.wind_speed_converter.view.util;

import android.app.Activity;
import android.app.AlertDialog;

/**
 ********************************************************************
 * An interface for a factory that can build alert dialog objects
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IAlertDialogFactory {

    /**
     * Builds an AlertDialog object {@link AlertDialog}
     *
     * @param activity
     *          Activity object {@link import android.app.Activity}
     *          This is the parent activity in which the alert will appear
     *
     * @return The AlertDialog object that was created
     *
     */
    public AlertDialog createDialog(Activity activity);
}
