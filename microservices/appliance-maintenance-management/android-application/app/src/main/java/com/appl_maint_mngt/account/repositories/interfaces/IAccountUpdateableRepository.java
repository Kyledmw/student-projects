package com.appl_maint_mngt.account.repositories.interfaces;

import com.appl_maint_mngt.account.models.AAccount;
import com.appl_maint_mngt.account.models.web.IAuthDetails;
import com.appl_maint_mngt.account.models.web.IJwtToken;
import com.appl_maint_mngt.account.models.web.IUserProfileReadable;

/**
 * Created by Kyle on 07/04/2017.
 */

public interface IAccountUpdateableRepository {
    void update(AAccount account);

    void updateToken(IJwtToken token);

    void updateAuth(IAuthDetails authDetails);

    void updateProfile(IUserProfileReadable userProfile);
}
