package com.hms.alarm_settings.handlers;

import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.hms.data_triggers.models.interfaces.IDataTriggerReadable;
import com.hms.devices.model.interfaces.IDeviceFeatureReadable;
import com.hms.devices.model.interfaces.IDeviceReadable;
import com.hms.alarm_settings.models.AlarmSettingModel;

import java.util.List;

/**
 * Created by Kyle on 19/02/2016.
 */
public interface IAlarmSettingsHandler {

    void getAlarmSettings(IDeviceReadable device, ICallback<AlarmSettingModel> callback);

    void setAlarmSettings(IDeviceReadable device, AlarmSettingModel settings, ICallback<Boolean> callback);

    void pushAlarmTrigger(IDeviceReadable device, IDataTriggerReadable triggerModel, ICallback<String> callback);

    void getDeviceFeatures(IDeviceReadable device, ICallback<List<IDeviceFeatureReadable>> callback);

    void unRegisterAsCallback();
}
