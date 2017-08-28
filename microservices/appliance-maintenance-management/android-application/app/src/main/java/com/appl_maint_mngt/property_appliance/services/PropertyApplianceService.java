package com.appl_maint_mngt.property_appliance.services;

import android.content.Context;

import com.appl_maint_mngt.MainActivity;
import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.errors.ErrorPayloadBuilder;
import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.common.utility.EmbeddedJsonExtractor;
import com.appl_maint_mngt.common.utility.GsonFactory;
import com.appl_maint_mngt.common.utility.RequestParamGenerator;
import com.appl_maint_mngt.diagnostic_report.models.DiagnosticReport;
import com.appl_maint_mngt.property.models.Property;
import com.appl_maint_mngt.property_appliance.constants.web.IPropertyApplianceWebConstants;
import com.appl_maint_mngt.property_appliance.constants.web.IPropertyApplianceWebResources;
import com.appl_maint_mngt.property_appliance.models.PropertyAppliance;
import com.appl_maint_mngt.property_appliance.repositories.interfaces.IPropertyApplianceUpdateableRepository;
import com.appl_maint_mngt.property_appliance.services.interfaces.IPropertyApplianceService;
import com.google.gson.Gson;
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
 * Created by Kyle on 17/04/2017.
 */

public class PropertyApplianceService implements IPropertyApplianceService {

    private static final Logger logger = LoggerManager.getLogger(PropertyApplianceService.class);

    private AsyncHttpClient httpClient;
    private Context context;

    private IPropertyApplianceUpdateableRepository repository;

    public PropertyApplianceService() {
        httpClient = new AsyncHttpClient();
        context = MainActivity.getInstance();

        repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getPropertyApplianceRepository();
    }

    @Override
    public void get(Long id, final IErrorCallback errorCallback) {
        logger.i("Entered PropertyAppliance get() for ID: %d", id);
        httpClient.get(IPropertyApplianceWebResources.GET_RESOURCE + id, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("PropertyAppliance get onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonFactory().createDateFormattingGson();
                Type responseType = new TypeToken<PropertyAppliance>(){}.getType();
                PropertyAppliance propertyAppliance = gson.fromJson(response.toString(), responseType);
                repository.addItem(propertyAppliance);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i(throwable, "PropertyAppliance get onFailure(). {statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.property_appliance_error_get)));
            }
        });
    }

    @Override
    public void findByPropertyId(Long propertyId, final IErrorCallback errorCallback) {
        logger.i("Entered PropertyAppliance findByPropertyId()");
        RequestParams params = new RequestParams();
        params.put(IPropertyApplianceWebConstants.PROPERTY_ID_PARAM, propertyId);
        httpClient.get(IPropertyApplianceWebResources.FIND_BY_PROPERTY_ID, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("PropertyAppliance findByPropertyId onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonFactory().createDateFormattingGson();
                String data = "";
                try {
                    data = new EmbeddedJsonExtractor().extractArray(response).toString();
                } catch (JSONException e) {
                    errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.common_error_unexpected)));
                    return;
                }
                Type responseType = new TypeToken<List<PropertyAppliance>>(){}.getType();
                List<PropertyAppliance> propertyApplianceList = gson.fromJson(data, responseType);
                repository.addItems(propertyApplianceList);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i(throwable, "PropertyAppliance findByPropertyId onFailure(). {statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.property_appliance_error_get)));
            }
        });
    }

    @Override
    public void findByPropertyIds(List<Long> propertyIds, final IErrorCallback errorCallback) {
        logger.i("Entered PropertyAppliance findByPropertyIds()");
        String params = new RequestParamGenerator<Long>().generateIDListRequestParams(propertyIds, IPropertyApplianceWebConstants.PROPERTY_IDS_PARAM);
        httpClient.get(IPropertyApplianceWebResources.FIND_BY_PROPERTY_ID_IN + params, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("PropertyAppliance findByPropertyIds onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonFactory().createDateFormattingGson();
                String data = "";
                try {
                    data = new EmbeddedJsonExtractor().extractArray(response).toString();
                } catch (JSONException e) {
                    errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.common_error_unexpected)));
                    return;
                }
                Type responseType = new TypeToken<List<PropertyAppliance>>(){}.getType();
                List<PropertyAppliance> propertyApplianceList = gson.fromJson(data, responseType);
                repository.addItems(propertyApplianceList);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i(throwable, "PropertyAppliance findByPropertyIds onFailure(). {statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.property_appliance_error_get)));
            }
        });
    }
}
