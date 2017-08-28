package com.hms.account.handlers.interfaces;

import com.hms.account.exceptions.AccountsException;
import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.hms.account.models.interfaces.IAccountReadable;

/**
 * Created by Kyle on 09/02/2016.
 */
public interface IAccountHandler {

    void authenticate(String email, String password, ICallback<Boolean> callback);

    void createAccount(String email, String password, ICallback<IAccountReadable> callback) throws AccountsException;

    void logout();
}
