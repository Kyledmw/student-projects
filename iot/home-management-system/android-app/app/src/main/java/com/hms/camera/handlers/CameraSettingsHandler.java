package com.hms.camera.handlers;

import com.hms.camera.handlers.interfaces.ICameraSettingsHandler;
import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.hms.devices.model.interfaces.IDeviceReadable;
import com.hms.camera.models.CameraSettingsModel;

import im.delight.android.ddp.MeteorCallback;
import im.delight.android.ddp.MeteorSingleton;

/**
 * Created by alan on 21/04/16.
 */
public class CameraSettingsHandler implements ICameraSettingsHandler, MeteorCallback {

    private ICallback<CameraSettingsModel> _callback;
    private JsonParser _parser;
    private Gson _gson;
    private String _subscriptionId;

    private CameraSettingsModel _model;


    public CameraSettingsHandler() {
        this._gson = new Gson();
        this._parser = new JsonParser();
    }

    @Override
    public void getCameraSettings(IDeviceReadable device, ICallback<CameraSettingsModel> callback) {

        this._callback = callback;
        MeteorSingleton.getInstance().setCallback(this);
        _subscriptionId = MeteorSingleton.getInstance().subscribe("cameraSettingsForDevice", new Object[]{device.getId()});
    }

    @Override
    public void sendCameraSettings(IDeviceReadable device, CameraSettingsModel settings, ICallback<Boolean> callback) {
        JsonObject jsonCameraSettings = settingsModelToJson(settings);
        String json = _gson.toJson(jsonCameraSettings);
        MeteorSingleton.getInstance().call("pushCameraSettings", new Object[]{device.getId(), json});
    }

    @Override
    public void unRegisterAsCallback() {
        MeteorSingleton.getInstance().unsubscribe(_subscriptionId);
        MeteorSingleton.getInstance().unsetCallback(this);

    }

    private JsonObject settingsModelToJson(CameraSettingsModel settingsModel) {
        JsonObject jsonCameraSettings = new JsonObject();

        jsonCameraSettings.addProperty("_id",(settingsModel.getId()));
        jsonCameraSettings.addProperty("power_switch", (settingsModel.getPowerSwitch()) ? 1 : 0);
        jsonCameraSettings.addProperty("recording", (settingsModel.getRecording()) ? 1 : 0);

        return jsonCameraSettings;
    }

    private CameraSettingsModel jsonToSettingsModel(String fieldsJson, String cameraSettingsId) {
        JsonObject jsonAlarmSettings = _parser.parse(fieldsJson).getAsJsonObject();

        int powerSwitch = jsonAlarmSettings.getAsJsonPrimitive("power_switch").getAsInt();
        int recordingSwitch = jsonAlarmSettings.getAsJsonPrimitive("recording").getAsInt();

        return new CameraSettingsModel(cameraSettingsId, (powerSwitch == 1), (recordingSwitch == 1));
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
        JsonObject jsonSettings = _parser.parse(s2).getAsJsonObject();

        JsonPrimitive psJson = jsonSettings.getAsJsonPrimitive("power_switch");
        JsonPrimitive recJson = jsonSettings.getAsJsonPrimitive("recording");

        if(psJson != null) {
            int powerSwitch = psJson.getAsInt();
            _model.setPowerSwitch((powerSwitch == 1));
        } else if(recJson != null) {
            int recSwitch = recJson.getAsInt();
            _model.setRecordingSwitch((recSwitch == 1));
        }
        _callback.callback(null, _model);
    }

    @Override
    public void onDataRemoved(String s, String s1) {

    }
}
