package com.epam.javacourse.api.trello.request.specifications;

import com.epam.javacourse.api.trello.models.TrelloBoard;
import com.epam.javacourse.api.trello.request.specifications.query.TrelloBoardReadParams;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;

public class TrelloBoardSpecification extends TrelloBaseSpecification {

    @Override
    protected RequestSpecBuilder getRequestSpecificationBuilder() {
        return super.getRequestSpecificationBuilder().setBasePath("/boards");
    }

    protected RequestSpecBuilder getItemRequestSpecificationBuilder() {
        return getRequestSpecificationBuilder().setBasePath("/boards/{id}");
    }

    public RequestSpecification create(TrelloBoard board) {
        return getRequestSpecificationBuilder()
            .setBody(board, ObjectMapperType.JACKSON_2)
            .build();
    }

    public RequestSpecification read(String id) {
        return getItemRequestSpecificationBuilder().addPathParam("id", id).build();
    }

    public RequestSpecification read(String id, TrelloBoardReadParams params) {
        return getItemRequestSpecificationBuilder()
            .addPathParam("id", id)
            .addQueryParams(params.asQueryParams())
            .build();
    }

    public RequestSpecification update(String id, TrelloBoard board) {
        return getItemRequestSpecificationBuilder()
            .addPathParam("id", id)
            .setBody(board, ObjectMapperType.JACKSON_2)
            .build();
    }

    public RequestSpecification delete(String id) {
        return getItemRequestSpecificationBuilder()
            .addPathParam("id", id)
            .build();
    }
}
