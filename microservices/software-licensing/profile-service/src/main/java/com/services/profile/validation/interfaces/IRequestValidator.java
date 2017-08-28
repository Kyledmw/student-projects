package com.services.profile.validation.interfaces;

import java.util.List;

import io.vertx.ext.web.RoutingContext;

public interface IRequestValidator {

    void validate(RoutingContext routingContext);

    boolean isValid();

    List<String> getErrorMessages();
}