package com.epam.javacourse.api.trello.request.specifications;

import com.epam.javacourse.api.trello.models.TrelloList;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;

public class TrelloListSpecification extends TrelloBaseSpecification {
    public RequestSpecification create(TrelloList list) {
        return getRequestSpecificationBuilder()
            .setBasePath("/boards/{boardId}/lists")
            .addPathParam("boardId", list.getBoardId())
            .setBody(list, ObjectMapperType.JACKSON_2)
            .build();
    }

    public RequestSpecification update(String id, TrelloList list) {
        return getRequestSpecificationBuilder()
            .setBasePath("/lists/{id}")
            .addPathParam("id", id)
            .setBody(list, ObjectMapperType.JACKSON_2)
            .build();
    }
}
