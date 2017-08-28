package com.appl_maint_mngt.property.services.dummy;

import com.appl_maint_mngt.address.models.Address;
import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.property.models.Property;
import com.appl_maint_mngt.property.repositories.interfaces.IPropertyUpdateableRepository;
import com.appl_maint_mngt.property.services.IPropertyService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 08/04/2017.
 */

public class DummyPropertyService implements IPropertyService {

    @Override
    public void findByIds(List<Long> ids, IErrorCallback callback) {
        IPropertyUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getPropertyRepository();

        List<Property> properties = new ArrayList<>();
        for(Long id : ids) {
            Property prop = new Property();
            prop.setId(id);
            prop.setAge(10);
            prop.setBathroomCount(2);
            prop.setBedCount(5);
            prop.setAddress(createAddress());
            properties.add(prop);
        }

        repository.addItems(properties);
    }

    @Override
    public void get(Long id, IErrorCallback callback) {
        IPropertyUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getPropertyRepository();
        Property prop = new Property();
        prop.setId(id);
        prop.setAge(10);
        prop.setBathroomCount(2);
        prop.setBedCount(5);
        prop.setAddress(createAddress());

        repository.addItem(prop);
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
