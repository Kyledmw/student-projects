package com.appl_maint_mngt.property_manager.repositories;

import com.appl_maint_mngt.property_manager.repositories.interfaces.IPropertyManagerReadableRepository;
import com.appl_maint_mngt.property_manager.repositories.interfaces.IPropertyManagerUpdateableRepository;

import java.util.Observable;

/**
 * Created by Kyle on 17/03/2017.
 */

public abstract class APropertyManagerRepository extends Observable implements IPropertyManagerReadableRepository, IPropertyManagerUpdateableRepository {
}
