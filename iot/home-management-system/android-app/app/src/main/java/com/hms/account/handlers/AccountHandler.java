

package com.hms.account.handlers;

import com.hms.account.handlers.interfaces.IAccountHandler;
import com.hms.account.exceptions.AccountsException;
import com.hms.common.view.models.callbacks.interfaces.ICallback;
import com.hms.account.models.interfaces.IAccountReadable;
import com.hms.account.models.AccountModel;

import im.delight.android.ddp.MeteorCallback;
import im.delight.android.ddp.MeteorSingleton;
import im.delight.android.ddp.ResultListener;

/**
 * Created by alan on 23/02/16.
 */
public class AccountHandler implements MeteorCallback, IAccountHandler {

    private ICallback<IAccountReadable> _createAccountCallback;
    private ICallback<Boolean> _loginCallback;
    private IAccountReadable loggedInAccount;

    public AccountHandler(){
        MeteorSingleton.getInstance().setCallback(this);
    }

    @Override
    public void authenticate(String email, String password, final ICallback<Boolean> callback) {

        MeteorSingleton.getInstance().loginWithEmail(email, password, new ResultListener() {
            @Override
            public void onSuccess(String s) {
                System.out.println("#######Success: " + s);
                callback.callback(null, true);
            }

            @Override
            public void onError(String s, String s1, String s2) {
                System.out.println("#######Error: " + s);
                callback.callback(null, false);
            }
        });
    }

    @Override
    public void createAccount(String email, String password, final ICallback<IAccountReadable> callback) throws AccountsException {

        final IAccountReadable account = new AccountModel(email);
        this._createAccountCallback = callback;

        MeteorSingleton.getInstance().registerAndLogin(email, email, password, new ResultListener() {

            @Override
            public void onSuccess(String s) {
                System.out.println("########################Success: " + s);
                _createAccountCallback.callback(null, account);
            }

            @Override
            public void onError(String s, String s1, String s2) {
                System.out.println("########################Error: " + s + "\n" + s1 + "\n" + s2);
            }
        });
    }

    @Override
    public void logout() {
        MeteorSingleton.getInstance().logout();
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
    public void onDataChanged(String collectionName, String documentID, String updatedValuesJson, String removedValuesJson) {
        System.out.println("Data changed in <"+collectionName+"> in document <"+documentID+">");
        System.out.println("    Updated: "+updatedValuesJson);
        System.out.println("    Removed: "+removedValuesJson);
    }

    @Override
    public void onDataRemoved(String s, String s1) {

    }
}
