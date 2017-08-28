package com.appl_maint_mngt.account.repositories;

import com.appl_maint_mngt.account.repositories.interfaces.IAccountReadableRepository;
import com.appl_maint_mngt.account.repositories.interfaces.IAccountUpdateableRepository;

import java.util.Observable;

/**
 * Created by Kyle on 07/04/2017.
 */

public abstract class AAccountRepository extends Observable implements IAccountReadableRepository, IAccountUpdateableRepository{
}
