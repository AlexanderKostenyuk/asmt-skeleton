asmt-skeleton

A project skeleton for BDD/API tests based on Gradle + JUnit Platform + Cucumber 7 + Spring (Boot Test) + Rest Assured with Allure reporting.

Contents
- What’s inside
- Project structure
- Configuration (YAML, profiles)
- How to run tests
- Reports (Allure, Cucumber HTML/JSON)
- Working with request/response fixtures
- Adding new APIs and steps
- Useful commands
- Troubleshooting

What’s inside
- Cucumber 7 (JUnit Platform engine) to run .feature files
- Spring Boot Test for DI and configuration binding (@ConfigurationProperties)
- Rest Assured for HTTP
- Allure Reports with step-level attachments (status/content-type/body)

Project structure
This project uses custom sourceSets (Java sources under src/main/code and src/test/code):

- build.gradle — dependencies, plugins, test and Allure settings
- src/main/code
  - com.asmt.qa.props — properties and their registration
    - DummyjsonProperties — @ConfigurationProperties("dummyjson")
    - PropertiesConfig — @EnableConfigurationProperties
  - com.asmt.qa.rest — HTTP clients/models
    - api — DummyjsonApiClient, DummyjsonEndpoints
    - models.requests — DummyjsonLoginRequest
    - models.responses — DummyjsonLoginResponse, DummyjsonUsersResponse
  - com.asmt.qa.utils — JsonUtils, FileUtil, ScenarioContext
- src/main/resources
  - application.yml — active profile
  - application-local.yml — local settings (DummyJSON base URL)
- src/test/code
  - com.asmt.qa.cucumber
    - CucumberTest — JUnit Platform Suite for Cucumber
    - CucumberSpringConfiguration — boots Spring Boot test context
    - AllureHooks — attaches response artifacts (status/content-type/body) to Allure
    - definitions — step definitions (DummyjsonDefinitions)
- src/test/resources
  - features — .feature files (POST_login.feature, GET_users.feature)
  - requests/dummyjson — request bodies (*.json)
  - responses/dummyjson — expected responses (*.json)

Configuration (YAML, profiles)
- src/main/resources/application.yml
  spring.profiles.active: local
- src/main/resources/application-local.yml
  dummyjson.url: https://dummyjson.com

The "local" profile is activated during Gradle test runs (see build.gradle → test.systemProperty), so Spring will automatically bind DummyjsonProperties.url at runtime.

How to run tests
- From the project root:
  ./gradlew test

Test runner class: src/test/code/com/asmt/qa/cucumber/CucumberTest.java

Reports
- Allure
  1) Running tests produces raw results under build/allure-results
  2) Generate HTML report: ./gradlew allureReport
  3) Open locally: ./gradlew allureServe (starts a local web server)

  Allure automatically attaches:
  - HTTP Status (status line)
  - Content-Type
  - Response Body (as JSON when applicable)

- Built-in Cucumber report
  - HTML: build/reports/cucumber/cucumber.html
  - JSON: build/reports/cucumber/cucumber.json

Working with request/response fixtures
- JSON files are read via CommonRequestResponseFactory:
  - requests: src/test/resources/requests/{namespace}/{name}.json
  - responses: src/test/resources/responses/{namespace}/{name}.json

Example (POST /auth/login):
- Request: requests/dummyjson/dummyjson_login_request.json
- Expected response: responses/dummyjson/dummyjson_login_response.json

Adding new APIs and steps
1) Endpoint
   - Add a value to DummyjsonEndpoints enum with the corresponding path
2) Client
   - Implement a method in DummyjsonApiClient (GET/POST, etc.)
3) Models
   - Create a record for request/response under com.asmt.qa.rest.models.requests|responses
   - Add @JsonIgnoreProperties(ignoreUnknown = true) to be resilient to extra fields
4) Cucumber steps
   - Add methods in definitions/DummyjsonDefinitions
   - Use ScenarioContext to store REQUEST/RESPONSE between steps
5) Features and fixtures
   - Create a .feature under src/test/resources/features
   - Add JSON in requests/responses following existing examples

Useful commands
- Run tests: ./gradlew test
- Allure report: ./gradlew allureReport
- Allure local server: ./gradlew allureServe
- Clean: ./gradlew clean

Troubleshooting
- No results visible in Gradle test
  Ensure the following are present:
  - testRuntimeOnly 'org.junit.platform:junit-platform-suite-engine'
  - testImplementation 'io.cucumber:cucumber-junit-platform-engine'

- Base URI cannot be null
  Check dummyjson.url configuration (local profile is active). The client may apply safe defaults, but correct configuration is preferred.

- SLF4J: No providers were found / NOP binding
  A runtime SLF4J binding (slf4j-simple) is included. If you still see warnings, verify your runtime picks it up.

License
— Internal project skeleton. Add license information if required.
