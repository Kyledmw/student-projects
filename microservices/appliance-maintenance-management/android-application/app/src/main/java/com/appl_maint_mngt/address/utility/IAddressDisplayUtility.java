package com.appl_maint_mngt.address.utility;

import com.appl_maint_mngt.address.models.interfaces.IAddressReadable;

/**
 * Created by Kyle on 08/04/2017.
 */

public interface IAddressDisplayUtility {

    String singleAddressLine(IAddressReadable address);
}
