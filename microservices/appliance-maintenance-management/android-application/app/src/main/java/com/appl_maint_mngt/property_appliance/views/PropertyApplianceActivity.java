package com.appl_maint_mngt.property_appliance.views;
;
import android.nfc.FormatException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.account.models.constants.UserType;
import com.appl_maint_mngt.account.models.interfaces.IAccountReadable;
import com.appl_maint_mngt.appliance.models.interfaces.IApplianceReadable;
import com.appl_maint_mngt.appliance_status.models.constants.StatusType;
import com.appl_maint_mngt.appliance_status.models.interfaces.IApplianceStatusReadable;
import com.appl_maint_mngt.common.errors.LoggingErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.common.views.ANFCActivity;
import com.appl_maint_mngt.diagnostic_report.models.interfaces.IDiagnosticReportReadable;
import com.appl_maint_mngt.diagnostic_report.views.utility.DiagnosticReportIntentBuilder;
import com.appl_maint_mngt.property_appliance.models.interfaces.IPropertyApplianceReadable;
import com.appl_maint_mngt.property_appliance.views.constants.IPropertyApplianceViewConstants;
import com.appl_maint_mngt.property_appliance.views.interfaces.IPropertyApplianceView;
import com.appl_maint_mngt.property_appliance_status_update.utility.PropertyApplianceStatusUpdateHandler;
import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import java.io.IOException;
import java.util.Observable;

public class PropertyApplianceActivity extends ANFCActivity {
    private static final Logger logger = LoggerManager.getLogger(PropertyApplianceActivity.class);

    private Long propertyApplianceId;
    private IPropertyApplianceView propertyApplianceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_appliance);
        logger.i("Creating PropertyApplianceActivity");

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            this.propertyApplianceId = extras.getLong(IPropertyApplianceViewConstants.ID_KEY);
            logger.i("PropertyApplianceActivity for ID: %d", propertyApplianceId);
            boolean update = extras.getBoolean(IPropertyApplianceViewConstants.UPDATE_KEY, false);
            if(update) {
                IPropertyApplianceReadable propAppl = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getPropertyApplianceRepository().getForId(propertyApplianceId);
                new PropertyApplianceStatusUpdateHandler(this).handle(propAppl);
            }
        }

        propertyApplianceView = new PropertyApplianceView(this);
        propertyApplianceView.setDiagnosticReportsOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IDiagnosticReportReadable diagnosticReport = (IDiagnosticReportReadable) parent.getItemAtPosition(position);
                startActivity(new DiagnosticReportIntentBuilder().build(PropertyApplianceActivity.this, diagnosticReport.getId()));
            }
        });

        propertyApplianceView.setSetupTagOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    writeToTag(String.valueOf(propertyApplianceId));
                    Toast.makeText(PropertyApplianceActivity.this, R.string.nfc_label_action_write_success, Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    logger.e(e, "IOException on NFC Write: ID: %d", propertyApplianceId);
                    Toast.makeText(PropertyApplianceActivity.this, R.string.nfc_err_action_cant_write, Toast.LENGTH_LONG).show();
                } catch (FormatException e) {
                    logger.e(e, "FormatException on NFC Write: ID: %d", propertyApplianceId);
                    Toast.makeText(PropertyApplianceActivity.this, R.string.nfc_err_action_cant_write, Toast.LENGTH_LONG).show();
                } catch (NullPointerException e) {
                    logger.e(e, "FormatException on NFC Write: ID: %d", propertyApplianceId);
                    Toast.makeText(PropertyApplianceActivity.this, R.string.nfc_err_action_cant_write, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void updateModels() {
        if(propertyApplianceId != null) {
            IPropertyApplianceReadable propertyAppliance = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getPropertyApplianceRepository().getForId(propertyApplianceId);
            IntegrationController.getInstance().getControllerFactory().createPropertyApplianceController().getPropertyAppliancesForProperty(propertyAppliance.getPropertyId(), new LoggingErrorCallback());
            IntegrationController.getInstance().getControllerFactory().createApplianceController().getForId(propertyAppliance.getApplianceId(), new LoggingErrorCallback());
            IntegrationController.getInstance().getControllerFactory().createDiagnosticReportController().getForPropertyAppliance(propertyApplianceId, new LoggingErrorCallback());
        }
    }

    @Override
    protected void startObserving() {
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observePropertyApplianceRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observeApplianceRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryObserverHandler().observeDiagnosticReportRepository(this);
    }

    @Override
    protected void stopObserving() {
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObservePropertyApplianceRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObserveApplianceRepository(this);
        IntegrationController.getInstance().getRepositoryController().getRepositoryUnObserveHandler().unObserveDiagnosticReportRepository(this);
    }

    @Override
    protected void updateView() {
        IPropertyApplianceReadable propertyAppliance = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getPropertyApplianceRepository().getForId(propertyApplianceId);
        IApplianceStatusReadable status = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getApplianceStatusRepository().getForId(propertyAppliance.getStatusId());
        propertyApplianceView.update(propertyAppliance);
        if(status != null) {
            propertyApplianceView.updateStatus(status);
        }

        IApplianceReadable appliance = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getApplianceRepository().get(propertyAppliance.getApplianceId());
        if(appliance != null) {
            propertyApplianceView.updateAppliance(appliance);
        }

        IAccountReadable account = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getAccountRepository().get();
        if(account.getUserType().equals(UserType.PROPERTY_MANAGER)) {
            disableNFCRead();
            propertyApplianceView.displaySetupTagButton();
            propertyApplianceView.hideGenerateDiagnosticReportButton();
            if(appliance != null) {
                if(!status.getType().equals(StatusType.OKAY) && !status.getType().equals(StatusType.REPAIRING)) {
                    propertyApplianceView.displayGenerateDiagnosticReportButton();
                }
            }
        } else {
            propertyApplianceView.hideSetupTagButton();
            propertyApplianceView.hideGenerateDiagnosticReportButton();
        }
        if(account.getUserType().equals(UserType.PROPERTY_TENANT)) {
            propertyApplianceView.hideDiagnosticReports();
        }
        propertyApplianceView.updateDiagnosticReports(IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getDiagnosticReportRepository().getAllForPropertyAppliance(propertyApplianceId));
    }

    @Override
    public void update(Observable o, Object arg) {
        logger.i("Received update from observable");
        updateView();
    }

    @Override
    protected void onStop() {
        super.onStop();
        enableNFCRead();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        enableNFCRead();
    }
}
