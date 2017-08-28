package com.hms.camera.handlers;

import android.util.Base64;

import com.hms.camera.handlers.interfaces.ICameraDataHandler;
import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.hms.camera.models.interfaces.ICameraReadable;
import com.hms.devices.model.interfaces.IDeviceReadable;
import com.hms.camera.models.CameraModel;

import im.delight.android.ddp.MeteorCallback;
import im.delight.android.ddp.MeteorSingleton;
import im.delight.android.ddp.SubscribeListener;

/**
 * Created by alan on 14/04/16.
 */
public class CameraDataHandler implements MeteorCallback, ICameraDataHandler {

    private ICallback<ICameraReadable> _callback;
    private JsonParser parser = new JsonParser();
    private String _subscriptionId;

    @Override
    public void getCameraData(IDeviceReadable device, ICallback<ICameraReadable> callback) {

        this._callback = callback;
        MeteorSingleton.getInstance().setCallback(this);
        _subscriptionId = MeteorSingleton.getInstance().subscribe("realTimeCameraForDevice", new Object[]{device.getId()}, new SubscribeListener() {
            @Override
            public void onSuccess() {
                System.out.println("### Successfully subscribed to camera data ###");
            }

            @Override
            public void onError(String s, String s1, String s2) {
                System.out.println("### Error subscription failed for camera data");
            }
        });
    }

    @Override
    public void unRegisterAsCallback() {
        MeteorSingleton.getInstance().unsubscribe(_subscriptionId);
        MeteorSingleton.getInstance().unsetCallback(this);
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

    private ICameraReadable jsonToCameraModel(String fieldsJson, String cameraDataId) {
        JsonObject jsonCameraModel = parser.parse(fieldsJson).getAsJsonObject();
        JsonPrimitive jsonTimestamp = jsonCameraModel.getAsJsonPrimitive("timestamp");
        JsonPrimitive jsonImage = jsonCameraModel.getAsJsonPrimitive("image");

        long timestamp = jsonTimestamp.getAsLong();
        String strImage = jsonImage.getAsString();

        byte [] arrImage = Base64.decode(strImage, Base64.DEFAULT);
        CameraModel cameraModel = new CameraModel(cameraDataId, arrImage, timestamp);
        return cameraModel;
    }


    @Override
    public void onDataAdded(String collectionName, String documentID, String fieldsJson) {
        System.out.println("Data added to <"+collectionName+"> in document <"+documentID+">");
        System.out.println("    Added: " + fieldsJson);

        ICameraReadable cameraModel = jsonToCameraModel(fieldsJson, documentID);
        _callback.callback(null, cameraModel);
    }

    @Override
    public void onDataChanged(String s, String s1, String s2, String s3) {
        System.out.println("Data added to <"+s+"> in document <"+s1+">");
        System.out.println("    Added: " + s2);
        ICameraReadable cameraModel = jsonToCameraModel(s2, s1);
        _callback.callback(null, cameraModel);
    }

    @Override
    public void onDataRemoved(String s, String s1) {

    }
}
