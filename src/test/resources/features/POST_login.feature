Feature: DummyJSON Authentication API
  As a QA engineer
  I want to authenticate against DummyJSON auth API
  So that I can receive access and refresh tokens

  Scenario: Successful login for user emilys
    When User makes POST request to LOGIN endpoint with the next dummyjson_login_request payload
    Then client receives 200 response code
    And client receives expected "dummyjson_login_response" response body