package com.appl_maint_mngt.common.utility;

import com.appl_maint_mngt.common.constants.ITimestampConstants;
import com.appl_maint_mngt.common.utility.interfaces.ITimestampFormatter;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Timestamp;

/**
 * Created by Kyle on 08/04/2017.
 */

public class TimestampFormatter implements ITimestampFormatter {

    private DateTimeFormatter dateTimeFormatter;

    public TimestampFormatter() {
        dateTimeFormatter = DateTimeFormat.forPattern(ITimestampConstants.VIEW_FORMAT);
    }

    @Override
    public String format(Timestamp timestamp) {
        DateTime dateTime = new DateTime(timestamp);
        return dateTime.toString(dateTimeFormatter);
    }
}
