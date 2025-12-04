Feature: DummyJSON Users API
  As a QA engineer
  I want to retrieve users from the DummyJSON Users API
  So that I can validate the users collection and its metadata

  Scenario: Successful retrieval of users list
    When User makes GET request to USERS endpoint
    Then client receives 200 response code
    And client receives expected Users "dummyjson_users_response" response body