package com.appl_maint_mngt.common.utility.interfaces;

import java.util.List;

/**
 * Created by Kyle on 16/04/2017.
 */

public interface IRequestParamGenerator<T> {

    String generateIDListRequestParams(List<T> ids, String paramName);
}
