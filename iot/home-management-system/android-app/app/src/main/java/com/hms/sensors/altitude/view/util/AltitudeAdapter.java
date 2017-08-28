package com.hms.sensors.altitude.view.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hms_app.R;
import com.hms.sensors.altitude.models.interfaces.IAltitudeReadable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.SimpleTimeZone;

/**
 * Created by alan on 03/04/16.
 */
public class AltitudeAdapter extends ArrayAdapter<IAltitudeReadable>{

    private Calendar _calendar;
    private SimpleDateFormat _dateFormat;

    public AltitudeAdapter(Context context, List<IAltitudeReadable> altitudeData) {
        super(context, 0, altitudeData);
        _calendar = new GregorianCalendar(SimpleTimeZone.getTimeZone("GMT"));
        _dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IAltitudeReadable altitudeReading = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.sensor_data_view, parent, false);
        }

        TextView dateHeader = (TextView)convertView.findViewById(R.id.sensor_data_date_tv);
        ImageView altitudeIconIv = (ImageView)convertView.findViewById(R.id.data_type_icon_iv);
        TextView altitudeValueTv = (TextView)convertView.findViewById(R.id.data_value_tv);
        TextView timestampTv = (TextView)convertView.findViewById(R.id.data_timestamp_tv);
        TextView measurementTypeTv = (TextView)convertView.findViewById(R.id.sensor_data_measurement_type_tv);

        String strDate = getDateString(altitudeReading.getTimestampSeconds());
        String strAltitude = Double.toString(altitudeReading.getValue());
        String strTimestamp = getTimeString(altitudeReading.getTimestampSeconds());
        String strMeasurementType = altitudeReading.getType();

        dateHeader.setText(strDate);
        altitudeIconIv.setImageResource(R.drawable.menu_item_altitude_100);
        altitudeValueTv.setText(strAltitude);
        measurementTypeTv.setText(strMeasurementType);
        timestampTv.setText(strTimestamp);

        return convertView;
    }



    private String getTimeString(long timestamp){
        _calendar.setTimeInMillis(timestamp);

        String strTime =
                _calendar.get(Calendar.HOUR_OF_DAY) + ":" +
                _calendar.get(Calendar.MINUTE) + ":" +
                _calendar.get(Calendar.SECOND);

        return strTime;
    }



    private String getDateString(long timestamp) {
        _calendar.setTimeInMillis(timestamp);
        return _dateFormat.format(_calendar.getTime());
    }
}
