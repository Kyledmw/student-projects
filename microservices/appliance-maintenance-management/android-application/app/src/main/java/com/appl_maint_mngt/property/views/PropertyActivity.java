package com.appl_maint_mngt.property.views;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.errors.LoggingErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.common.views.ACommonActivity;
import com.appl_maint_mngt.common.views.ANFCActivity;
import com.appl_maint_mngt.property.models.interfaces.IPropertyReadable;
import com.appl_maint_mngt.property.repositories.constants.IPropertyObserverUpdateTypes;
import com.appl_maint_mngt.property.views.constants.IPropertyViewConstants;
import com.appl_maint_mngt.property.views.interfaces.IPropertyView;
import com.appl_maint_mngt.property_appliance.models.interfaces.IPropertyApplianceReadable;
import com.appl_maint_mngt.property_appliance.views.utility.PropertyApplianceIntentBuilder;
import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import java.util.Observable;

public class PropertyActivity extends ANFCActivity {
    private static final Logger logger = LoggerManager.getLogger(PropertyActivity.class);

    private Long propertyId;
    private IPropertyView propertyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);
        logger.i("Creating PropertyActivity");

        propertyView = new PropertyView(this);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
           this.propertyId = extras.getLong(IPropertyViewConstants.ID_KEY);
            logger.i("PropertyActivity for ID: %d", propertyId);
        }

        propertyView.propertyApplianceOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IPropertyApplianceReadable propertyApplianceReadable = (IPropertyApplianceReadable) parent.getItemAtPosition(position);
                startActivity(new PropertyApplianceIntentBuilder().build(PropertyActivity.this, propertyApplianceReadable.getId()));
            }
        });

        updateView();
    }

    @Override
    public void onResume() {
        super.onResume();
        logger.i("Entered onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        logger.i("Entered onPause()");
    }

    @Override
    public void updateModels() {
        if(propertyId != null) {
            IntegrationController.getInstance().getControllerFactory().createPropertyController().getProperty(propertyId, new LoggingErrorCallback());
            IntegrationController.getInstance().getControllerFactory().createPropertyApplianceController().getPropertyAppliancesForProperty(propertyId, new LoggingErrorCallback());
        }
    }

    @Override
    protected void startObserving() {
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observePropertyRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observePropertyApplianceRepository(this);
    }

    @Override
    protected void stopObserving() {
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObservePropertyRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObservePropertyApplianceRepository(this);
    }

    @Override
    protected void updateView() {
        logger.i("Updating View");
        IPropertyReadable property = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getPropertyRepository().getForId(propertyId);
        if(property != null) {
            propertyView.update(property);
            propertyView.updatePropertyAppliances(IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getPropertyApplianceRepository().getForPropertyId(property.getId()));
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        logger.i("Received update from observable");
        updateView();
        if(arg.equals(IPropertyObserverUpdateTypes.NEW_ITEM_UPDATE)) {
            logger.i("Entered Update: %s", IPropertyObserverUpdateTypes.NEW_ITEM_UPDATE);
        }
    }
}
