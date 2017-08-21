package ie.kyle.alert_dialog_factories;

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
     * Builds an AlertDialog object {@link android.app.AlertDialog}
     *
     * @param activity
     *          Activity object {@link import android.app.Activity}
     *          This is the parent activity in which the alert will appear
     *
     * @param message_id
     *          The resource id of the string to be displayed in the dialog body
     *
     * @param title_id
     *          The resource id of the string to be displayed as the dialog title
     *
     *
     * @return The AlertDialog object that was created
     *
     */
    AlertDialog createDialog(Activity activity, int message_id, int title_id);
}

