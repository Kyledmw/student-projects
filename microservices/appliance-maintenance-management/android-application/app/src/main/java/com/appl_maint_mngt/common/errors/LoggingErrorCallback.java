package com.appl_maint_mngt.common.errors;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.errors.interfaces.IErrorPayload;
import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import java.util.Arrays;

/**
 * Created by Kyle on 18/04/2017.
 */

public class LoggingErrorCallback implements IErrorCallback {
    private static final Logger logger = LoggerManager.getLogger(LoggingErrorCallback.class);
    @Override
    public void callback(IErrorPayload payload) {
        logger.e(Arrays.toString(payload.getErrors().toArray()));
    }
}
