package com.epam.javacourse.api.trello.response.specifications;

import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matcher;

public interface ApiResponseSpecification {
    ResponseSpecification ok();

    ResponseSpecification okWithBody(Matcher<?> body);

    ResponseSpecification notFound();
}
