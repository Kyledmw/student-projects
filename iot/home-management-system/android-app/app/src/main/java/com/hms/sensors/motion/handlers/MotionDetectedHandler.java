package com.hms.sensors.motion.handlers;

import android.util.Base64;

import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.hms.sensors.motion.handlers.interfaces.IMotionDetectionHandler;
import com.hms.camera.models.interfaces.ICameraReadable;
import com.hms.devices.model.interfaces.IDeviceReadable;
import com.hms.sensors.motion.model.interfaces.IMotionDetectionReadable;
import com.hms.camera.models.CameraModel;
import com.hms.sensors.motion.model.MotionDetectionModel;

import im.delight.android.ddp.MeteorCallback;
import im.delight.android.ddp.MeteorSingleton;
import im.delight.android.ddp.ResultListener;
import im.delight.android.ddp.SubscribeListener;

/**
 * Created by alan on 26/02/16.
 */
public class MotionDetectedHandler implements MeteorCallback, IMotionDetectionHandler {
    private ICallback<IMotionDetectionReadable> _callback;
    private JsonParser parser = new JsonParser();
    private String _subscriptionId;


    @Override
    public void getMotionDetected(IDeviceReadable device, ICallback<IMotionDetectionReadable> callback) {

        MeteorSingleton.getInstance().setCallback(this);
        this._callback = callback;

        _subscriptionId = MeteorSingleton.getInstance().subscribe("motionDetectionForDevice", new Object[]{device.getId()}, new SubscribeListener() {
            @Override
            public void onSuccess() {
                System.out.println("### Successfully subscribed to motion ###");
            }

            @Override
            public void onError(String s, String s1, String s2) {
                System.out.println("### Motion subscription failed ###");
                System.out.println(s + "\n" + s1 + "\n" + s2 + "\n");
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

   private void jsonToMotionDetectionModel(String fieldsJson) {

       final JsonObject jsonMotionDetection = parser.parse(fieldsJson).getAsJsonObject();

       final String strCameraModelId = jsonMotionDetection.getAsJsonPrimitive("camera_data_id").getAsString();

       MeteorSingleton.getInstance().call("cameraDataForId", new Object[]{strCameraModelId}, new ResultListener() {
           @Override
           public void onSuccess(String s) {
               int timestamp = jsonMotionDetection.getAsJsonPrimitive("timestamp").getAsInt();
               MotionDetectionModel mdl = new MotionDetectionModel(timestamp, strCameraModelId);

               if(s != null) {
                   ICameraReadable cameraModel = jsonToCameraModel(s);
                   mdl.setCameraModel(cameraModel);
               }

               _callback.callback(null, mdl);
           }

           @Override
           public void onError(String s, String s1, String s2) {

           }
       });
    }


    private ICameraReadable jsonToCameraModel(String fieldsJson) {
        JsonObject jsonCameraModel = parser.parse(fieldsJson).getAsJsonObject();
        JsonPrimitive jsonTimestamp = jsonCameraModel.getAsJsonPrimitive("timestamp");
        JsonPrimitive jsonImage = jsonCameraModel.getAsJsonPrimitive("image");
        JsonPrimitive jsonId = jsonCameraModel.getAsJsonPrimitive("_id");

        long timestamp = jsonTimestamp.getAsLong();
        String strImage = jsonImage.getAsString();
        String strId = jsonId.getAsString();

        byte [] arrImage = Base64.decode(strImage, Base64.DEFAULT);
        CameraModel cameraModel = new CameraModel(strId, arrImage, timestamp);
        return cameraModel;
    }


    @Override
    public void onDataAdded(String collectionName, String documentID, String fieldsJson) {
        System.out.println("Data added to <"+collectionName+"> in document <"+documentID+">");
        System.out.println("    Added: " + fieldsJson);
        jsonToMotionDetectionModel(fieldsJson);
    }

    @Override
    public void onDataChanged(String collectionName, String documentID, String updatedValuesJson, String removedValuesJson) {
        System.out.println("Data changed in <"+collectionName+"> in document <"+documentID+">");
        System.out.println("    Updated: " + updatedValuesJson);
        System.out.println("    Removed: " + removedValuesJson);
    }

    @Override
    public void onDataRemoved(String collectionName, String documentID) {
        System.out.println("Data removed from <" + collectionName + "> in document <" + documentID + ">");
    }
}
