package com.appl_maint_mngt.property.services;

import android.content.Context;

import com.appl_maint_mngt.MainActivity;
import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.errors.ErrorPayloadBuilder;
import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.common.utility.EmbeddedJsonExtractor;
import com.appl_maint_mngt.common.utility.GsonFactory;
import com.appl_maint_mngt.common.utility.RequestParamGenerator;
import com.appl_maint_mngt.property.constants.web.IPropertyWebConstants;
import com.appl_maint_mngt.property.constants.web.IPropertyWebResources;
import com.appl_maint_mngt.property.models.Property;
import com.appl_maint_mngt.property.repositories.interfaces.IPropertyUpdateableRepository;
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

public class PropertyService implements IPropertyService {

    private static final Logger logger = LoggerManager.getLogger(PropertyService.class);

    private AsyncHttpClient httpClient;
    private Context context;

    private IPropertyUpdateableRepository repository;

    public PropertyService() {
        httpClient = new AsyncHttpClient();
        context = MainActivity.getInstance();

        repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getPropertyRepository();
    }

    @Override
    public void findByIds(List<Long> ids, final IErrorCallback errorCallback) {
        logger.i("Entered PropertyService findByIds()");
        String params = new RequestParamGenerator<Long>().generateIDListRequestParams(ids, IPropertyWebConstants.IDS_PARAM);
        httpClient.get(IPropertyWebResources.FIND_BY_ID_IN_RESOURCE + params, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("PropertyService findByIds onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonFactory().createDateFormattingGson();
                String data = "";
                try {
                    data = new EmbeddedJsonExtractor().extractArray(response).toString();
                } catch (JSONException e) {
                    errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.common_error_unexpected)));
                    return;
                }
                Type responseType = new TypeToken<List<Property>>(){}.getType();
                List<Property> propertytList = gson.fromJson(data, responseType);
                repository.addItems(propertytList);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i(throwable, "PropertyService findByIds onFailure(). {statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.property_error_get)));
            }
        });
    }

    @Override
    public void get(Long id, final IErrorCallback errorCallback) {
        logger.i("Entered PropertyService get()");
        httpClient.get(IPropertyWebResources.GET_RESOURCE + id, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("PropertyService get onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonFactory().createDateFormattingGson();
                Type responseType = new TypeToken<Property>(){}.getType();
                Property property = gson.fromJson(response.toString(), responseType);
                repository.addItem(property);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i(throwable, "PropertyService findByIds onFailure(). {statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.property_error_get)));
            }
        });
    }
}
