package com.appl_maint_mngt.common.utility;

import com.appl_maint_mngt.common.constants.ITimestampConstants;
import com.appl_maint_mngt.common.utility.interfaces.IGsonFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Kyle on 16/04/2017.
 */

public class GsonFactory implements IGsonFactory {
    @Override
    public Gson createDateFormattingGson() {
        return new GsonBuilder().setDateFormat(ITimestampConstants.WEB_FORMAT).create();
    }
}
