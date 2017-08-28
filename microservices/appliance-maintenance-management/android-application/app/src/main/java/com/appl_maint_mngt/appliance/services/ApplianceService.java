package com.appl_maint_mngt.appliance.services;

import android.content.Context;

import com.appl_maint_mngt.MainActivity;
import com.appl_maint_mngt.R;
import com.appl_maint_mngt.account.services.UserAuthService;
import com.appl_maint_mngt.appliance.constants.web.IApplianceWebResources;
import com.appl_maint_mngt.appliance.models.Appliance;
import com.appl_maint_mngt.appliance.repositories.interfaces.IApplianceUpdateableRepository;
import com.appl_maint_mngt.appliance.services.interfaces.IApplianceService;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Kyle on 16/04/2017.
 */
public class ApplianceService implements IApplianceService {

    private static final Logger logger = LoggerManager.getLogger(ApplianceService.class);

    private AsyncHttpClient httpClient;
    private Context context;

    private IApplianceUpdateableRepository repository;

    public ApplianceService() {
        httpClient = new AsyncHttpClient();
        context = MainActivity.getInstance();

        repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getApplianceRepository();
    }

    @Override
    public void get(String id, final IErrorCallback errorCallback) {
        logger.i("Entered get(): Id: %s", id);
        httpClient.get(IApplianceWebResources.GET_RESOURCE + id, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("Appliance get onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonBuilder().create();
                Type responseType = new TypeToken<Appliance>() {}.getType();
                Appliance appliance = gson.fromJson(response.toString(), responseType);
                repository.addItem(appliance);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i(throwable, "Appliance get onFailure(). {statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.appliance_error_get)));
            }

            @Override
            public void onFailure(int statusCode, Header[] h, String s, Throwable t) {
                logger.i(t, "Appliance get onFailure(). {statusCode: %d}", statusCode);
                if(s != null) logger.i("Response: %s", s);
            }
        });
    }

    @Override
    public void get(final IErrorCallback errorCallback) {
        logger.i("Entered getAll()");
        httpClient.get(IApplianceWebResources.GET_RESOURCE, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("Appliance getAll onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonBuilder().create();
                String data = "";
                try {
                    data = new EmbeddedJsonExtractor().extractArray(response).toString();
                } catch (JSONException e) {
                    errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.common_error_unexpected)));
                    return;
                }
                Type responseType = new TypeToken<List<Appliance>>(){}.getType();
                List<Appliance> appliances = gson.fromJson(data, responseType);
                repository.addItems(appliances);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i(throwable, "Appliance getAll onFailure(). {statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.appliance_error_get)));
            }

            @Override
            public void onFailure(int statusCode, Header[] h, String s, Throwable t) {
                logger.i(t, "Appliance getAll onFailure(). {statusCode: %d}", statusCode);
                if(s != null) logger.i("Response: %s", s);
            }
        });
    }
}
