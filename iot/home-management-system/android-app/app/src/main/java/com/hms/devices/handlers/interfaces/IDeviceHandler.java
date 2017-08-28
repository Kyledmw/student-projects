package com.hms.devices.handlers.interfaces;

import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.hms.account.models.interfaces.IAccountReadable;
import com.hms.devices.model.interfaces.IDeviceReadable;

import java.util.List;

/**
 * Created by Kyle on 09/02/2016.
 */
public interface IDeviceHandler {

    String getDevices(IAccountReadable account, ICallback<List<IDeviceReadable>> callback);

    void activateDevice(String key, String name, ICallback<Boolean> callback);

    void deActivateDevice(IDeviceReadable devicee, ICallback<Boolean> callback);

    void unRegisterAsCallback();
}
