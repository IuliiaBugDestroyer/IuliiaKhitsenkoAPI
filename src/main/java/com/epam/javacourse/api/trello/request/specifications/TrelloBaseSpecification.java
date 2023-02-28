package com.epam.javacourse.api.trello.request.specifications;

import com.epam.javacourse.api.trello.request.specifications.parameters.AuthParams;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

public abstract class TrelloBaseSpecification {
    private static final String ENDPOINT_URL = "https://api.trello.com/1/";
    private static final AuthParams AUTH_PARAMS = new AuthParams();

    protected RequestSpecBuilder getRequestSpecificationBuilder() {
        return new RequestSpecBuilder()
            .setBaseUri(ENDPOINT_URL)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .addQueryParam(AUTH_PARAMS.getApiKeyPropertyName(), AUTH_PARAMS.getApiKey())
            .addQueryParam(AUTH_PARAMS.getApiKeyPropertyName(), AUTH_PARAMS.getApiToken());
    }
}
