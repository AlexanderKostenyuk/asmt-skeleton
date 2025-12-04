package com.asmt.qa.rest.api;

import com.asmt.qa.props.DummyjsonProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DummyjsonApiClient {

    private final DummyjsonProperties dummyjsonProperties;

    private RequestSpecification getClient() {
        return RestAssured
            .given()
            .relaxedHTTPSValidation()
            .baseUri(dummyjsonProperties.url())
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .redirects()
            .follow(true);
    }

    public Response get(DummyjsonEndpoints endpoint) {
        return getClient()
            .when()
            .get(endpoint.getPath())
            .thenReturn();
    }

    public Response post(DummyjsonEndpoints endpoint, Object body) {
        return getClient()
            .body(body)
            .when()
            .post(endpoint.getPath())
            .thenReturn();
    }
}
