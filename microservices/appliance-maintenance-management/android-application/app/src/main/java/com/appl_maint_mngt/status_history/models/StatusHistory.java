package com.appl_maint_mngt.status_history.models;

import java.sql.Timestamp;

/**
 * Created by Kyle on 17/03/2017.
 */
public class StatusHistory extends AStatusHistory {

    private Long statusId;
    private Timestamp loggedTime;

    @Override
    public Long getStatusId() {
        return statusId;
    }

    @Override
    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    @Override
    public Timestamp getLoggedTime() {
        return loggedTime;
    }

    @Override
    public void setLoggedTime(Timestamp loggedTime) {
        this.loggedTime = loggedTime;
    }
}
