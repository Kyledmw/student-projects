package com.hms.sensors.settings.handlers;

import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hms.devices.model.interfaces.IDeviceReadable;
import com.hms.sensors.settings.models.SensorSettingsModel;
import com.hms.sensors.settings.handlers.interfaces.ISensorSettingsHandler;

import im.delight.android.ddp.MeteorCallback;
import im.delight.android.ddp.MeteorSingleton;

/**
 * Created by alan on 21/04/16.
 */
public class SensorSettingsHandler implements ISensorSettingsHandler, MeteorCallback {

    private ICallback<SensorSettingsModel> _callback;
    private JsonParser _parser = new JsonParser();
    private Gson _gson;
    private String _subscriptionId;

    public SensorSettingsHandler() {
        _gson = new Gson();
    }

    @Override
    public void getSensorSettings(IDeviceReadable device, ICallback<SensorSettingsModel> callback) {
        this._callback = callback;
        MeteorSingleton.getInstance().setCallback(this);
        _subscriptionId = MeteorSingleton.getInstance().subscribe("sensorSettingsForDevice", new Object[]{device.getId()});
    }


    @Override
    public void setSensorSettings(IDeviceReadable device, SensorSettingsModel settings, ICallback<Boolean> callback) {

        JsonObject jsonSensorSettings = settingsModelToJson(settings);
        String json = _gson.toJson(jsonSensorSettings);
        MeteorSingleton.getInstance().call("pushSensorSettings", new Object[]{device.getId(), json});
    }

    @Override
    public void unRegisterAsCallback() {
        MeteorSingleton.getInstance().unsubscribe(_subscriptionId);
        MeteorSingleton.getInstance().unsetCallback(this);
    }


    private JsonObject settingsModelToJson(SensorSettingsModel settingsModel) {

        JsonObject jsonSensorSettings = new JsonObject();
        jsonSensorSettings.addProperty("_id",(settingsModel.getId()));
        jsonSensorSettings.addProperty("power_switch", (settingsModel.getPowerSwitch()) ? 1 : 0);
        return jsonSensorSettings;
    }


    public SensorSettingsModel jsonToSensorSettings(String fieldsJson, String sensorModelId) {

        JsonObject jsonSensorSettings = _parser.parse(fieldsJson).getAsJsonObject();
        int powerSwitch = jsonSensorSettings.getAsJsonPrimitive("power_switch").getAsInt();

        SensorSettingsModel sensorSettings;
        if(powerSwitch == 1) {
            sensorSettings = new SensorSettingsModel(true);
        }
        else {
            sensorSettings = new SensorSettingsModel(false);
        }

        sensorSettings.setId(sensorModelId);
        return sensorSettings;
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

        SensorSettingsModel sensorSettings = jsonToSensorSettings(fieldsJson, documentID);
        _callback.callback(null, sensorSettings);
    }

    @Override
    public void onDataChanged(String s, String s1, String s2, String s3) {
        System.out.println("Data added to <"+s+"> in document <"+s1+">");
        System.out.println("    Changed: " + s2);

        SensorSettingsModel sensorSettings = jsonToSensorSettings(s2, s1);
        _callback.callback(null, sensorSettings);

    }

    @Override
    public void onDataRemoved(String s, String s1) {

    }
}
