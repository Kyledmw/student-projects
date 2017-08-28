package com.hms.data_triggers.handlers;

import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.hms.devices.model.interfaces.IDeviceReadable;
import com.hms.data_triggers.models.DataTriggersModel;

import java.util.List;

/**
 * Created by Kyle on 19/02/2016.
 */
public interface IDataTriggerHandlers {

    void getDataTriggers(IDeviceReadable device, ICallback<List<DataTriggersModel>> callback);

    void setDataTriggers(IDeviceReadable device, List<DataTriggersModel> dataTriggers, ICallback<Boolean> callback);
}
