package com.appl_maint_mngt.common.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Kyle on 08/04/2017.
 */

public abstract class ACommonListAdapter<T> extends ArrayAdapter<T> {
    public ACommonListAdapter(@NonNull Context context, @NonNull List<T> objects) {
        super(context, 0, objects);
    }

    protected View prepareConvertView(View convertView, ViewGroup parent, int layoutId) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
        }
        return convertView;
    }

    public abstract View getView(int position, View convertView, ViewGroup parent);
}
