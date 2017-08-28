package com.hms.devices.view.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hms_app.R;
import com.hms.devices.model.interfaces.IDeviceMovedReadable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.SimpleTimeZone;

/**
 * Created by alan on 03/04/16.
 */
public class DeviceMovedAdapter extends ArrayAdapter<IDeviceMovedReadable>{

    private Calendar _calendar;
    private SimpleDateFormat _dateFormat;

    public DeviceMovedAdapter(Context context, List<IDeviceMovedReadable> deviceMovedData) {
        super(context, 0, deviceMovedData);
        _calendar = new GregorianCalendar(SimpleTimeZone.getTimeZone("GMT"));
        _dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IDeviceMovedReadable deviceMovedReading = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.sensor_data_view, parent, false);
        }

        TextView dateHeaderTv = (TextView)convertView.findViewById(R.id.sensor_data_date_tv);
        ImageView deviceMovedIconIv = (ImageView)convertView.findViewById(R.id.data_type_icon_iv);
        TextView deviceMovedTimestampTv = (TextView)convertView.findViewById(R.id.data_timestamp_tv);
        TextView deviceMovedTv = (TextView)convertView.findViewById(R.id.data_value_tv);
        TextView deviceMovedTypeTv = (TextView)convertView.findViewById(R.id.sensor_data_measurement_type_tv);

        String strDate = getDateString(deviceMovedReading.getTimestampSeconds());
        String strTimestamp = getTimeString(deviceMovedReading.getTimestampSeconds());

        dateHeaderTv.setText(strDate);
        deviceMovedTv.setText("Device Moved");
        deviceMovedTypeTv.setVisibility(View.GONE);
        deviceMovedIconIv.setImageResource(R.drawable.menu_item_device_moved_100);
        deviceMovedTimestampTv.setText(strTimestamp);
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
