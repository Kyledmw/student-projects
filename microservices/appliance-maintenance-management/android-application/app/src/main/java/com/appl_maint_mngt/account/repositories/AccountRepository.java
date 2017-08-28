package com.appl_maint_mngt.account.repositories;

import com.appl_maint_mngt.account.models.AAccount;
import com.appl_maint_mngt.account.models.Account;
import com.appl_maint_mngt.account.models.interfaces.IAccountReadable;
import com.appl_maint_mngt.account.models.web.IAuthDetails;
import com.appl_maint_mngt.account.models.web.IJwtToken;
import com.appl_maint_mngt.account.models.web.IUserProfileReadable;
import com.appl_maint_mngt.account.repositories.constants.IAccountObserverUpdateTypes;

/**
 * Created by Kyle on 07/04/2017.
 */

public class AccountRepository extends AAccountRepository {

    private AAccount accountModel;

    public AccountRepository() {
        accountModel = new Account();
    }

    @Override
    public IAccountReadable get() {
        return accountModel;
    }

    @Override
    public void update(AAccount account) {
        accountModel = account;
    }

    @Override
    public void updateToken(IJwtToken token) {
        accountModel.setToken(token.getToken());
        updateObservers(IAccountObserverUpdateTypes.TOKEN_UPDATE);
    }

    @Override
    public void updateAuth(IAuthDetails authDetails) {
        accountModel.setEmail(authDetails.getEmail());
        accountModel.setUserType(authDetails.getUserType());
        accountModel.setId(authDetails.getId());
        updateObservers(IAccountObserverUpdateTypes.AUTH_UPDATE);
    }

    @Override
    public void updateProfile(IUserProfileReadable userProfile) {
        accountModel.setFirstName(userProfile.getFirstName());
        accountModel.setSurname(userProfile.getSurname());
        accountModel.setDateOfBirth(userProfile.getDateOfBirth());
        updateObservers(IAccountObserverUpdateTypes.PROFILE_UPDATE);
    }

    private void updateObservers(String updateType) {
        setChanged();
        notifyObservers(updateType);
        hasChanged();
    }
}
