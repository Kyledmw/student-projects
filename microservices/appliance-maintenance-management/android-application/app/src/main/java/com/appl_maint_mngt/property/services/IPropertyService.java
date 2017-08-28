package com.appl_maint_mngt.property.services;

import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;

import java.util.List;

/**
 * Created by Kyle on 08/04/2017.
 */

public interface IPropertyService {

    void findByIds(List<Long> ids, IErrorCallback callback);

    void get(Long id, IErrorCallback callback);
}
