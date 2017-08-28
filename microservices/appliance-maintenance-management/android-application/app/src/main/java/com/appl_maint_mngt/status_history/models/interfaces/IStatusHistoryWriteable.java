package com.appl_maint_mngt.status_history.models.interfaces;

import java.sql.Timestamp;

/**
 * Created by Kyle on 17/03/2017.
 */

public interface IStatusHistoryWriteable {

    void setStatusId(Long statusId);
    void setLoggedTime(Timestamp loggedTime);
}
