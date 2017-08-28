package com.appl_maint_mngt.maintenance_schedule.services;

import android.content.Context;

import com.appl_maint_mngt.MainActivity;
import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.errors.ErrorPayloadBuilder;
import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.common.utility.EmbeddedJsonExtractor;
import com.appl_maint_mngt.common.utility.GsonFactory;
import com.appl_maint_mngt.common.utility.RequestParamGenerator;
import com.appl_maint_mngt.maintenance_schedule.constants.web.IMaintenanceScheduleWebConstants;
import com.appl_maint_mngt.maintenance_schedule.constants.web.IMaintenanceScheduleWebResources;
import com.appl_maint_mngt.maintenance_schedule.models.MaintenanceSchedule;
import com.appl_maint_mngt.maintenance_schedule.repositories.interfaces.IMaintenanceScheduleUpdateableRepository;
import com.appl_maint_mngt.maintenance_schedule.services.interfaces.IMaintenanceScheduleService;
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
 * Created by Kyle on 14/04/2017.
 */

public class MaintenanceScheduleService implements IMaintenanceScheduleService {

    private static final Logger logger = LoggerManager.getLogger(MaintenanceScheduleService.class);

    private AsyncHttpClient httpClient;
    private Context context;

    private IMaintenanceScheduleUpdateableRepository repository;

    public MaintenanceScheduleService() {
        httpClient = new AsyncHttpClient();
        context = MainActivity.getInstance();

        repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getMaintenanceScheduleRepository();
    }

    @Override
    public void findByRepairReportId(final Long repairReportId, final IErrorCallback errorCallback) {
        logger.i("Entered MaintenanceSchedule findByRepairReportId");
        RequestParams params = new RequestParams();
        params.put(IMaintenanceScheduleWebConstants.REPAIR_REPORT_ID_PARAM, repairReportId);
        httpClient.get(context, IMaintenanceScheduleWebResources.FIND_BY_REPAIR_REPORT_ID, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("MaintenanceSchedule findByRepairReportId onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonFactory().createDateFormattingGson();
                Type responseType = new TypeToken<MaintenanceSchedule>(){}.getType();
                MaintenanceSchedule maintenanceSchedule = gson.fromJson(response.toString(), responseType);
                repository.addItem(maintenanceSchedule);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i(throwable, "MaintenanceSchedule findByRepairReportId onFailure(). statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.maintenance_schedule_error_get)));
            }

            @Override
            public void onFailure(int statusCode, Header[] h, String s, Throwable t) {
                logger.i(t, "MaintenanceSchedule findByRepairReportId onFailure(). statusCode: %d}", statusCode);
                if(s != null) logger.i("Response: %s", s);
            }
        });
    }

    @Override
    public void findByRepairReportIdIn(List<Long> repairReportIds, final IErrorCallback errorCallback) {
        logger.i("Entered MaintenanceSchedule findByRepairReportIds");
        String params = new RequestParamGenerator<Long>().generateIDListRequestParams(repairReportIds, IMaintenanceScheduleWebConstants.REPAIR_REPORT_IDS_PARAM);
        httpClient.get(context, IMaintenanceScheduleWebResources.FIND_BY_REPAIR_REPORT_ID_IN + params, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("MaintenanceSchedule findByRepairReportIdIn onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonFactory().createDateFormattingGson();
                String data = "";
                try {
                    data = new EmbeddedJsonExtractor().extractArray(response).toString();
                } catch (JSONException e) {
                    errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.common_error_unexpected)));
                    return;
                }
                Type responseType = new TypeToken<List<MaintenanceSchedule>>(){}.getType();
                List<MaintenanceSchedule> list = gson.fromJson(data, responseType);
                repository.addItems(list);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i(throwable, "MaintenanceSchedule findByRepairReportIdIn onFailure(). statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.maintenance_schedule_error_get)));
            }
        });
    }
}
