package com.appl_maint_mngt.property.views;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.account.models.constants.UserType;
import com.appl_maint_mngt.account.utility.AccountUtility;
import com.appl_maint_mngt.account.views.utility.AccountIntentBuilder;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.common.views.ACommonActivity;
import com.appl_maint_mngt.common.views.ANFCActivity;
import com.appl_maint_mngt.property.models.interfaces.IPropertyReadable;
import com.appl_maint_mngt.property.views.interfaces.IPropertyListView;
import com.appl_maint_mngt.property.views.utility.PropertyIntentBuilder;
import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import java.util.Observable;

public class PropertyListActivity extends ANFCActivity {
    private static final Logger logger = LoggerManager.getLogger(PropertyListActivity.class);

    private IPropertyListView propertyListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_list);
        if(!new AccountUtility().getAccountUserType().equals(UserType.PROPERTY_MANAGER)) {
            startActivity(new AccountIntentBuilder().buildLogin(this));
        }
        propertyListView = new PropertyListView(this);
        propertyListView.setPropertyOnClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                logger.i("OnItemSelect. Postion: %d", position);
                IPropertyReadable property = (IPropertyReadable) parent.getItemAtPosition(position);
                startActivity(new PropertyIntentBuilder().build(PropertyListActivity.this, property.getId()));
            }
        });
        updateView();
    }

    @Override
    public void updateModels() {
    }

    @Override
    protected void startObserving() {
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observePropertyRepository(this);
    }

    @Override
    protected void stopObserving() {
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObservePropertyRepository(this);
    }

    @Override
    protected void updateView() {
        logger.i("Updating View");
        propertyListView.update(IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getPropertyRepository().getAll());
    }

    @Override
    public void update(Observable o, Object arg) {
        logger.i("Received update from observable");
        updateView();
    }
}
