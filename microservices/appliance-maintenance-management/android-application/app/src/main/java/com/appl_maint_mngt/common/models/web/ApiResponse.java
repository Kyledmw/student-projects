package com.appl_maint_mngt.common.models.web;

import com.appl_maint_mngt.common.models.web.constants.ApiResponseStatus;
import com.appl_maint_mngt.common.models.web.interfaces.IApiResponse;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kyle on 16/04/2017.
 */

public class ApiResponse<T> implements IApiResponse<T> {

    @SerializedName("status")
    private ApiResponseStatus status;
    private String message;
    private T data;

    public ApiResponse(ApiResponseStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ApiResponseStatus getStatus() {
        return status;
    }
    public void setStatus(ApiResponseStatus status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
}
