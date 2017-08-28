package com.appl_maint_mngt.property_tenant.services;

import android.content.Context;

import com.appl_maint_mngt.MainActivity;
import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.errors.ErrorPayloadBuilder;
import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.property_tenant.constants.web.IPropertyTenantWebResources;
import com.appl_maint_mngt.property_tenant.models.PropertyTenant;
import com.appl_maint_mngt.property_tenant.repositories.interfaces.IPropertyTenantUpdateableRepository;
import com.appl_maint_mngt.property_tenant.services.interfaces.IPropertyTenantService;
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

public class PropertyTenantService implements IPropertyTenantService {

    private static final Logger logger = LoggerManager.getLogger(PropertyTenantService.class);

    private AsyncHttpClient httpClient;
    private Context context;

    private IPropertyTenantUpdateableRepository repository;

    public PropertyTenantService() {
        httpClient = new AsyncHttpClient();
        context = MainActivity.getInstance();

        repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getPropertyTenantRepository();
    }

    @Override
    public void get(Long accountId, final IErrorCallback errorCallback) {
        logger.i("Entered PropertyTenant get()");
        httpClient.get(IPropertyTenantWebResources.GET_RESOURCE + accountId, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("PropertyTenant get onSuccess()");
                Gson gson = new GsonBuilder().create();
                Type responseType = new TypeToken<PropertyTenant>(){}.getType();
                PropertyTenant tenant = gson.fromJson(response.toString(), responseType);
                repository.update(tenant);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i(throwable, "PropertyTenant get() onFailure(). statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.property_tenant_error_get)));
            }
        });
    }
}
