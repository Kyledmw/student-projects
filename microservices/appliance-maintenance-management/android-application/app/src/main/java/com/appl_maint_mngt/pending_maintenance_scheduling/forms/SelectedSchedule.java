package com.appl_maint_mngt.pending_maintenance_scheduling.forms;

import org.joda.time.DateTime;

/**
 * Created by Kyle on 15/04/2017.
 */

public class SelectedSchedule {

    private DateTime start;
    private DateTime end;

    public SelectedSchedule(DateTime start, DateTime end) {
        this.start = start;
        this.end = end;
    }

    public DateTime getStart() {
        return start;
    }

    public void setStart(DateTime start) {
        this.start = start;
    }

    public DateTime getEnd() {
        return end;
    }

    public void setEnd(DateTime end) {
        this.end = end;
    }
}
