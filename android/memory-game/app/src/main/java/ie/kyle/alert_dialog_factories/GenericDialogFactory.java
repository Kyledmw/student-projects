package ie.kyle.alert_dialog_factories;

import android.app.Activity;
import android.app.AlertDialog;

/**
 ********************************************************************
 * A factory that is responsible for building Generic Alert Dialogs
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class GenericDialogFactory implements IAlertDialogFactory {

    @Override
    public AlertDialog createDialog(Activity activity, int message_id, int title_id) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setMessage(message_id).setTitle(title_id);

        return builder.create();
    }
}
