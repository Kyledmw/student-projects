package com.appl_maint_mngt.account.services;

import android.content.Context;

import com.appl_maint_mngt.MainActivity;
import com.appl_maint_mngt.R;
import com.appl_maint_mngt.account.constants.web.IUserProfileWebResources;
import com.appl_maint_mngt.account.events.IUserProfileEvents;
import com.appl_maint_mngt.account.models.web.UserProfile;
import com.appl_maint_mngt.account.repositories.interfaces.IAccountUpdateableRepository;
import com.appl_maint_mngt.account.services.interfaces.IUserProfileService;
import com.appl_maint_mngt.common.errors.ErrorPayloadBuilder;
import com.appl_maint_mngt.common.errors.interfaces.IErrorCallback;
import com.appl_maint_mngt.common.events.ApplicationEventBus;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.common.models.web.ApiResponse;
import com.appl_maint_mngt.common.utility.GsonFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import org.json.JSONObject;

import java.lang.reflect.Type;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Kyle on 16/04/2017.
 */

public class UserProfileService implements IUserProfileService {

    private static final Logger logger = LoggerManager.getLogger(UserProfileService.class);

    private AsyncHttpClient httpClient;
    private Context context;

    private IAccountUpdateableRepository repository;

    public UserProfileService() {
        httpClient = new AsyncHttpClient();
        context = MainActivity.getInstance();

        repository = IntegrationController.getInstance().getRepositoryController().getUpdateableRepositoryRetriever().getAccountRepository();
    }

    @Override
    public void get(Long id, final IErrorCallback errorCallback) {
        logger.i("Entered get(): Id: %d", id);
        httpClient.get(IUserProfileWebResources.PROFILE_GET_RESOURCE + id, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                logger.i("Profile get onSuccess(). {statusCode: %d, response: %s}", statusCode, response.toString());
                Gson gson = new GsonFactory().createDateFormattingGson();
                Type responseType = new TypeToken<ApiResponse<UserProfile>>(){}.getType();
                ApiResponse<UserProfile> profileResponse = gson.fromJson(response.toString(), responseType);
                logger.i("ApiResponse message: %s", profileResponse.getMessage());
                repository.updateProfile(profileResponse.getData());
                ApplicationEventBus.getInstance().sendEvent(IUserProfileEvents.GET_EVENT);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                logger.i(throwable, "Profile get onFailure(). {statusCode: %d}", statusCode);
                if(response != null) logger.i("Response: %s", response.toString());
                errorCallback.callback(new ErrorPayloadBuilder().buildForString(context.getString(R.string.account_error_login)));
            }
        });
    }
}