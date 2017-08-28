package com.appl_maint_mngt.property_appliance.services.dummy;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.property_appliance.models.PropertyAppliance;
import com.appl_maint_mngt.property_appliance.repositories.interfaces.IPropertyApplianceUpdateableRepository;
import com.appl_maint_mngt.property_appliance.services.interfaces.IPropertyApplianceService;
import com.appl_maint_mngt.status_history.models.StatusHistory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 08/04/2017.
 */

public class DummyPropertyApplianceService implements IPropertyApplianceService {
    @Override
    public void get(Long id, IErrorCallback errorCallback) {
        IPropertyApplianceUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getPropertyApplianceRepository();

        PropertyAppliance propertyAppliance1 = new PropertyAppliance();
        propertyAppliance1.setId(id);
        propertyAppliance1.setApplianceId("1");
        propertyAppliance1.setStatusId((long) 1);
        propertyAppliance1.setPropertyId((long) 1);
        propertyAppliance1.setAge(5);
        propertyAppliance1.setName("Name");
        propertyAppliance1.setStatusHistory(new ArrayList<StatusHistory>());

        repository.addItem(propertyAppliance1);
    }

    @Override
    public void findByPropertyId(Long propertyId, IErrorCallback callback) {
        IPropertyApplianceUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getPropertyApplianceRepository();
        List<PropertyAppliance> propertyApplianceList = new ArrayList<>();

        PropertyAppliance propertyAppliance1 = new PropertyAppliance();
        propertyAppliance1.setId((long) 1);
        propertyAppliance1.setApplianceId("1");
        propertyAppliance1.setStatusId((long) 1);
        propertyAppliance1.setPropertyId((long) 1);
        propertyAppliance1.setAge(5);
        propertyAppliance1.setName("Name");
        propertyAppliance1.setStatusHistory(new ArrayList<StatusHistory>());

        propertyApplianceList.add(propertyAppliance1);
        repository.addItems(propertyApplianceList);
    }

    @Override
    public void findByPropertyIds(List<Long> propertyIds, IErrorCallback callback) {
        IPropertyApplianceUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getPropertyApplianceRepository();
        List<PropertyAppliance> propertyApplianceList = new ArrayList<>();
        long counter = 1;
        for(Long id : propertyIds) {
            PropertyAppliance propertyAppliance = new PropertyAppliance();
            propertyAppliance.setId(counter);
            propertyAppliance.setPropertyId(id);
            propertyAppliance.setApplianceId("1");
            propertyAppliance.setStatusId((long) 1);
            propertyAppliance.setAge(5);
            propertyAppliance.setName("Name");
            propertyAppliance.setStatusHistory(new ArrayList<StatusHistory>());
            propertyApplianceList.add(propertyAppliance);
            counter++;
        }
        repository.addItems(propertyApplianceList);
    }
}
