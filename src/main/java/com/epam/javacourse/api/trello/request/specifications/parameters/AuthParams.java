package com.epam.javacourse.api.trello.request.specifications.parameters;

import lombok.Getter;

@Getter
public class AuthParams {
    private final String apiKeyPropertyName = "key";
    private String apiKey = System.getenv(apiKeyPropertyName);

    private final String apiTokenPropertyName = "token";
    private String apiToken = System.getenv(apiTokenPropertyName);
}
