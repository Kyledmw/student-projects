package com.appl_maint_mngt.pending_maintenance_scheduling.forms.interfaces;

import org.joda.time.DateTime;

/**
 * Created by Kyle on 15/04/2017.
 */

public interface ISelectedSchedule {

    DateTime getStart();

    void setStart(DateTime start);

    DateTime getEnd();

    void setEnd(DateTime end);
}
