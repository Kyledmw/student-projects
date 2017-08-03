package com.chat_room.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteCallback<T> extends Remote{

	public void notify(T object) throws RemoteException;
}
