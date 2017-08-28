package com.appl_maint_mngt.maintenance_engineer.services;

import android.content.Context;
import android.preference.PreferenceActivity;

import com.appl_maint_mngt.MainActivity;
import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.errors.ErrorPayloadBuilder;
import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.maintenance_engineer.constants.web.IMaintenanceEngineerWebResources;
import com.appl_maint_mngt.maintenance_engineer.models.MaintenanceEngineer;
import com.appl_maint_mngt.maintenance_engineer.repositories.interfaces.IMaintenanceEngineerUpdateableRepository;
import com.appl_maint_mngt.maintenance_engineer.services.interfaces.IMaintenanceEngineerService;
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

public class MaintenanceEngineerService implements IMaintenanceEngineerService {

    private static final Logger logger = LoggerManager.getLogger(MaintenanceEngineerService.class);

    private AsyncHttpClient httpClient;
    private Context context;

    private IMaintenanceEngineerUpdateableRepository repository;

    public MaintenanceEngineerService() {
        httpClient = new AsyncHttpClient();
        context = MainActivity.getInstance();

        repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getMaintenanceEngineerRepository();
    }

    @Override
    public void get(Long id, final IErrorCallback errorCallback) {
        logger.i("Entered MaintenanceEngineer get()");
        httpClient.get(IMaintenanceEngineerWebResources.GET_RESOURCE + id, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("MaintenanceEngineer get onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonBuilder().create();
                Type responseType = new TypeToken<MaintenanceEngineer>(){}.getType();
                MaintenanceEngineer maintenanceEngineer = gson.fromJson(response.toString(), responseType);
                repository.update(maintenanceEngineer);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i(throwable, "MaintenanceEngineer post onFailure(). statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.maintenance_engineer_error_get)));
            }
        });
    }
}
