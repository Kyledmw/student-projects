package com.appl_maint_mngt.property.controller.interfaces;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;

import java.util.List;

/**
 * Created by Kyle on 07/04/2017.
 */

public interface IPropertyController {

    void getProperties(List<Long> ids, IErrorCallback callback);

    void getProperty(Long id, IErrorCallback callback);
}
