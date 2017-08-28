package com.appl_maint_mngt.pending_repair_report.services;

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
import com.appl_maint_mngt.pending_repair_report.constants.web.IPendingRepairReportWebConstants;
import com.appl_maint_mngt.pending_repair_report.constants.web.IPendingRepairReportWebResources;
import com.appl_maint_mngt.pending_repair_report.models.PendingRepairReport;
import com.appl_maint_mngt.pending_repair_report.models.web.interfaces.IPendingRepairReportPayload;
import com.appl_maint_mngt.pending_repair_report.repositories.interfaces.IPendingRepairReportUpdateableRepository;
import com.appl_maint_mngt.pending_repair_report.services.interfaces.IPendingRepairReportService;
import com.appl_maint_mngt.pending_repair_report.utility.PendingRepairReportWebResourceBuilder;
import com.appl_maint_mngt.pending_repair_report.utility.interfaces.IPendingRepairReportWebResourceBuilder;
import com.google.gson.Gson;
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
 * Created by Kyle on 17/04/2017.
 */

public class PendingRepairReportService implements IPendingRepairReportService {

    private static final Logger logger = LoggerManager.getLogger(PendingRepairReportService.class);

    private AsyncHttpClient httpClient;
    private Context context;

    private IPendingRepairReportUpdateableRepository repository;

    public PendingRepairReportService() {
        httpClient = new AsyncHttpClient();
        context = MainActivity.getInstance();

        repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getPendingRepairReportRepository();
    }

    @Override
    public void accept(Long id, final IErrorCallback errorCallback) {
        logger.i("Entered PendingRepairReport accept");
        logger.i("PendingRepairReport accept resource: %s", new PendingRepairReportWebResourceBuilder().buildAcceptResource(id));
        httpClient.post(new PendingRepairReportWebResourceBuilder().buildAcceptResource(id), new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("PendingRepairReport accept onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonFactory().createDateFormattingGson();
                Type responseType = new TypeToken<PendingRepairReport>(){}.getType();
                PendingRepairReport pendingRepairReport = gson.fromJson(response.toString(), responseType);
                repository.addItem(pendingRepairReport);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i("PendingRepairReport accept onFailure(). {statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.pending_repair_report_error_accept)));
            }
        });
    }

    @Override
    public void decline(Long id, final IErrorCallback errorCallback) {
        logger.i("Entered PendingRepairReport decline");
        logger.i("PendingRepairReport decline resource: %s", new PendingRepairReportWebResourceBuilder().buildDeclineResource(id));
        httpClient.post(new PendingRepairReportWebResourceBuilder().buildDeclineResource(id), new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("PendingRepairReport decline onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonFactory().createDateFormattingGson();
                Type responseType = new TypeToken<PendingRepairReport>(){}.getType();
                PendingRepairReport pendingRepairReport = gson.fromJson(response.toString(), responseType);
                repository.addItem(pendingRepairReport);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i("PendingRepairReport decline onFailure(). {statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.pending_repair_report_error_decline)));
            }
        });
    }

    @Override
    public void findByDiagnosticRequestId(Long diagnosticRequestId, final IErrorCallback errorCallback) {
        logger.i("Entered PendingRepairReport findByDiagnosticRequestId");
        RequestParams params = new RequestParams();
        params.put(IPendingRepairReportWebConstants.DIAG_REQ_ID_PARAM, diagnosticRequestId);
        httpClient.get(IPendingRepairReportWebResources.FIND_BY_DIAGNOSTIC_REQUEST_ID_RESOURCE , params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("PendingRepairReport findByDiagnosticRequestIdIn onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonFactory().createDateFormattingGson();
                String data = "";
                try {
                    data = new EmbeddedJsonExtractor().extractObject(response).toString();
                } catch (JSONException e) {
                    errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.common_error_unexpected)));
                    return;
                }
                Type responseType = new TypeToken<PendingRepairReport>(){}.getType();
                PendingRepairReport pendingRepairReport = gson.fromJson(data, responseType);
                repository.addItem(pendingRepairReport);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i("PendingRepairReport findByDiagnosticRequestId onFailure(). {statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.pending_repair_report_error_get)));
            }
        });
    }

    @Override
    public void findByDiagnosticRequestIdIn(List<Long> ids, final IErrorCallback errorCallback) {
        logger.i("Entered PendingRepairReport findByDiagnosticRequestIdIn");
        String params = new RequestParamGenerator<Long>().generateIDListRequestParams(ids, IPendingRepairReportWebConstants.DIAG_REQ_IDS_PARAM);
        httpClient.get(IPendingRepairReportWebResources.FIND_BY_DIAGNSOTIC_REQUEST_ID_IN_RESOURCE + params, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("PendingRepairReport findByDiagnosticRequestIdIn onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonFactory().createDateFormattingGson();
                String data = "";
                try {
                    data = new EmbeddedJsonExtractor().extractArray(response).toString();
                } catch (JSONException e) {
                    errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.common_error_unexpected)));
                    return;
                }
                Type responseType = new TypeToken<List<PendingRepairReport>>(){}.getType();
                List<PendingRepairReport> pendingRepairReports = gson.fromJson(data, responseType);
                repository.addItems(pendingRepairReports);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i("PendingRepairReport findByDiagnosticRequestIdIn onFailure(). {statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.pending_repair_report_error_get)));
            }
        });
    }

    @Override
    public void create(IPendingRepairReportPayload payload, final IErrorCallback errorCallback) {
        logger.i("Entered PendingRepairReport create");
        Gson gson = new Gson();
        StringEntity entity = null;
        try {
            entity = new StringEntity(gson.toJson(payload));
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, IWebConstants.CONTENT_TYPE_JSON));
        } catch (UnsupportedEncodingException e) {
            logger.e(e, "UnsupportedEncodingException: on %s", gson.toJson(payload));
            errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.common_error_unexpected)));
        }

        httpClient.post(context, IPendingRepairReportWebResources.CREATE_RESOURCE, entity, IWebConstants.CONTENT_TYPE_JSON, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("PendingRepairReport create onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonFactory().createDateFormattingGson();
                Type responseType = new TypeToken<PendingRepairReport>(){}.getType();
                PendingRepairReport pendingRepairReport = gson.fromJson(response.toString(), responseType);
                repository.addItem(pendingRepairReport);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i("PendingRepairReport create onFailure(). {statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.pending_repair_report_error_create)));
            }
        });
    }

    @Override
    public void findByEngineerId(Long engineerId, final IErrorCallback errorCallback) {
        logger.i("Entered PendingRepairReport findByEngineerId");
        RequestParams params = new RequestParams();
        params.put(IPendingRepairReportWebConstants.ENG_ID_PARAM, engineerId);
        httpClient.get(IPendingRepairReportWebResources.FIND_BY_ENGINEER_ID_RESOURCE, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("PendingRepairReport findByEngineerId onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonFactory().createDateFormattingGson();
                String data = "";
                try {
                    data = new EmbeddedJsonExtractor().extractArray(response).toString();
                } catch (JSONException e) {
                    errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.common_error_unexpected)));
                    return;
                }
                Type responseType = new TypeToken<List<PendingRepairReport>>(){}.getType();
                List<PendingRepairReport> pendingRepairReports = gson.fromJson(data, responseType);
                repository.addItems(pendingRepairReports);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i("PendingRepairReport findByEngineerId onFailure(). {statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.pending_repair_report_error_get)));
            }
        });
    }
}
