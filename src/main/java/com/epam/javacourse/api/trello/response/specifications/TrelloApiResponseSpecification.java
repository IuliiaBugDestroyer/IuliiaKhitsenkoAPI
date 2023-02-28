package com.epam.javacourse.api.trello.response.specifications;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import java.net.HttpURLConnection;
import org.hamcrest.Matcher;

public class TrelloApiResponseSpecification implements ApiResponseSpecification {

    @Override
    public ResponseSpecification ok() {
        return new ResponseSpecBuilder().expectStatusCode(HttpURLConnection.HTTP_OK).build();
    }

    @Override
    public ResponseSpecification okWithBody(Matcher<?> body) {
        return new ResponseSpecBuilder()
            .expectContentType(ContentType.JSON)
            .expectStatusCode(HttpURLConnection.HTTP_OK)
            .expectBody(body)
            .build();
    }

    @Override
    public ResponseSpecification notFound() {
        return new ResponseSpecBuilder()
            .expectStatusCode(HttpURLConnection.HTTP_NOT_FOUND)
            .expectContentType(ContentType.TEXT)
            .build();
    }
}
