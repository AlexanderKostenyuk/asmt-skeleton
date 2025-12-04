package com.asmt.qa.cucumber;

import static com.asmt.qa.utils.ScenarioContext.Context.RESPONSE;

import com.asmt.qa.utils.ScenarioContext;
import io.cucumber.java.AfterStep;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AllureHooks {

    private final ScenarioContext scenarioContext;

    @AfterStep
    public void attachResponseArtifacts() {
        Response response = scenarioContext.get(RESPONSE);
        if (response != null) {
            try {
                Allure.addAttachment("HTTP Status", response.getStatusLine());
            } catch (Exception ignored) { }
            try {
                Allure.addAttachment("Content-Type", response.getContentType());
            } catch (Exception ignored) { }
            try {
                String contentType = response.getContentType();
                String body = response.getBody() != null ? response.getBody().asPrettyString() : "";
                if (contentType != null && contentType.contains("json")) {
                    Allure.addAttachment("Response Body", "application/json", body);
                } else {
                    Allure.addAttachment("Response Body", body);
                }
            } catch (Exception ignored) { }
        }
    }
}
