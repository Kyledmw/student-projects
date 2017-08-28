package com.appl_maint_mngt.common.utility;

import android.os.Handler;

import com.appl_maint_mngt.common.utility.interfaces.ICommonActivityIntervalUpdater;
import com.appl_maint_mngt.common.views.ACommonActivity;

/**
 * Created by Kyle on 18/04/2017.
 */

public class CommonActivityIntervalUpdater implements ICommonActivityIntervalUpdater {
    private static final int SECOND = 1000;

    private final static int INTERVAL = SECOND * 5;

    private ACommonActivity activity;

    private Handler updateHandler;
    private Runnable updateTask;

    public CommonActivityIntervalUpdater(final ACommonActivity activity) {
        this.activity = activity;
        updateHandler = new Handler();
        updateTask = new Runnable() {
            @Override
            public void run() {
                activity.updateModels();
                updateHandler.postDelayed(updateTask, INTERVAL);
            }
        };
    }

    @Override
    public void startUpdating() {
        updateTask.run();
    }

    @Override
    public void stopUpdating() {
        updateHandler.removeCallbacks(updateTask);
    }
}
