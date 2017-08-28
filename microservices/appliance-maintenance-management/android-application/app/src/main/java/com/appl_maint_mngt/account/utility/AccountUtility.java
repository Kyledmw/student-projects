package com.appl_maint_mngt.account.utility;

import com.appl_maint_mngt.account.models.constants.UserType;
import com.appl_maint_mngt.account.utility.interfaces.IAccountUtility;
import com.appl_maint_mngt.common.integration.IntegrationController;

/**
 * Created by Kyle on 08/04/2017.
 */

public class AccountUtility implements IAccountUtility {
    @Override
    public UserType getAccountUserType() {
        return IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getAccountRepository().get().getUserType();
    }
}
