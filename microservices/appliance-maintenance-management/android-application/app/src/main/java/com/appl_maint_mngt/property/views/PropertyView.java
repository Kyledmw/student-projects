package com.appl_maint_mngt.property.views;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.address.utility.AddressDisplayUtility;
import com.appl_maint_mngt.property.models.interfaces.IPropertyReadable;
import com.appl_maint_mngt.property.views.interfaces.IPropertyView;
import com.appl_maint_mngt.property_appliance.models.interfaces.IPropertyApplianceReadable;
import com.appl_maint_mngt.property_appliance.views.utility.PropertyApplianceListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Created by Kyle on 08/04/2017.
 */

public class PropertyView implements IPropertyView {

    private TextView addrLineTv;
    private TextView addrCityTv;
    private TextView addrStateTv;
    private TextView addrCountryTv;
    private TextView addrPostalCodeTv;

    private TextView ageTv;
    private TextView bedCountTv;
    private TextView bathroomCountTv;

    private ListView propertyAppliancesLV;
    private PropertyApplianceListAdapter propertyApplianceListAdapter;

    public PropertyView(Activity parent) {
        addrLineTv = (TextView) parent.findViewById(R.id.property_addr_textview_addrline);
        addrCityTv = (TextView) parent.findViewById(R.id.property_addr_textview_city);
        addrStateTv = (TextView) parent.findViewById(R.id.property_addr_textview_state);
        addrCountryTv = (TextView) parent.findViewById(R.id.property_addr_textview_country);
        addrPostalCodeTv = (TextView) parent.findViewById(R.id.property_addr_textview_postalcode);

        ageTv = (TextView) parent.findViewById(R.id.property_details_textview_age);
        bedCountTv = (TextView) parent.findViewById(R.id.property_details_textview_bedcount);
        bathroomCountTv = (TextView) parent.findViewById(R.id.property_details_textview_bathroomcount);
        propertyAppliancesLV = (ListView) parent.findViewById(R.id.property_appliances_listview_list);
        propertyApplianceListAdapter = new PropertyApplianceListAdapter(parent, new ArrayList<IPropertyApplianceReadable>());
        propertyAppliancesLV.setAdapter(propertyApplianceListAdapter);
    }


    @Override
    public void update(IPropertyReadable property) {
        addrLineTv.setText(new AddressDisplayUtility().singleAddressLine(property.getAddress()));
        addrCityTv.setText(property.getAddress().getCity());
        addrStateTv.setText(property.getAddress().getState());
        addrCountryTv.setText(property.getAddress().getCountry());
        addrPostalCodeTv.setText(property.getAddress().getPostalCode());

        ageTv.setText(String.format(Locale.ENGLISH, "%d", property.getAge()));
        bedCountTv.setText(String.format(Locale.ENGLISH, "%d", property.getBedCount()));
        bathroomCountTv.setText(String.format(Locale.ENGLISH, "%d", property.getBathroomCount()));
    }

    @Override
    public void updatePropertyAppliances(List<IPropertyApplianceReadable> list) {
        propertyApplianceListAdapter.clear();
        propertyApplianceListAdapter.addAll(list);
        propertyApplianceListAdapter.notifyDataSetChanged();
    }

    @Override
    public void propertyApplianceOnItemClickListener(AdapterView.OnItemClickListener listener) {
        propertyAppliancesLV.setOnItemClickListener(listener);
    }
}
