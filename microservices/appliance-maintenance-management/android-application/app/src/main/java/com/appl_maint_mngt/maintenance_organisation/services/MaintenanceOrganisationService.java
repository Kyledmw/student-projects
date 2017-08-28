package com.appl_maint_mngt.maintenance_organisation.services;

import android.content.Context;

import com.appl_maint_mngt.MainActivity;
import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.errors.ErrorPayloadBuilder;
import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.common.utility.EmbeddedJsonExtractor;
import com.appl_maint_mngt.common.utility.GsonFactory;
import com.appl_maint_mngt.maintenance_organisation.constants.web.IMaintenanceOrganisationWebResources;
import com.appl_maint_mngt.maintenance_organisation.models.MaintenanceOrganisation;
import com.appl_maint_mngt.maintenance_organisation.repositories.interfaces.IMaintenanceOrganisationUpdateableRepository;
import com.appl_maint_mngt.maintenance_organisation.services.interfaces.IMaintenanceOrganisationService;
import com.google.gson.Gson;
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
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Kyle on 09/04/2017.
 */

public class MaintenanceOrganisationService implements IMaintenanceOrganisationService {

    private static final Logger logger = LoggerManager.getLogger(MaintenanceOrganisationService.class);

    private AsyncHttpClient httpClient;
    private Context context;

    private IMaintenanceOrganisationUpdateableRepository repository;

    public MaintenanceOrganisationService() {
        httpClient = new AsyncHttpClient();
        context = MainActivity.getInstance();

        repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getMaintenanceOrganisationRepository();
    }

    @Override
    public void getAll(final IErrorCallback errorCallback) {
        httpClient.get(IMaintenanceOrganisationWebResources.GET_RESOURCE, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("MaintenanceOrganisation getAll onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonFactory().createDateFormattingGson();
                String data = "";
                try {
                    data = new EmbeddedJsonExtractor().extractArray(response).toString();
                } catch (JSONException e) {
                    errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.common_error_unexpected)));
                    return;
                }
                Type responseType = new TypeToken<List<MaintenanceOrganisation>>(){}.getType();
                List<MaintenanceOrganisation> maintenanceOrganisations = gson.fromJson(data, responseType);
                repository.addItems(maintenanceOrganisations);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i(throwable, "MaintenanceOrganisation getAll onFailure(). statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.maintenance_organisation_error_get)));
            }
        });
    }
}
