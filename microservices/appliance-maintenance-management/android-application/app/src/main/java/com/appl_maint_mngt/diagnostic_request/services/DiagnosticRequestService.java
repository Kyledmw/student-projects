package com.appl_maint_mngt.diagnostic_request.services;

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
import com.appl_maint_mngt.diagnostic_request.constants.web.IDiagnosticRequestWebConstants;
import com.appl_maint_mngt.diagnostic_request.constants.web.IDiagnosticRequestWebResources;
import com.appl_maint_mngt.diagnostic_request.models.DiagnosticRequest;
import com.appl_maint_mngt.diagnostic_request.models.constants.ResponseStatus;
import com.appl_maint_mngt.diagnostic_request.models.interfaces.IDiagnosticRequestReadable;
import com.appl_maint_mngt.diagnostic_request.models.web.interfaces.IDiagnosticRequestPayload;
import com.appl_maint_mngt.diagnostic_request.models.web.interfaces.IDiagnosticRequestUpdatePayload;
import com.appl_maint_mngt.diagnostic_request.repositories.interfaces.IDiagnosticRequestUpdateableRepository;
import com.appl_maint_mngt.diagnostic_request.services.interfaces.IDiagnosticRequestService;
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
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

/**
 * Created by Kyle on 10/04/2017.
 */

public class DiagnosticRequestService implements IDiagnosticRequestService {

    private static final Logger logger = LoggerManager.getLogger(DiagnosticRequestService.class);

    private AsyncHttpClient httpClient;
    private Context context;

    private IDiagnosticRequestUpdateableRepository repository;

    public DiagnosticRequestService() {
        httpClient = new AsyncHttpClient();
        context = MainActivity.getInstance();

        repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getDiagnosticRequestRepository();
    }

