package com.appl_maint_mngt.common.constants.web;

import com.appl_maint_mngt.common.constants.ICommonConstants;

import cz.msebera.android.httpclient.entity.ContentType;

/**
 * Created by Kyle on 16/04/2017.
 */

public interface IWebConstants {
    String EDGE_URL = "http://192.168.1.8";
    String EDGE_PORT = "8200";
    String API_RESOURCE = "/api";

    String API_URL = EDGE_URL + ICommonConstants.COLON + EDGE_PORT + API_RESOURCE;

    String CONTENT_TYPE_JSON = ContentType.APPLICATION_JSON.getMimeType();

    String REST_REPO_EMBEDDED_KEY = "_embedded";
    String REST_REPO_DATA_KEY = "data";
}
