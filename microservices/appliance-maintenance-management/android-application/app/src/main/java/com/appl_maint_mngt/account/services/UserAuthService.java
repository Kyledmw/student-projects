package com.appl_maint_mngt.account.services;

import android.content.Context;

import com.appl_maint_mngt.MainActivity;
import com.appl_maint_mngt.R;
import com.appl_maint_mngt.account.constants.web.IUserAuthWebConstants;
import com.appl_maint_mngt.account.constants.web.IUserAuthWebResources;
import com.appl_maint_mngt.account.events.IUserAuthEvents;
import com.appl_maint_mngt.account.forms.interfaces.ILoginForm;
import com.appl_maint_mngt.account.models.web.AuthDetails;
import com.appl_maint_mngt.account.models.web.JwtToken;
import com.appl_maint_mngt.account.repositories.interfaces.IAccountUpdateableRepository;
import com.appl_maint_mngt.account.services.interfaces.IUserAuthService;
import com.appl_maint_mngt.common.constants.web.IWebConstants;
import com.appl_maint_mngt.common.errors.ErrorPayloadBuilder;
import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.events.ApplicationEventBus;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.common.models.web.ApiResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

/**
 * Created by Kyle on 16/04/2017.
 */

public class UserAuthService implements IUserAuthService {

    private static final Logger logger = LoggerManager.getLogger(UserAuthService.class);

    private AsyncHttpClient httpClient;
    private Context context;

    private IAccountUpdateableRepository repository;

    public UserAuthService() {
        httpClient = new AsyncHttpClient();
        context = MainActivity.getInstance();

        repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getAccountRepository();
    }

    @Override
    public void postLogin(ILoginForm form, final IErrorCallback errorCallback) {
        logger.i("Entered postLogin()");
        Gson gson = new Gson();
        StringEntity entity = null;
        try {
            entity = new StringEntity(gson.toJson(form));
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, IWebConstants.CONTENT_TYPE_JSON));
        } catch (UnsupportedEncodingException e) {
            logger.e(e, "UnsupportedEncodingException: on %s", gson.toJson(form));
            errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.common_error_unexpected)));
        }

        httpClient.post(context, IUserAuthWebResources.LOGIN_RESOURCE, entity, IWebConstants.CONTENT_TYPE_JSON, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("Login onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonBuilder().create();
                Type responseType = new TypeToken<ApiResponse<JwtToken>>(){}.getType();
                ApiResponse<JwtToken> tokenResponse = gson.fromJson(response.toString(), responseType);
                logger.i("ApiResponse message: %s", tokenResponse.getMessage());
                logger.i("Extracted Data: %s", tokenResponse.getData().getToken());
                repository.updateToken(tokenResponse.getData());
                ApplicationEventBus.getInstance().sendEvent(IUserAuthEvents.LOGIN_EVENT);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i("Login onFailure(). {statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.account_error_login)));
            }
        });
    }

    @Override
    public void getDetails(String token, final IErrorCallback errorCallback) {
        logger.i("Entered getDetails");
        httpClient.addHeader(IUserAuthWebConstants.X_AUTH_HEADER, IUserAuthWebConstants.TOKEN_PREFIX + token);
        httpClient.get(IUserAuthWebResources.DETAILS_RESOURCE, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("Details onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonBuilder().create();
                Type responseType = new TypeToken<ApiResponse<AuthDetails>>(){}.getType();
                ApiResponse<AuthDetails> detailsResponse = gson.fromJson(response.toString(), responseType);
                logger.i("ApiResponse message: %s", detailsResponse.getMessage());
                repository.updateAuth(detailsResponse.getData());
                ApplicationEventBus.getInstance().sendEvent(IUserAuthEvents.AUTH_EVENT);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i("Details onFailure(). {statusCode: %d", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.account_error_details)));
            }
        });
    }
}
