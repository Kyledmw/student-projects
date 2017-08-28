package com.appl_maint_mngt.account.views.utility;

import android.content.Intent;

import com.appl_maint_mngt.account.views.LoginActivity;
import com.appl_maint_mngt.account.views.interfaces.IAccountIntentBuilder;
import com.appl_maint_mngt.common.views.ACommonActivity;

/**
 * Created by Kyle on 08/04/2017.
 */

public class AccountIntentBuilder implements IAccountIntentBuilder {
    @Override
    public Intent buildLogin(ACommonActivity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        return intent;
    }
}
