package com.hms.sensors.motion.view.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hms_app.R;
import com.hms.sensors.motion.model.interfaces.IMotionDetectionReadable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.SimpleTimeZone;

/**
 * Created by alan on 03/04/16.
 */
public class MotionDetectionAdapter extends ArrayAdapter<IMotionDetectionReadable>{

    private Calendar _calendar;
    private SimpleDateFormat _dateFormat;

    public MotionDetectionAdapter(Context context, List<IMotionDetectionReadable> motionDetectionData) {
        super(context, 0, motionDetectionData);
        _calendar = new GregorianCalendar(SimpleTimeZone.getTimeZone("GMT"));
        _dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IMotionDetectionReadable motionDetectionReading = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.motion_detection_adapter_view, parent, false);
        }


        ImageView icon = (ImageView)convertView.findViewById(R.id.motion_detection_icon_iv);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.motion_detection_image);

        TextView motionDetectedTv = (TextView)convertView.findViewById(R.id.motion_detection_value_tv);
        TextView motionDetectedTypeTv = (TextView)convertView.findViewById(R.id.motion_detection_measurement_type_tv);
        TextView timestampTv = (TextView)convertView.findViewById(R.id.motion_detection_timestamp_tv);
        TextView dateTv = (TextView)convertView.findViewById(R.id.motion_detection_date_tv);

        icon.setImageResource(R.drawable.menu_item_motion_detected_100);
        motionDetectedTv.setText("Motion Detected");
        motionDetectedTypeTv.setVisibility(View.GONE);

        long timestamp = motionDetectionReading.getTimestampSeconds();
        String date = getDateString(timestamp);
        String time = getTimeString(timestamp);
        timestampTv.setText(time);
        dateTv.setText(date);



        if(motionDetectionReading.getCameraData() == null) {
            imageView.setVisibility(View.GONE);
        }
        else {
            Bitmap bmp = BitmapFactory.decodeByteArray(motionDetectionReading.getCameraData().getImageBytes(), 0,
                    motionDetectionReading.getCameraData().getImageBytes().length);
            imageView.setImageBitmap(bmp);
        }


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
