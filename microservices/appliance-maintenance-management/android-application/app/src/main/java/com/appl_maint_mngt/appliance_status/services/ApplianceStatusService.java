package com.appl_maint_mngt.appliance_status.services;

import android.content.Context;

import com.appl_maint_mngt.MainActivity;
import com.appl_maint_mngt.R;
import com.appl_maint_mngt.appliance_status.constants.web.IApplianceStatusWebResources;
import com.appl_maint_mngt.appliance_status.models.ApplianceStatus;
import com.appl_maint_mngt.appliance_status.repositories.interfaces.IApplianceStatusUpdateableRepository;
import com.appl_maint_mngt.appliance_status.services.interfaces.IApplianceStatusService;
import com.appl_maint_mngt.common.errors.ErrorPayloadBuilder;
import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.common.utility.EmbeddedJsonExtractor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Kyle on 16/04/2017.
 */

public class ApplianceStatusService implements IApplianceStatusService {

    private static final Logger logger = LoggerManager.getLogger(ApplianceStatusService.class);

    private AsyncHttpClient httpClient;
    private Context context;

    private IApplianceStatusUpdateableRepository repository;

    public ApplianceStatusService() {
        httpClient = new AsyncHttpClient();
        context = MainActivity.getInstance();

        repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getApplianceStatusRepository();
    }

    @Override
    public void get(final IErrorCallback errorCallback) {
        httpClient.get(IApplianceStatusWebResources.GET_RESOURCE, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("ApplianceStatus get onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonBuilder().create();
                String data = "";
                try {
                    data = new EmbeddedJsonExtractor().extractArray(response).toString();
                } catch (JSONException e) {
                    errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.common_error_unexpected)));
                    return;
                }
                Type responseType = new TypeToken<List<ApplianceStatus>>(){}.getType();
                List<ApplianceStatus> applianceStatuses = gson.fromJson(data, responseType);
                repository.addItems(applianceStatuses);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i(throwable, "ApplianceStatus get onFailure(). {statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.appliance_status_error_get)));
            }

        });
    }
}
