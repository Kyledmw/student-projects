package com.appl_maint_mngt.pending_maintenance_scheduling.services;

import android.content.Context;

import com.appl_maint_mngt.MainActivity;
import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.constants.ICommonConstants;
import com.appl_maint_mngt.common.constants.web.IWebConstants;
import com.appl_maint_mngt.common.errors.ErrorPayloadBuilder;
import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.common.models.web.ApiResponse;
import com.appl_maint_mngt.common.models.web.constants.ApiResponseStatus;
import com.appl_maint_mngt.common.utility.GsonFactory;
import com.appl_maint_mngt.maintenance_organisation.services.MaintenanceOrganisationService;
import com.appl_maint_mngt.pending_maintenance_scheduling.constants.web.IPendingMaintenanceSchedulingWebConstants;
import com.appl_maint_mngt.pending_maintenance_scheduling.constants.web.IPendingMaintenanceSchedulingWebResources;
import com.appl_maint_mngt.pending_maintenance_scheduling.models.PendingMaintenanceSchedule;
import com.appl_maint_mngt.pending_maintenance_scheduling.models.constants.SchedulerType;
import com.appl_maint_mngt.pending_maintenance_scheduling.models.webs.interfaces.IPendingMaintenanceSchedulePayload;
import com.appl_maint_mngt.pending_maintenance_scheduling.repositories.interfaces.IPendingMaintenanceSchedulingUpdateableRepository;
import com.appl_maint_mngt.pending_maintenance_scheduling.services.interfaces.IPendingMaintenanceSchedulingService;
import com.appl_maint_mngt.pending_maintenance_scheduling.utility.PendingMaintenanceSchedulingWebResourceBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import org.json.JSONArray;
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

public class PendingMaintenanceSchedulingService implements IPendingMaintenanceSchedulingService {

    private static final Logger logger = LoggerManager.getLogger(PendingMaintenanceSchedulingService.class);

    private AsyncHttpClient httpClient;
    private Context context;

    private IPendingMaintenanceSchedulingUpdateableRepository repository;

    public PendingMaintenanceSchedulingService() {
        httpClient = new AsyncHttpClient();
        context = MainActivity.getInstance();

        repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getPendingMaintenanceSchedulingRepository();
    }

