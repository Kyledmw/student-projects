package com.appl_maint_mngt.account.services.dummy;

import com.appl_maint_mngt.account.events.IUserProfileEvents;
import com.appl_maint_mngt.account.models.web.UserProfile;
import com.appl_maint_mngt.account.repositories.interfaces.IAccountUpdateableRepository;
import com.appl_maint_mngt.account.services.interfaces.IUserProfileService;
import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.events.ApplicationEventBus;
import com.appl_maint_mngt.common.integration.IntegrationController;

import java.sql.Timestamp;

/**
 * Created by Kyle on 07/04/2017.
 */

public class DummyUserProfileService implements IUserProfileService {

    @Override
    public void get(Long id, IErrorCallback errorCallback) {
        IAccountUpdateableRepository repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getAccountRepository();
        UserProfile profile = new UserProfile();
        profile.setFirstName("FirstName");
        profile.setSurname("Surname");
        profile.setDateOfBirth(new Timestamp(System.currentTimeMillis()));
        repository.updateProfile(profile);
        ApplicationEventBus.getInstance().sendEvent(IUserProfileEvents.GET_EVENT);
    }
}
