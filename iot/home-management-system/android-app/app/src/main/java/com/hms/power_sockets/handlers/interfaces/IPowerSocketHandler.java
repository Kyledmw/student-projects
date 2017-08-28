package com.hms.power_sockets.handlers.interfaces;

import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.hms.devices.model.interfaces.IDeviceReadable;
import com.hms.power_sockets.models.interfaces.IPowerSocketReadable;

import java.util.List;

/**
 * Created by Kyle on 01/03/2016.
 */
public interface IPowerSocketHandler {

    void getPowerSockets(IDeviceReadable device, ICallback<List<IPowerSocketReadable>> callback);

    void sendPowerSocket(IDeviceReadable device, IPowerSocketReadable powerSocketModel, ICallback<Boolean> callback);

    void deletePowerSocket(IDeviceReadable device, IPowerSocketReadable powerSocketModel, ICallback<Boolean> callback);

    void unRegisterAsCallback();
}
