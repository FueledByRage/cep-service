Feature: Test API endpoints
  Scenario: Create a new CEP
    Given the following request body:
      """
      {
        "cep": "abc defg"
      }
      """
    When I send a POST request to "http://localhost:8080/cep-service"
    Then the response status code should be 403
    And the response body should contain the newly created CEP details
