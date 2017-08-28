package com.appl_maint_mngt.common.views.interfaces;

import android.content.DialogInterface;

/**
 * Created by Kyle on 08/04/2017.
 */

public interface ICommonDialog {

    void setOnPositiveButtonClickListener(DialogInterface.OnClickListener listener);

    void create();
    void show();
    void close();
}
