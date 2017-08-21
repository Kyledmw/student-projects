package com.wind_speed_converter.view.activities;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import com.wind_speed_converter.R;
import com.wind_speed_converter.view.components.BarChartView;
import com.wind_speed_converter.view.constants.IActivityConstants;

import java.util.HashMap;
import java.util.Map;


/**
 ********************************************************************
 * This activity class is responsible for controlling the <i>activity_graphic_view.xml</i>
 *
 * @extends {@link android.support.v7.app.AppCompatActivity}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class GraphicActivity extends AppCompatActivity {

    private static final String PHONE_NUMBER = "021 1234567";
    private static final String TEL_LABEL = "tel:";

    private BarChartView _barchartView;

    private double _metric;
    private double _knot;
    private double _beua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic);

        initViewListeners();

        //retrieve data passed from previous activity
        Intent i = getIntent();
        _metric = i.getDoubleExtra(IActivityConstants.METRIC_KEY, -1);
        _knot = i.getDoubleExtra(IActivityConstants.KNOT_KEY, -1);
        _beua = i.getDoubleExtra(IActivityConstants.BEUA_KEY, -1);

        _barchartView = (BarChartView) findViewById(R.id.drawing_area);

        //Create map of relevant data for the BarChartView
        Map<String, Double> data = new HashMap<String, Double>();
        data.put(IActivityConstants.METRIC_KEY, _metric);
        data.put(IActivityConstants.KNOT_KEY, _knot);
        data.put(IActivityConstants.BEUA_KEY, _beua);

        _barchartView.setBarchartData(data);

        //force a redraw in the future
        _barchartView.invalidate();
    }

    /**
     * This method is responsible for creating listeners for the view components
     * This is called when the activity is created
     */
    private void initViewListeners() {
        Button notificationBtn = (Button) findViewById(R.id.graph_btn_notif);

        notificationBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                createNotification();
            }
        });
    }
    /**
     * This method is responsible for creating a notification which calls PHONE_NUMBER on tap.
     *
     * This is called on the notificationButton click listener.
     */
    private void createNotification() {
        //Use Notification Builder to create our notification
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContentTitle(getString(R.string.call_notif));
        mBuilder.setContentText(getString(R.string.call) + PHONE_NUMBER);
        mBuilder.setSmallIcon(R.drawable.call_notif);

        //Create an intent to be used for our notification.
        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(TEL_LABEL + PHONE_NUMBER));

        //Build a stack of notification intents
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntent(callIntent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pendingIntent);

        //Tell the NotificationManger to notify the user with our built notification
        NotificationManager notifManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notifManager.notify(1, mBuilder.build());

    }
}
