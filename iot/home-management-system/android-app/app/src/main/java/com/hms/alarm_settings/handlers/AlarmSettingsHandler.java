package com.hms.alarm_settings.handlers;

import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.hms.data_triggers.models.interfaces.IDataTriggerReadable;
import com.hms.devices.model.interfaces.IDeviceFeatureReadable;
import com.hms.devices.model.interfaces.IDeviceReadable;
import com.hms.alarm_settings.models.AlarmSettingModel;
import com.hms.devices.model.DeviceFeatureModel;

import java.util.ArrayList;
import java.util.List;

import im.delight.android.ddp.MeteorCallback;
import im.delight.android.ddp.MeteorSingleton;
import im.delight.android.ddp.ResultListener;

/**
 * Created by alan on 21/04/16.
 */
public class AlarmSettingsHandler implements IAlarmSettingsHandler, MeteorCallback {

    private ICallback<AlarmSettingModel> _callback;
    private JsonParser _parser;
    private Gson _gson;
    private String _subscriptionId;
    private AlarmSettingModel _model;


    public AlarmSettingsHandler() {
        this._gson = new Gson();
        this._parser = new JsonParser();
    }

    @Override
    public void getAlarmSettings(IDeviceReadable device, ICallback<AlarmSettingModel> callback) {

        this._callback = callback;
        MeteorSingleton.getInstance().setCallback(this);
        _subscriptionId = MeteorSingleton.getInstance().subscribe("alarmSettingsForDevice", new Object[]{device.getId()});
    }

    @Override
    public void setAlarmSettings(IDeviceReadable device, AlarmSettingModel settings, ICallback<Boolean> callback) {
        JsonObject jsonAlarmSettings = settingsModelToJson(settings);
        String json = _gson.toJson(jsonAlarmSettings);
        MeteorSingleton.getInstance().call("pushAlarmSettings", new Object[]{device.getId(), json});
    }

    @Override
    public void pushAlarmTrigger(IDeviceReadable device, IDataTriggerReadable triggerModel, final ICallback<String> callback) {
        JsonObject jsonTrigger = triggerModelToJson(triggerModel);
        String json = _gson.toJson(jsonTrigger);
        MeteorSingleton.getInstance().call("pushDataTriggers", new Object[]{device.getId(), json}, new ResultListener() {

            @Override
            public void onSuccess(String s) {
                callback.callback(null, "Successfully created the data trigger.");
            }

            @Override
            public void onError(String s, String s1, String s2) {
                callback.callback(null, "Error occurred creating the data trigger.");
            }
        });
    }

    @Override
    public void getDeviceFeatures(IDeviceReadable device, final ICallback<List<IDeviceFeatureReadable>> callback) {
        MeteorSingleton.getInstance().call("implementedDeviceFeaturesForDevice", new Object[] {device.getId()}, new ResultListener() {
            @Override
            public void onSuccess(String s) {
                System.out.println("SUCCESS: #1");
                System.out.println(s);
                ArrayList<IDeviceFeatureReadable> devFetList = new ArrayList<IDeviceFeatureReadable>();
                JsonArray devFeatureListJSON = _parser.parse(s).getAsJsonArray();
                for (int i = 0; i < devFeatureListJSON.size(); i++) {
                    JsonObject devFetJSON = devFeatureListJSON.get(i).getAsJsonObject();
                    String id = devFetJSON.getAsJsonPrimitive("_id").getAsString();
                    String type = devFetJSON.getAsJsonPrimitive("feature_type").getAsString();

                    devFetList.add(new DeviceFeatureModel(id, type));
                }

                callback.callback(null, devFetList);

            }

            @Override
            public void onError(String s, String s1, String s2) {
                System.out.println("ERROR: #1");
                System.out.println(s);
                System.out.println(s1);
                System.out.println(s2);
            }
        });
    }

    private JsonObject triggerModelToJson(IDataTriggerReadable trigger) {
        JsonObject jsonTriggerModel = new JsonObject();
        jsonTriggerModel.addProperty("device_feature_id", trigger.getFeatureTypeId());
        jsonTriggerModel.addProperty("limit", trigger.getLimit());
        jsonTriggerModel.addProperty("alarm_enabled", (trigger.getAlarmEnabled()) ? 1 : 0);
        jsonTriggerModel.addProperty("notifications_enabled", (trigger.getNotificationsEnabled()) ? 1 : 0);
        return jsonTriggerModel;
    }

    @Override
    public void unRegisterAsCallback() {
        MeteorSingleton.getInstance().unsubscribe(_subscriptionId);
        MeteorSingleton.getInstance().unsetCallback(this);
    }

    private AlarmSettingModel jsonToSettingsModel(String fieldsJson, String alarmSettingsId) {
        JsonObject jsonAlarmSettings = _parser.parse(fieldsJson).getAsJsonObject();

        int powerSwitch = jsonAlarmSettings.getAsJsonPrimitive("power_switch").getAsInt();
        int triggerSwitch = jsonAlarmSettings.getAsJsonPrimitive("trigger_switch").getAsInt();
        int duration = jsonAlarmSettings.getAsJsonPrimitive("duration").getAsInt();

        return new AlarmSettingModel(alarmSettingsId, (powerSwitch == 1), (triggerSwitch == 1), duration);
    }


    private JsonObject settingsModelToJson(AlarmSettingModel settingsModel) {

        JsonObject jsonAlarmSettings = new JsonObject();
        jsonAlarmSettings.addProperty("_id",(settingsModel.getId()));
        jsonAlarmSettings.addProperty("power_switch", (settingsModel.getPowerSwitch()) ? 1 : 0);
        jsonAlarmSettings.addProperty("trigger_switch", (settingsModel.getTriggerSwitch()) ? 1 : 0);
        jsonAlarmSettings.addProperty("duration", (settingsModel.getDurationSeconds()));

        return jsonAlarmSettings;
    }

    @Override
    public void onConnect(boolean b) {

    }

    @Override
    public void onDisconnect() {

    }

    @Override
    public void onException(Exception e) {

    }

    @Override
    public void onDataAdded(String collectionName, String documentID, String fieldsJson) {
        System.out.println("Data added to <"+collectionName+"> in document <"+documentID+">");
        System.out.println("    Added: " + fieldsJson);

        _model = jsonToSettingsModel(fieldsJson, documentID);
        _callback.callback(null, _model);
    }

    @Override
    public void onDataChanged(String s, String s1, String s2, String s3) {
        System.out.println("Data changed to <"+s+"> in document <"+s1+">");
        System.out.println("    Changed: " + s2);

        JsonObject jsonAlarmSettings = _parser.parse(s2).getAsJsonObject();


        JsonPrimitive jsonPS = jsonAlarmSettings.getAsJsonPrimitive("power_switch");
        JsonPrimitive jsonTS = jsonAlarmSettings.getAsJsonPrimitive("trigger_switch");
        JsonPrimitive jsonDuration = jsonAlarmSettings.getAsJsonPrimitive("duration");

        if(jsonPS != null) {
            int powerSwitch = jsonPS.getAsInt();
            _model.setPowerSwitch((powerSwitch == 1));
        } else if(jsonTS != null) {
            int triggerSwitch = jsonTS.getAsInt();
            _model.setTriggerSwitch((triggerSwitch  == 1));
        } else if(jsonDuration != null) {
            int duration = jsonDuration.getAsInt();
            _model.setDurationSeconds(duration);
        }

        _callback.callback(null, _model);
    }

    @Override
    public void onDataRemoved(String s, String s1) {

    }
}
