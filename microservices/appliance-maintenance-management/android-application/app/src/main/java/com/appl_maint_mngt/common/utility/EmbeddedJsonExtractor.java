package com.appl_maint_mngt.common.utility;

import com.appl_maint_mngt.common.constants.web.IWebConstants;
import com.appl_maint_mngt.common.utility.interfaces.IEmbeddedJsonExtractor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kyle on 16/04/2017.
 */

public class EmbeddedJsonExtractor implements IEmbeddedJsonExtractor {

    @Override
    public JSONArray extractArray(JSONObject json) throws JSONException {
        return json.getJSONObject(IWebConstants.REST_REPO_EMBEDDED_KEY).getJSONArray(IWebConstants.REST_REPO_DATA_KEY);
    }

    @Override
    public JSONObject extractObject(JSONObject json) throws JSONException {
        return json.getJSONObject(IWebConstants.REST_REPO_EMBEDDED_KEY).getJSONObject(IWebConstants.REST_REPO_DATA_KEY);
    }
}
