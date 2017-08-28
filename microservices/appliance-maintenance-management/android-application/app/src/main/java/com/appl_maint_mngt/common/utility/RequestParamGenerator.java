package com.appl_maint_mngt.common.utility;

import com.appl_maint_mngt.common.constants.ICommonConstants;
import com.appl_maint_mngt.common.utility.interfaces.IRequestParamGenerator;

import java.util.List;

/**
 * Created by Kyle on 16/04/2017.
 */

public class RequestParamGenerator<T> implements IRequestParamGenerator<T> {

    @Override
    public String generateIDListRequestParams(List<T> ids, String paramName) {
        StringBuilder builder = new StringBuilder();
        builder.append(ICommonConstants.QMARK);
        for(T id: ids) {
            builder.append(paramName);
            builder.append(ICommonConstants.EQUALS);
            builder.append(id);
            builder.append(ICommonConstants.AND);
        }
        return builder.toString();
    }
}
