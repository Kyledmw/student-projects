package com.appl_maint_mngt.property_manager.controllers.interfaces;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;

/**
 * Created by Kyle on 07/04/2017.
 */

public interface IPropertyManagerController {

    void getPropertyManager(Long accountId, IErrorCallback errorCallback);
}
