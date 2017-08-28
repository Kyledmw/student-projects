package com.appl_maint_mngt.common.utility.interfaces;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kyle on 16/04/2017.
 */

public interface IEmbeddedJsonExtractor {

    JSONArray extractArray(JSONObject json) throws JSONException;
    JSONObject extractObject(JSONObject json) throws JSONException;
}
