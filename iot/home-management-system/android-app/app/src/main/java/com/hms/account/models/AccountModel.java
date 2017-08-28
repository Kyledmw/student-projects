package com.hms.account.models;

import com.hms.account.models.interfaces.IAccountReadable;

/**
 * Created by Kyle on 09/02/2016.
 */
public class AccountModel implements IAccountReadable {


    private String _email;

    public AccountModel(String email) {
        _email = email;
    }

    @Override
    public String getEmail() {
        return _email;
    }

    @Override
    public String toString() {
        return "Email: " + _email;
    }
}
