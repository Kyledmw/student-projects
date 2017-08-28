package com.appl_maint_mngt.property_manager.services;

import android.content.Context;

import com.appl_maint_mngt.MainActivity;
import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.errors.ErrorPayloadBuilder;
import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.property_manager.constants.web.IPropertyManagerWebResources;
import com.appl_maint_mngt.property_manager.models.PropertyManager;
import com.appl_maint_mngt.property_manager.repositories.interfaces.IPropertyManagerUpdateableRepository;
import com.appl_maint_mngt.property_manager.services.interfaces.IPropertyManagerService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import org.json.JSONObject;

import java.lang.reflect.Type;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Kyle on 16/04/2017.
 */

public class PropertyManagerService implements IPropertyManagerService {

    private static final Logger logger = LoggerManager.getLogger(PropertyManagerService.class);

    private AsyncHttpClient httpClient;
    private Context context;

    private IPropertyManagerUpdateableRepository repository;

    public PropertyManagerService() {
        httpClient = new AsyncHttpClient();
        context = MainActivity.getInstance();

        repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getPropertyManagerRepository();
    }

    @Override
    public void get(Long accountId, final IErrorCallback errorCallback) {
        logger.i("Entered PropertyManager get()");
        httpClient.get(IPropertyManagerWebResources.GET_RESOURCE + accountId, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("PropertyManager get onSuccess()");
                Gson gson = new GsonBuilder().create();
                Type responseType = new TypeToken<PropertyManager>(){}.getType();
                PropertyManager propertyManager = gson.fromJson(response.toString(), responseType);
                repository.update(propertyManager);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i(throwable, "PropertyTenant get() onFailure(). statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.property_manager_error_get)));
            }
        });
    }
}
