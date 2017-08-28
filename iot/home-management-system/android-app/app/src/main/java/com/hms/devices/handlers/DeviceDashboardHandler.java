package com.hms.devices.handlers;

import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hms.devices.handlers.interfaces.IDeviceDashboardHandler;
import com.hms.devices.model.interfaces.IDeviceFeatureReadable;
import com.hms.devices.model.interfaces.IDeviceReadable;
import com.hms.devices.model.DeviceFeatureModel;

import im.delight.android.ddp.MeteorSingleton;
import im.delight.android.ddp.ResultListener;
import im.delight.android.ddp.MeteorCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 24/04/2016.
 */
public class DeviceDashboardHandler implements IDeviceDashboardHandler, MeteorCallback {

    private ICallback<List<IDeviceFeatureReadable>> _activityCallback;
    private JsonParser _parser;

    public DeviceDashboardHandler() {
        _parser = new JsonParser();
    }

    @Override
    public void getFeaturesForDevice(IDeviceReadable device, ICallback<List<IDeviceFeatureReadable>> callback) {

        _activityCallback = callback;

        MeteorSingleton.getInstance().setCallback(this);
        System.out.println("HERE: #1");
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

                _activityCallback.callback(null, devFetList);

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
    public void onDataAdded(String s, String s1, String s2) {

    }

    @Override
    public void onDataChanged(String s, String s1, String s2, String s3) {

    }

    @Override
    public void onDataRemoved(String s, String s1) {

    }
}
