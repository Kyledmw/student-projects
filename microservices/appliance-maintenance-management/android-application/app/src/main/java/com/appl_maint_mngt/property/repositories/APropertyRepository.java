package com.appl_maint_mngt.property.repositories;

import com.appl_maint_mngt.property.repositories.interfaces.IPropertyReadableRepository;
import com.appl_maint_mngt.property.repositories.interfaces.IPropertyUpdateableRepository;

import java.util.Observable;

/**
 * Created by Kyle on 17/03/2017.
 */

public abstract class APropertyRepository extends Observable implements IPropertyReadableRepository, IPropertyUpdateableRepository {
}
