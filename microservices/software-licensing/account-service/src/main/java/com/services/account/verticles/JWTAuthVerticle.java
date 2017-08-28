package com.services.account.verticles;

import com.services.account.constants.IAuthConstants;
import com.services.account.constants.IEventBusAddresses;
import com.services.account.constants.IModelFieldConstants;
import com.services.account.constants.IValidationConstants;
import com.services.account.strings.IErrorMessages;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTOptions;

public class JWTAuthVerticle extends AbstractVerticle {

    private final static String JWT_AUTH_KEY = "jwt";

    private final static String KEYSTORE_CONFIG_KEY = "keyStore";

    private final static String TYPE_KEY = "type";
    private final static String PATH_KEY = "path";
    private final static String PASSWORD_KEY = "password";

    private final static String KEYSTORE_TYPE = "jceks";
    private final static String KEYSTORE_FILENAME = "keystore.jceks";
    private final static String PASSWORD = "secret";

    private final static long SECONDS_TO_EXPIRE_TOKEN = 300;

    private JWTAuth _jwt;
    private EventBus _eb;


    @Override
    public void start(Future<Void> startFuture) {
        JsonObject keyStoreConfig = new JsonObject();
        keyStoreConfig.put(TYPE_KEY, KEYSTORE_TYPE);
        keyStoreConfig.put(PATH_KEY, KEYSTORE_FILENAME);
        keyStoreConfig.put(PASSWORD_KEY, PASSWORD);

        JsonObject config = new JsonObject().put(KEYSTORE_CONFIG_KEY, keyStoreConfig);

        _jwt = JWTAuth.create(vertx, config);

        _eb = vertx.eventBus();

        _eb.consumer(IEventBusAddresses.TOKEN_EB_ADDRESS_AUTH, message -> {
            System.out.println("Received a token auth request");
            JsonObject authInfo = new JsonObject().put(JWT_AUTH_KEY, message.body());
            _jwt.authenticate(authInfo, res -> {
                if(res.failed()) {
                    message.reply(getTokenErrorObj());
                } else {
                    String userId = res.result().principal().getString(IModelFieldConstants.USER_ID_KEY);
                    message.reply(new JsonObject().put(IValidationConstants.SUCCESS_KEY, true).put(IModelFieldConstants.USER_ID_KEY, userId));
                }
            });
        });

        _eb.consumer(IEventBusAddresses.TOKEN_EB_ADDRESS_GENERATE, message -> {
            JsonObject messageBody = (JsonObject) message.body();
            String userId = messageBody.getString(IModelFieldConstants.USER_ID_KEY);

            JsonObject claims = new JsonObject().put(IModelFieldConstants.USER_ID_KEY, userId);
            String token = _jwt.generateToken(claims, new JWTOptions().setExpiresInSeconds(SECONDS_TO_EXPIRE_TOKEN));

            message.reply(new JsonObject().put(IAuthConstants.TOKEN_KEY, token));
        });
    }

    private JsonObject getTokenErrorObj() {
        JsonArray errMessages = new JsonArray().add(IErrorMessages.INVALID_TOKEN);
        JsonObject errors = new JsonObject().put(IValidationConstants.SUCCESS_KEY, false).put(IValidationConstants.MESSAGES_KEY, errMessages);
        return errors;
    }
}
