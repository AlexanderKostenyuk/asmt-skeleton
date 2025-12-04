package com.asmt.qa.cucumber.definitions;

import static com.asmt.qa.utils.ScenarioContext.Context.REQUEST;
import static com.asmt.qa.utils.ScenarioContext.Context.RESPONSE;
import static org.assertj.core.api.Assertions.assertThat;

import com.asmt.qa.factory.CommonRequestResponseFactory;
import com.asmt.qa.rest.api.DummyjsonApiClient;
import com.asmt.qa.rest.api.DummyjsonEndpoints;
import com.asmt.qa.rest.models.requests.DummyjsonLoginRequest;
import com.asmt.qa.rest.models.responses.DummyjsonLoginResponse;
import com.asmt.qa.rest.models.responses.DummyjsonUsersResponse;
import com.asmt.qa.utils.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class DummyjsonDefinitions {

    private final DummyjsonApiClient dummyjsonApiClient;
    private final ScenarioContext scenarioContext;

    @When("User makes GET request to {} endpoint")
    public void getRequest(DummyjsonEndpoints endpoint) {
        Response response = dummyjsonApiClient.get(endpoint);
        scenarioContext.set(RESPONSE, response);
        log.info("Response: {}", response.prettyPeek());
    }

    @When("User makes POST request to {} endpoint with the next {} payload")
    public void postRequest(DummyjsonEndpoints endpoint, String payload) {
        String fileKey = "dummyjson/" + payload;
        DummyjsonLoginRequest requestPayload = CommonRequestResponseFactory.readRequest(DummyjsonLoginRequest.class, fileKey);
        Response response = dummyjsonApiClient.post(endpoint, requestPayload);
        scenarioContext.set(RESPONSE, response);
        log.info("Response body: {}", response.prettyPeek());
    }

    @Then("client receives {} response code")
    public void validateStatusCode(int responseCode) {
        Response response = scenarioContext.get(RESPONSE);
        response.then().statusCode(responseCode);
    }

    @Then("client receives expected {string} response body")
    public void validateResponseBody(String payload){
        String fileKey = "dummyjson/" + payload;
        DummyjsonLoginResponse expectedResponse = CommonRequestResponseFactory.readResponse(fileKey, DummyjsonLoginResponse.class);

        Response response = scenarioContext.get(RESPONSE);
        DummyjsonLoginResponse actualResponse = response.as(DummyjsonLoginResponse.class);

        assertThat(actualResponse)
            .as("Response body does not match the expected result")
            .usingRecursiveComparison()
            .ignoringCollectionOrder()
            .ignoringFields("id", "accessToken", "refreshToken")
            .isEqualTo(expectedResponse);
        
    }

    @Then("client receives expected Users {string} response body")
    public void validateUsersResponseBody(String payload){
        String fileKey = "dummyjson/" + payload;
        DummyjsonUsersResponse expectedResponse = CommonRequestResponseFactory.readResponse(fileKey, DummyjsonUsersResponse.class);

        Response response = scenarioContext.get(RESPONSE);
        DummyjsonUsersResponse actualResponse = response.as(DummyjsonUsersResponse.class);

        assertThat(actualResponse)
            .as("Response body does not match the expected result")
            .usingRecursiveComparison()
            .ignoringCollectionOrder()
            .ignoringFields("id", "accessToken", "refreshToken")
            .isEqualTo(expectedResponse);

    }
}
