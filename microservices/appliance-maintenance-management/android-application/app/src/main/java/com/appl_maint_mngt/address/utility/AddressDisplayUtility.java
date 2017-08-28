package com.appl_maint_mngt.address.utility;

import com.appl_maint_mngt.address.models.interfaces.IAddressReadable;
import com.appl_maint_mngt.common.constants.ICommonConstants;

/**
 * Created by Kyle on 08/04/2017.
 */

public class AddressDisplayUtility implements IAddressDisplayUtility {

    @Override
    public String singleAddressLine(IAddressReadable address) {
        StringBuilder builder = new StringBuilder();
        builder.append(address.getAddressLine1());
        builder.append(ICommonConstants.COMMA);
        builder.append(ICommonConstants.BLANK_SPACE);
        builder.append(address.getAddressLine2());
        return builder.toString();
    }
}
