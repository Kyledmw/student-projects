package com.appl_maint_mngt.diagnostic_report.services;

import android.content.Context;

import com.appl_maint_mngt.MainActivity;
import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.constants.web.IWebConstants;
import com.appl_maint_mngt.common.errors.ErrorPayloadBuilder;
import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.common.utility.EmbeddedJsonExtractor;
import com.appl_maint_mngt.common.utility.GsonFactory;
import com.appl_maint_mngt.common.utility.RequestParamGenerator;
import com.appl_maint_mngt.diagnostic_report.constants.web.IDiagnosticReportWebConstants;
import com.appl_maint_mngt.diagnostic_report.constants.web.IDiagnosticReportWebResources;
import com.appl_maint_mngt.diagnostic_report.models.DiagnosticReport;
import com.appl_maint_mngt.diagnostic_report.models.web.interfaces.IDiagnosticReportForm;
import com.appl_maint_mngt.diagnostic_report.repositories.interfaces.IDiagnosticReportUpdateableRepository;
import com.appl_maint_mngt.diagnostic_report.services.interfaces.IDiagnosticReportService;
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

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

/**
 * Created by Kyle on 16/04/2017.
 */

public class DiagnosticReportService implements IDiagnosticReportService {

    private static final Logger logger = LoggerManager.getLogger(DiagnosticReportService.class);

    private AsyncHttpClient httpClient;
    private Context context;

    private IDiagnosticReportUpdateableRepository repository;

    public DiagnosticReportService() {
        httpClient = new AsyncHttpClient();
        context = MainActivity.getInstance();

        repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getDiagnosticReportRepository();
    }

    @Override
    public void post(IDiagnosticReportForm diagRep, final IErrorCallback errorCallback) {
        logger.i("Entered DiagnosticReport post()");
        Gson gson = new GsonFactory().createDateFormattingGson();
        StringEntity entity = null;
        try {
            entity = new StringEntity(gson.toJson(diagRep));
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, IWebConstants.CONTENT_TYPE_JSON));
        } catch (UnsupportedEncodingException e) {
            logger.e(e, "UnsupportedEncodingException: on %s", gson.toJson(diagRep));
            errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.common_error_unexpected)));
        }

        httpClient.post(context, IDiagnosticReportWebResources.POST_RESOURCE, entity, IWebConstants.CONTENT_TYPE_JSON, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("DiagnosticReport post onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonFactory().createDateFormattingGson();
                Type responseType = new TypeToken<DiagnosticReport>(){}.getType();

                DiagnosticReport diagnosticReport = gson.fromJson(response.toString(), responseType);
                repository.addItem(diagnosticReport);
            }

            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i(throwable, "DiagnosticReport post onFailure(). statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.diagnostic_report_error_post)));
            }
        });
    }

    @Override
    public void getForPropertyApplianceId(Long propertyApplianceId, final IErrorCallback errorCallback) {
        logger.i("Entered DiagnosticReport getForPropertyApplianceId()");
        RequestParams params = new RequestParams();
        params.put(IDiagnosticReportWebConstants.PROP_APPL_ID_PARAM, propertyApplianceId);
        httpClient.get(IDiagnosticReportWebResources.FIND_BY_PROP_APPL_ID_RESOURCE, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("DiagnosticReport getForPropertyApplianceId onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonFactory().createDateFormattingGson();
                String data = "";
                try {
                    data = new EmbeddedJsonExtractor().extractArray(response).toString();
                } catch (JSONException e) {
                    errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.common_error_unexpected)));
                    return;
                }
                Type responseType = new TypeToken<List<DiagnosticReport>>(){}.getType();
                List<DiagnosticReport> diagnosticReportList = gson.fromJson(data, responseType);
                repository.addItems(diagnosticReportList);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i(throwable, "DiagnosticReport getForPropertyApplianceId onFailure(). {statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.diagnostic_report_error_get)));
            }
            @Override
            public void onFailure(int statusCode, Header[] h, String s, Throwable t) {
                logger.i(t, "DiagnosticReport getForPropertyApplianceIdIn onFailure(). {statusCode: %d}", statusCode);
                if(s != null) logger.i("Response: %s", s);
            }
        });
    }

    @Override
    public void findByPropertyApplianceIdsIn(List<Long> propertyApplianceIds, final IErrorCallback errorCallback) {
        logger.i("Entered DiagnosticReport findByPropertyApplianceIdsIn()");
        String params = new RequestParamGenerator<Long>().generateIDListRequestParams(propertyApplianceIds, IDiagnosticReportWebConstants.PROP_APPL_IDS_PARAM);
        httpClient.get(IDiagnosticReportWebResources.FIND_BY_PROP_APPL_ID_IN_RESOURCE + params, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("DiagnosticReport getForPropertyApplianceIdsIn onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonFactory().createDateFormattingGson();
                String data = "";
                try {
                    data = new EmbeddedJsonExtractor().extractArray(response).toString();
                } catch (JSONException e) {
                    errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.common_error_unexpected)));
                    return;
                }
                Type responseType = new TypeToken<List<DiagnosticReport>>(){}.getType();
                List<DiagnosticReport> diagnosticReportList = gson.fromJson(data, responseType);
                repository.addItems(diagnosticReportList);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i(throwable, "DiagnosticReport getForPropertyApplianceIdIn onFailure(). {statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.diagnostic_report_error_get)));
            }
            @Override
            public void onFailure(int statusCode, Header[] h, String s, Throwable t) {
                logger.i(t, "DiagnosticReport getForPropertyApplianceIdIn onFailure(). {statusCode: %d}", statusCode);
                if(s != null) logger.i("Response: %s", s);
            }
        });
    }

    @Override
    public void findByIdsIn(List<Long> ids, final IErrorCallback errorCallback) {
        logger.i("Entered DiagnosticReport findByIdsIn()");
        String params = new RequestParamGenerator<Long>().generateIDListRequestParams(ids, IDiagnosticReportWebConstants.IDS_PARAM);
        httpClient.get(IDiagnosticReportWebResources.FIND_BY_ID_IN_RESOURCE + params, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("DiagnosticReport findByIdsIn onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonFactory().createDateFormattingGson();
                String data = "";
                try {
                    data = new EmbeddedJsonExtractor().extractArray(response).toString();
                } catch (JSONException e) {
                    errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.common_error_unexpected)));
                    return;
                }
                Type responseType = new TypeToken<List<DiagnosticReport>>(){}.getType();
                List<DiagnosticReport> diagnosticReportList = gson.fromJson(data, responseType);
                repository.addItems(diagnosticReportList);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i(throwable, "DiagnosticReport findByIdsIn onFailure(). {statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.diagnostic_report_error_get)));
            }

            @Override
            public void onFailure(int statusCode, Header[] h, String s, Throwable t) {
                logger.i(t, "DiagnosticReport findByIdsIn onFailure(). {statusCode: %d}", statusCode);
                if(s != null) logger.i("Response: %s", s);
            }
        });
    }

    @Override
    public void get(Long id, final IErrorCallback errorCallback) {
        logger.i("Entered DiagnosticReport get() for ID: %d", id);
        httpClient.get(IDiagnosticReportWebResources.GET_RESOURCE + id, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("DiagnosticReport get onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonFactory().createDateFormattingGson();
                Type responseType = new TypeToken<DiagnosticReport>(){}.getType();
                DiagnosticReport diagnosticReport = gson.fromJson(response.toString(), responseType);
                repository.addItem(diagnosticReport);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i(throwable, "DiagnosticReport get onFailure(). {statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.diagnostic_report_error_get)));
            }
        });
    }
}
