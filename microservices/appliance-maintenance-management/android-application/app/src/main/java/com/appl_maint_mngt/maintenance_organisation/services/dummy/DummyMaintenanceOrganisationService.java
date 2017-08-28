package com.appl_maint_mngt.maintenance_organisation.services.dummy;

import com.appl_maint_mngt.address.models.Address;
import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.maintenance_organisation.models.MaintenanceOrganisation;
import com.appl_maint_mngt.maintenance_organisation.repositories.interfaces.IMaintenanceOrganisationUpdateableRepository;
import com.appl_maint_mngt.maintenance_organisation.services.interfaces.IMaintenanceOrganisationService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 09/04/2017.
 */

public class DummyMaintenanceOrganisationService implements IMaintenanceOrganisationService {
    @Override
    public void getAll(IErrorCallback callback) {
        IMaintenanceOrganisationUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getMaintenanceOrganisationRepository();
        List<MaintenanceOrganisation> maintenanceOrganisationList = new ArrayList<>();

        MaintenanceOrganisation org1 = new MaintenanceOrganisation();
        org1.setId((long) 1);
        org1.setAddress(createAddress());
        org1.setDescription("Description");
        org1.setName("Name");
        maintenanceOrganisationList.add(org1);

        repository.addItems(maintenanceOrganisationList);
    }
    private Address createAddress() {
        Address addr = new Address();
        addr.setAddressLine1("AddrLine1");
        addr.setAddressLine2("AddrLine2");
        addr.setCity("City");
        addr.setCountry("Country");
        addr.setLatitude(0);
        addr.setLongitude(0);
        addr.setPostalCode("0000");
        addr.setState("State");
        return addr;
    }
}
