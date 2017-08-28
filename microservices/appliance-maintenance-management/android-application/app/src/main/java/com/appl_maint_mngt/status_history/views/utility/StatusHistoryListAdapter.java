package com.appl_maint_mngt.status_history.views.utility;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.appliance_status.models.ApplianceStatus;
import com.appl_maint_mngt.appliance_status.models.interfaces.IApplianceStatusReadable;
import com.appl_maint_mngt.appliance_status.repositories.interfaces.IApplianceStatusReadableRepository;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.common.utility.TimestampFormatter;
import com.appl_maint_mngt.common.utility.interfaces.ITimestampFormatter;
import com.appl_maint_mngt.common.views.ACommonListAdapter;
import com.appl_maint_mngt.status_history.models.interfaces.IStatusHistoryReadable;

import java.util.List;

/**
 * Created by Kyle on 08/04/2017.
 */

public class StatusHistoryListAdapter extends ACommonListAdapter<IStatusHistoryReadable> {

    private IApplianceStatusReadableRepository applianceStatusReadableRepository;
    private ITimestampFormatter timestampFormatter;

    public StatusHistoryListAdapter(@NonNull Context context, @NonNull List<IStatusHistoryReadable> objects) {
        super(context, objects);
        applianceStatusReadableRepository = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getApplianceStatusRepository();
        timestampFormatter = new TimestampFormatter();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IStatusHistoryReadable statusHistory = getItem(position);

        convertView = prepareConvertView(convertView, parent, R.layout.listitem_status_history);

        TextView nameTV = (TextView) convertView.findViewById(R.id.listitem_statushistory_name);
        TextView timeTV = (TextView) convertView.findViewById(R.id.listitem_statushistory_time);

        IApplianceStatusReadable status = applianceStatusReadableRepository.getForId(statusHistory.getStatusId());

        if(status != null) {
            nameTV.setText(status.getType().toString());
        }
        timeTV.setText(timestampFormatter.format(statusHistory.getLoggedTime()));

        return convertView;
    }
}
