package com.hms.common.view.models.callbacks.interfaces;

import com.hms.common.view.models.callbacks.exceptions.CallbackException;

/**
 * Created by Kyle on 09/02/2016.
 */
public interface ICallback <T> {

    void callback(CallbackException e, T data);
}
