package com.appl_maint_mngt.repair_report.services;

import android.content.Context;

import com.appl_maint_mngt.MainActivity;
import com.appl_maint_mngt.R;
import com.appl_maint_mngt.common.errors.ErrorPayloadBuilder;
import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.common.utility.EmbeddedJsonExtractor;
import com.appl_maint_mngt.common.utility.GsonFactory;
import com.appl_maint_mngt.common.utility.RequestParamGenerator;
import com.appl_maint_mngt.diagnostic_report.constants.web.IDiagnosticReportWebConstants;
import com.appl_maint_mngt.repair_report.constants.web.IRepairReportWebConstants;
import com.appl_maint_mngt.repair_report.constants.web.IRepairReportWebResources;
import com.appl_maint_mngt.repair_report.models.RepairReport;
import com.appl_maint_mngt.repair_report.repositories.interfaces.IRepairReportUpdateableRepository;
import com.appl_maint_mngt.repair_report.services.interfaces.IRepairReportService;
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

public class RepairReportService implements IRepairReportService {

    private static final Logger logger = LoggerManager.getLogger(RepairReportService.class);

    private AsyncHttpClient httpClient;
    private Context context;

    private IRepairReportUpdateableRepository repository;

    public RepairReportService() {
        httpClient = new AsyncHttpClient();
        context = MainActivity.getInstance();

        repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getRepairReportRepository();
    }

    @Override
    public void findByDiagnosticReportId(Long id, final IErrorCallback errorCallback) {
        logger.i("Entered RepairReport findByDiagnosticReportId(()");
        RequestParams params = new RequestParams();
        params.put(IRepairReportWebConstants.DIAG_REP_ID_PARAM, id);
        httpClient.get(IRepairReportWebResources.FIND_BY_DIAGNOSTIC_REPORT_ID, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("RepairReport findByDiagnosticReportId( onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonFactory().createDateFormattingGson();
                Type responseType = new TypeToken<RepairReport>(){}.getType();
                RepairReport repairReport = gson.fromJson(response.toString(), responseType);
                repository.addItem(repairReport);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i(throwable, "RepairReport findByDiagnosticReportId onFailure(). {statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.repair_report_error_get)));
            }
        });
    }

    @Override
    public void findByDiagnosticReportIdsIn(List<Long> ids, final IErrorCallback errorCallback) {
        logger.i("Entered RepairReport findByDiagnosticReportIdIn(()");
        String params = new RequestParamGenerator<Long>().generateIDListRequestParams(ids, IRepairReportWebConstants.DIAG_REP_IDS_PARAM);
        httpClient.get(IRepairReportWebResources.FIND_BY_DIAGNOSTIC_REPORT_ID_IN + params, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("RepairReport findByDiagnosticReportIdIn( onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonFactory().createDateFormattingGson();
                String data = "";
                try {
                    data = new EmbeddedJsonExtractor().extractArray(response).toString();
                } catch (JSONException e) {
                    errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.common_error_unexpected)));
                    return;
                }
                Type responseType = new TypeToken<List<RepairReport>>(){}.getType();
                List<RepairReport> repairReportList = gson.fromJson(data, responseType);
                repository.addItems(repairReportList);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i(throwable, "RepairReport findByDiagnosticReportIdIn onFailure(). {statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.repair_report_error_get)));
            }
        });
    }

    @Override
    public void findByEngineerId(Long engineerId, final IErrorCallback errorCallback) {
        logger.i("Entered RepairReport findByEngineerId()");
        RequestParams params = new RequestParams();
        params.put(IRepairReportWebConstants.ENGINEER_ID_PARAM, engineerId);
        httpClient.get(IRepairReportWebResources.FIND_BY_ENGINEER_ID, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("RepairReport findByEngineerId( onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonFactory().createDateFormattingGson();
                String data = "";
                try {
                    data = new EmbeddedJsonExtractor().extractArray(response).toString();
                } catch (JSONException e) {
                    errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.common_error_unexpected)));
                    return;
                }
                Type responseType = new TypeToken<List<RepairReport>>(){}.getType();
                List<RepairReport> repairReportList = gson.fromJson(data, responseType);
                repository.addItems(repairReportList);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i(throwable, "RepairReport findByEngineerId onFailure(). {statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.repair_report_error_get)));
            }
        });
    }
}