    @Override
    public void post(IDiagnosticRequestPayload payload, final IErrorCallback errorCallback) {
        logger.i("Entered DiagnosticRequest post()");
        Gson gson = new GsonBuilder().create();
        StringEntity entity = null;
        try {
            entity = new StringEntity(gson.toJson(payload));
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, IWebConstants.CONTENT_TYPE_JSON));
        } catch (UnsupportedEncodingException e) {
            logger.e(e, "UnsupportedEncodingException: on %s", gson.toJson(payload));
            errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.common_error_unexpected)));
        }
        httpClient.post(context, IDiagnosticRequestWebResources.POST_RESOURCE, entity, IWebConstants.CONTENT_TYPE_JSON, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("DiagnosticRequest post onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonFactory().createDateFormattingGson();
                Type responseType = new TypeToken<DiagnosticRequest>(){}.getType();
                DiagnosticRequest diagnosticRequest = gson.fromJson(response.toString(), responseType);
                repository.addItem(diagnosticRequest);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i(throwable, "DiagnosticRequest post onFailure(). statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.diagnostic_request_error_save)));
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String s, Throwable t) {
                logger.i(t, "DiagnosticRequest post onFailure(). statusCode: %d}", statusCode);
                if(s != null) logger.i("Response: %s", s);
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.diagnostic_request_error_save)));
            }
        });
    }

    @Override
    public void findByDiagnosticReportId(Long diagRepId, final IErrorCallback errorCallback) {
        logger.i("Entered DiagnosticRequest findByDiagnosticReportId()");
        RequestParams params = new RequestParams();
        params.put(IDiagnosticRequestWebConstants.DIAG_REP_ID_PARAM, diagRepId);
        httpClient.get(IDiagnosticRequestWebResources.FIND_BY_DIAGNOSTIC_REPORT_ID, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("DiagnosticRequest findByDiagnosticReportId onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonFactory().createDateFormattingGson();
                String data = "";
                try {
                    data = new EmbeddedJsonExtractor().extractArray(response).toString();
                } catch (JSONException e) {
                    errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.common_error_unexpected)));
                    return;
                }
                Type responseType = new TypeToken<List<DiagnosticRequest>>(){}.getType();
                List<DiagnosticRequest> diagnosticRequestList = gson.fromJson(data, responseType);
                repository.addItems(diagnosticRequestList);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i(throwable, "DiagnosticReport findByDiagnosticReportId onFailure(). {statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.diagnostic_request_error_get)));
            }
        });
    }

    @Override
    public void findByDiagnosticReportIds(List<Long> diagRepIds, final IErrorCallback errorCallback) {
        logger.i("Entered DiagnosticRequest findByDiagnosticReportIds()");
        String params = new RequestParamGenerator<Long>().generateIDListRequestParams(diagRepIds, IDiagnosticRequestWebConstants.DIAG_REP_IDS_PARAM);
        httpClient.get(IDiagnosticRequestWebResources.FIND_BY_DIAGNOSTIC_REPORT_ID_IN + params, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("DiagnosticRequest findByDiagnosticReportIds onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonFactory().createDateFormattingGson();
                String data = "";
                try {
                    data = new EmbeddedJsonExtractor().extractArray(response).toString();
                } catch (JSONException e) {
                    errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.common_error_unexpected)));
                    return;
                }
                Type responseType = new TypeToken<List<DiagnosticRequest>>(){}.getType();
                List<DiagnosticRequest> diagnosticRequestList = gson.fromJson(data, responseType);
                repository.addItems(diagnosticRequestList);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i(throwable, "DiagnosticReport findByDiagnosticReportIds onFailure(). {statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.diagnostic_report_error_get)));
            }
        });
    }

    @Override
    public void findByMaintenanceOrganisationId(Long maintOrgId, final IErrorCallback errorCallback) {
        logger.i("Entered DiagnosticRequest findByMaintenanceOrganisationId()");
        RequestParams params = new RequestParams();
        params.put(IDiagnosticRequestWebConstants.MAINTENANCE_ORGANISATION_ID_PARAM, maintOrgId);
        httpClient.get(IDiagnosticRequestWebResources.FIND_BY_MAINT_ORG_ID, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("DiagnosticRequest findByMaintenanceOrganisationId onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonFactory().createDateFormattingGson();
                String data = "";
                try {
                    data = new EmbeddedJsonExtractor().extractArray(response).toString();
                } catch (JSONException e) {
                    errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.common_error_unexpected)));
                    return;
                }
                Type responseType = new TypeToken<List<DiagnosticRequest>>(){}.getType();
                List<DiagnosticRequest> diagnosticRequestList = gson.fromJson(data, responseType);
                repository.addItems(diagnosticRequestList);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i(throwable, "DiagnosticReport findByMaintenanceOrganisationId onFailure(). {statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.diagnostic_report_error_get)));
            }
        });
    }

    @Override
    public void put(IDiagnosticRequestUpdatePayload payload, final IErrorCallback errorCallback) {
        logger.i("Entered DiagnosticRequest put()");
        Gson gson = new GsonBuilder().create();
        StringEntity entity = null;
        try {
            entity = new StringEntity(gson.toJson(payload));
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, IWebConstants.CONTENT_TYPE_JSON));
        } catch (UnsupportedEncodingException e) {
            logger.e(e, "UnsupportedEncodingException: on %s", gson.toJson(payload));
            errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.common_error_unexpected)));
        }
        httpClient.put(context, IDiagnosticRequestWebResources.PUT_RESOURCE + payload.getId(), entity, IWebConstants.CONTENT_TYPE_JSON, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("DiagnosticRequest put onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonFactory().createDateFormattingGson();
                Type responseType = new TypeToken<DiagnosticRequest>(){}.getType();
                DiagnosticRequest diagnosticRequest = gson.fromJson(response.toString(), responseType);
                repository.addItem(diagnosticRequest);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i(throwable, "DiagnosticRequest put onFailure(). statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.diagnostic_request_error_save)));

            }
        });
    }
}
