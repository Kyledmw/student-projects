package com.hms.devices.view.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hms_app.R;
import com.hms.common.view.models.MenuItem;

import java.util.List;

/**
 * Created by alan on 31/03/16.
 */
public class DashboardAdapter extends ArrayAdapter<MenuItem> {

    public DashboardAdapter(Context context, List<MenuItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MenuItem item = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.dash_menu_item, parent, false);
        }

        ImageView itemIcon = (ImageView)convertView.findViewById(R.id.menu_icon);
        TextView itemText = (TextView) convertView.findViewById(R.id.menu_item_text);

        itemIcon.setImageResource(item.getIconId());
        itemText.setText(item.getMenuText());

        return convertView;
    }
}
