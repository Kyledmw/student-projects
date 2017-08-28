package com.appl_maint_mngt.property.repositories.interfaces;


import com.appl_maint_mngt.property.models.interfaces.IPropertyReadable;

import java.util.List;

/**
 * Created by Kyle on 17/03/2017.
 */

public interface IPropertyReadableRepository {

    List<IPropertyReadable> getAll();

    IPropertyReadable getForId(Long id);
}
