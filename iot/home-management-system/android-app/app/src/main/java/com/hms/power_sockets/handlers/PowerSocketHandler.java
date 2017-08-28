package com.hms.power_sockets.handlers;

import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hms.devices.model.interfaces.IDeviceReadable;
import com.hms.power_sockets.handlers.interfaces.IPowerSocketHandler;
import com.hms.power_sockets.models.interfaces.IPowerSocketReadable;
import com.hms.power_sockets.models.PowerSocketModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import im.delight.android.ddp.MeteorCallback;
import im.delight.android.ddp.MeteorSingleton;
import im.delight.android.ddp.ResultListener;
import im.delight.android.ddp.SubscribeListener;

/**
 * Created by alan on 05/04/16.
 */
public class PowerSocketHandler implements IPowerSocketHandler, MeteorCallback {

    private String _subscriptionId;
    private ArrayList<IPowerSocketReadable> _powerSockets;
    private ICallback<List<IPowerSocketReadable>> _callback;
    private JsonParser _parser = new JsonParser();
    private Gson gson = new Gson();


    public PowerSocketHandler() {
        _powerSockets = new ArrayList<>();
    }


    @Override
    public void getPowerSockets(IDeviceReadable device, ICallback<List<IPowerSocketReadable>> callback) {
        this._callback = callback;

        MeteorSingleton.getInstance().setCallback(this);
        _subscriptionId = MeteorSingleton.getInstance().subscribe("powerSocketsForDevice", new Object[]{device.getId()}, new SubscribeListener() {
            @Override
            public void onSuccess() {
                System.out.println("### Successfully subscribed to power sockets###");
            }

            @Override
            public void onError(String s, String s1, String s2) {
                System.out.println("### Power socket subscription failed ###");
                System.out.println(s + "\n" + s1 + "\n" + s2 + "\n");
            }
        });

    }


    @Override
    public void sendPowerSocket(IDeviceReadable device, IPowerSocketReadable powerSocket, final ICallback<Boolean> callback) {
        JsonObject jsonPowerSocket = powerSocketModelToJson(powerSocket);
        String json = gson.toJson(jsonPowerSocket);

        MeteorSingleton.getInstance().call("pushPowerSocket", new Object[]{device.getId(), json}, new ResultListener() {
            @Override
            public void onSuccess(String s) {
                System.out.println("### Successfully updated power socket ###");
                if(s == null) {
                    callback.callback(null, true);
                }
                else {
                    callback.callback(null, false);
                }
            }

            @Override
            public void onError(String s, String s1, String s2) {
                System.out.println("### Power socket update failed ###");
                System.out.println(s + s1 + s2);
                callback.callback(null, false);
            }
        });
    }


    @Override
    public void deletePowerSocket(IDeviceReadable device, IPowerSocketReadable powerSocketModel, final ICallback<Boolean> callback) {

        MeteorSingleton.getInstance().call("pushDeletePowerSocket", new Object[]{device.getId(), powerSocketModel.getId()}, new ResultListener() {
            @Override
            public void onSuccess(String s) {
                System.out.println("### Successfully deleted power socket ###");
                callback.callback(null, true);
            }

            @Override
            public void onError(String s, String s1, String s2) {
                System.out.println("### Power socket deletion failed ###");
                System.out.println(s + s1 + s2);
                callback.callback(null, false);
            }
        });
    }


    private JsonObject powerSocketModelToJson(IPowerSocketReadable powerSocket) {
        JsonObject jsonPowerSocket = new JsonObject();

        jsonPowerSocket.addProperty("_id", powerSocket.getId());
        jsonPowerSocket.addProperty("socket_name", powerSocket.getName());
        jsonPowerSocket.addProperty("socket_number", powerSocket.getNumber());
        jsonPowerSocket.addProperty("power_switch", (powerSocket.getPowerSwitch()) ? 1 : 0);

        return jsonPowerSocket;
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

    public IPowerSocketReadable jsonToPowerSocket(String fieldsJson, String powerSocketId) {
        JsonObject jsonPowerSocket = _parser.parse(fieldsJson).getAsJsonObject();

        String socketName = jsonPowerSocket.getAsJsonPrimitive("socket_name").getAsString();
        int socketNumber = jsonPowerSocket.getAsJsonPrimitive("socket_number").getAsInt();
        int powerSwitch = jsonPowerSocket.getAsJsonPrimitive("power_switch").getAsInt();

        IPowerSocketReadable powerSocketReadable;
        if(powerSwitch == 1) {
            powerSocketReadable = new PowerSocketModel(powerSocketId, socketName, socketNumber, true);
        }
        else {
            powerSocketReadable = new PowerSocketModel(powerSocketId, socketName, socketNumber, false);
        }

        return powerSocketReadable;
    }


    @Override
    public void onDataAdded(String collectionName, String documentID, String fieldsJson) {
        System.out.println("Data added to <"+collectionName+"> in document <"+documentID+">");
        System.out.println("    Added: " + fieldsJson);

        _powerSockets.add(jsonToPowerSocket(fieldsJson, documentID));
        _callback.callback(null, _powerSockets);
    }

    @Override
    public void onDataChanged(String collectionName, String documentID, String updatedValuesJson, String removedValuesJson) {
        System.out.println("Data changed in <" + collectionName + "> in document <" + documentID + ">");
        System.out.println("    Updated: " + updatedValuesJson);
        System.out.println("    Removed: " + removedValuesJson);

        if(removedValuesJson != null) {
            _powerSockets.remove(jsonToPowerSocket(removedValuesJson, documentID));
            _callback.callback(null, _powerSockets);
        }
    }

    @Override
    public void onDataRemoved(String collectionName, String documentID) {
        System.out.println("Data removed from <"+collectionName+"> in document <"+documentID+">");

        for(Iterator<IPowerSocketReadable> it = _powerSockets.iterator(); it.hasNext();) {
            IPowerSocketReadable powerSocket = it.next();
            if(powerSocket.getId().equals(documentID)) {
                it.remove();
            }
        }
        _callback.callback(null, _powerSockets);
    }
}
