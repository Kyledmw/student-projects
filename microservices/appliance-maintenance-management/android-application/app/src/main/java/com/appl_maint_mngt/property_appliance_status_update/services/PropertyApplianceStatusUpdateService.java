package com.appl_maint_mngt.property_appliance_status_update.services;

import android.content.Context;
import android.preference.PreferenceActivity;

import com.appl_maint_mngt.MainActivity;
import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.constants.web.IWebConstants;
import com.appl_maint_mngt.common.errors.ErrorPayloadBuilder;
import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.events.ApplicationEventBus;
import com.appl_maint_mngt.common.models.web.ApiResponse;
import com.appl_maint_mngt.property_appliance_status_update.constants.web.IPropertyApplianceStatusUpdateWebResources;
import com.appl_maint_mngt.property_appliance_status_update.events.constants.IPropertyApplianceStatusUpdateEvents;
import com.appl_maint_mngt.property_appliance_status_update.models.web.interfaces.IPropertyApplianceStatusUpdatePayload;
import com.appl_maint_mngt.property_appliance_status_update.services.interfaces.IPropertyApplianceStatusUpdateService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

/**
 * Created by Kyle on 16/04/2017.
 */

public class PropertyApplianceStatusUpdateService implements IPropertyApplianceStatusUpdateService {

    private static final Logger logger = LoggerManager.getLogger(PropertyApplianceStatusUpdateService.class);

    private AsyncHttpClient httpClient;
    private Context context;

    public PropertyApplianceStatusUpdateService() {
        httpClient = new AsyncHttpClient();
        context = MainActivity.getInstance();
    }

    @Override
    public void update(IPropertyApplianceStatusUpdatePayload payload, final IErrorCallback errorCallback) {
        ApplicationEventBus.getInstance().sendEvent(IPropertyApplianceStatusUpdateEvents.SUCCESS_EVENT);
        logger.i("Entered update");
        Gson gson = new Gson();
        StringEntity entity = null;
        try {
            entity = new StringEntity(gson.toJson(payload));
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, IWebConstants.CONTENT_TYPE_JSON));
        } catch (UnsupportedEncodingException e) {
            logger.e(e, "UnsupportedEncodingException: on %s", gson.toJson(payload));
            errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.common_error_unexpected)));
        }
        httpClient.post(context, IPropertyApplianceStatusUpdateWebResources.UPDATE_RESOURCE, entity, IWebConstants.CONTENT_TYPE_JSON, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("Update onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonBuilder().create();
                Type responseType = new TypeToken<ApiResponse<Boolean>>(){}.getType();
                ApiResponse<Boolean> apiResponse = gson.fromJson(response.toString(), responseType);
                logger.i("ApiResponse message: %s", apiResponse.getMessage());
                if(apiResponse.getData()) {
                    ApplicationEventBus.getInstance().sendEvent(IPropertyApplianceStatusUpdateEvents.SUCCESS_EVENT);
                } else {
                    ApplicationEventBus.getInstance().sendEvent(IPropertyApplianceStatusUpdateEvents.FAIL_EVENT);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i("update onFailure(). {statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.property_appliance_status_update_error_update)));
            }
        });
    }
}
