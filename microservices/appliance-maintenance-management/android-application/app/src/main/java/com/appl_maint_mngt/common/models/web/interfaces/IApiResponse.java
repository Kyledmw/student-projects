package com.appl_maint_mngt.common.models.web.interfaces;

import com.appl_maint_mngt.common.models.web.constants.ApiResponseStatus;

/**
 * Created by Kyle on 16/04/2017.
 */

public interface IApiResponse<T> {
    ApiResponseStatus getStatus();
    void setStatus(ApiResponseStatus status);
    String getMessage();
    void setMessage(String message);
    T getData();
    void setData(T data);
}
