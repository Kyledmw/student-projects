package com.chat_room.server.rmi.adapters;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

import com.chat_room.rmi.interfaces.IRemoteCallback;
import com.chat_room.rmi.interfaces.IRemoteMessage;
import com.chat_room.server.constants.IChatConstants;
import com.chat_room.server.models.interfaces.IChatUpdate;
import com.chat_room.server.models.interfaces.IMessage;

public class RMICallbackHandler implements Observer {
	
	private static RMICallbackHandler _instance;
	
	private Queue<IRemoteCallback<IRemoteMessage>> _callbackQueue;
	private Queue<IRemoteCallback<IRemoteMessage>> _intermediateQueue;
	
	private boolean _newMessage;
	
	private RMICallbackHandler(){
		_callbackQueue = new LinkedList<IRemoteCallback<IRemoteMessage>>();
		_intermediateQueue = new LinkedList<IRemoteCallback<IRemoteMessage>>();
		_newMessage = false;
	}
	
	public static RMICallbackHandler getInstance() {
		if(_instance == null) {
			_instance = new RMICallbackHandler();
		}
		return _instance;
	}

	public void update(Observable o, Object arg) {
		_newMessage = true;
		if(arg instanceof IChatUpdate){
			String updateType = ((IChatUpdate) arg).getUpdateType();
			if(updateType.equals(IChatConstants.NEW_MESSAGE)) {
				IRemoteCallback<IRemoteMessage> callback;
				try {
					IRemoteMessage rm = (IRemoteMessage) UnicastRemoteObject.exportObject(new RemoteMessageAdapter((IMessage) arg), 0);
					while(!_callbackQueue.isEmpty()) {
						try {
							callback = _callbackQueue.poll();
							callback.notify(rm);
						} catch (RemoteException e) {
							_callbackQueue.remove(rm);
						}
					}
					_callbackQueue = _intermediateQueue;
					_intermediateQueue = new LinkedList<IRemoteCallback<IRemoteMessage>>();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}
		_newMessage = false;
	}
	
	public void addCallback(IRemoteCallback<IRemoteMessage> callback) {
		if(_newMessage) {
			_intermediateQueue.add(callback);
		} else {
			_callbackQueue.add(callback);
		}
	}

}
/*
 * 
			IRemoteMessage rm = (IRemoteMessage) UnicastRemoteObject.exportObject(new RemoteMessageAdapter(cur), 0);
 * 
 * 
 */
