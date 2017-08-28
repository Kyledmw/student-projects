package com.appl_maint_mngt;

import android.content.Intent;
import android.os.Bundle;

import com.appl_maint_mngt.account.views.utility.AccountIntentBuilder;
import com.appl_maint_mngt.common.views.ACommonActivity;

import net.danlew.android.joda.JodaTimeAndroid;

import java.util.Observable;

public class MainActivity extends ACommonActivity {

    private static MainActivity instance;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        instance = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JodaTimeAndroid.init(this);
        toLoginView();
    }

    @Override
    public void updateModels() {

    }

    @Override
    protected void startObserving() {

    }

    @Override
    protected void stopObserving() {

    }

    @Override
    protected void updateView() {
    }

    private void toLoginView() {
        Intent loginIntent = new AccountIntentBuilder().buildLogin(this);
        startActivity(loginIntent);
    }

    public static MainActivity getInstance() {
        return instance;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
