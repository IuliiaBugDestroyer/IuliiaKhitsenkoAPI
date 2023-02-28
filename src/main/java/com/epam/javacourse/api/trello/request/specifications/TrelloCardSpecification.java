package com.epam.javacourse.api.trello.request.specifications;

import com.epam.javacourse.api.trello.models.TrelloCard;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;

public class TrelloCardSpecification extends TrelloBaseSpecification {

    public RequestSpecification create(TrelloCard card) {
        return getRequestSpecificationBuilder()
            .setBasePath("/cards")
            .setBody(card, ObjectMapperType.JACKSON_2)
            .build();
    }

    public RequestSpecification delete(String id) {
        return getRequestSpecificationBuilder()
            .setBasePath("/cards/{id}")
            .addPathParam("id", id)
            .build();
    }
}