    @Override
    public void add(IPendingMaintenanceSchedulePayload payload, final IErrorCallback errorCallback) {
        logger.i("Entered PendingMaintenanceSchedulingService add");
        Gson gson = new Gson();
        StringEntity entity = null;
        try {
            entity = new StringEntity(gson.toJson(payload));
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, IWebConstants.CONTENT_TYPE_JSON));
        } catch (UnsupportedEncodingException e) {
            logger.e(e, "UnsupportedEncodingException: on %s", gson.toJson(payload));
            errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.common_error_unexpected)));
        }
        httpClient.post(context, IPendingMaintenanceSchedulingWebResources.ADD_RESOURCE, entity, IWebConstants.CONTENT_TYPE_JSON,  new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("PendingMaintenanceSchedulingService add onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonFactory().createDateFormattingGson();
                Type responseType = new TypeToken<ApiResponse<PendingMaintenanceSchedule>>(){}.getType();
                ApiResponse<PendingMaintenanceSchedule> apiResponse = gson.fromJson(response.toString(), responseType);
                logger.i("ApiResponse: %s", apiResponse.getMessage());
                if(apiResponse.getStatus().equals(ApiResponseStatus.SUCCESS)) {
                    repository.addItem(apiResponse.getData());
                } else {
                    errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.pending_maintenance_schedule_error_add)));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i("PendingMaintenanceSchedulingService add onFailure(). {statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.pending_maintenance_schedule_error_add)));
            }
        });
    }

    @Override
    public void getPendingSchedules(Long reportId, SchedulerType type, final IErrorCallback errorCallback) {
        logger.i("Entered PendingMaintenanceSchedulingService getPendingSchedules");
        RequestParams params = new RequestParams();
        params.put(IPendingMaintenanceSchedulingWebConstants.SCHEDULER_TYPE_PARAM, type.toString());
        httpClient.get(new PendingMaintenanceSchedulingWebResourceBuilder().buildPendingReportResource(reportId), params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("PendingMaintenanceSchedulingService getPendingSchedules onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonFactory().createDateFormattingGson();
                Type responseType = new TypeToken<ApiResponse<List<PendingMaintenanceSchedule>>>(){}.getType();
                ApiResponse<List<PendingMaintenanceSchedule>> apiResponse = gson.fromJson(response.toString(), responseType);
                logger.i("ApiResponse: %s", apiResponse.getMessage());
                if(apiResponse.getStatus().equals(ApiResponseStatus.SUCCESS)) {
                    repository.addItems(apiResponse.getData());
                } else {
                    errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.pending_maintenance_schedule_error_add)));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i("PendingMaintenanceSchedulingService getPendingSchedules onFailure(). {statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.pending_maintenance_schedule_error_get)));
            }
        });
    }

    @Override
    public void getAllPending(final Long reportId, final IErrorCallback errorCallback) {
        logger.i("Entered PendingMaintenanceSchedulingService getAllPending");
        httpClient.get(IPendingMaintenanceSchedulingWebResources.GET_REPORT_RESOURCE + reportId + ICommonConstants.FORWARD_SLASH, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                logger.i("PendingMaintenanceSchedulingService getAllPending onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonFactory().createDateFormattingGson();
                Type responseType = new TypeToken<List<PendingMaintenanceSchedule>>(){}.getType();
                List<PendingMaintenanceSchedule> list = gson.fromJson(response.toString(), responseType);
                repository.addItems(list);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i("PendingMaintenanceSchedulingService getAllPending onFailure(). {statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.pending_maintenance_schedule_error_get)));
            }
        });
    }

    @Override
    public void accept(Long id, final IErrorCallback errorCallback) {
        logger.i("Entered PendingMaintenanceSchedulingService accept");
        httpClient.post(new PendingMaintenanceSchedulingWebResourceBuilder().buildAcceptResource(id), new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("PendingMaintenanceSchedulingService accept onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonFactory().createDateFormattingGson();
                Type responseType = new TypeToken<ApiResponse<PendingMaintenanceSchedule>>(){}.getType();
                ApiResponse<PendingMaintenanceSchedule> apiResponse = gson.fromJson(response.toString(), responseType);
                logger.i("ApiResponse: %s", apiResponse.getMessage());
                if(apiResponse.getStatus().equals(ApiResponseStatus.SUCCESS)) {
                    repository.addItem(apiResponse.getData());
                } else {
                    errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.pending_maintenance_schedule_error_add)));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i("PendingMaintenanceSchedulingService accept onFailure(). {statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.pending_maintenance_schedule_error_accept)));
            }
        });
    }

    @Override
    public void decline(Long id, final IErrorCallback errorCallback) {
        logger.i("Entered PendingMaintenanceSchedulingService decline");
        httpClient.post(new PendingMaintenanceSchedulingWebResourceBuilder().buildDeclineResource(id), new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("PendingMaintenanceSchedulingService decline onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonFactory().createDateFormattingGson();
                Type responseType = new TypeToken<ApiResponse<PendingMaintenanceSchedule>>(){}.getType();
                ApiResponse<PendingMaintenanceSchedule> apiResponse = gson.fromJson(response.toString(), responseType);
                logger.i("ApiResponse: %s", apiResponse.getMessage());
                if(apiResponse.getStatus().equals(ApiResponseStatus.SUCCESS)) {
                    repository.addItem(apiResponse.getData());
                } else {
                    errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.pending_maintenance_schedule_error_add)));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i("PendingMaintenanceSchedulingService decline onFailure(). {statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.pending_maintenance_schedule_error_decline)));
            }
        });
    }
}
