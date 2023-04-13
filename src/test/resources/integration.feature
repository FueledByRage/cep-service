Feature: Test API endpoints
  Scenario: Create a new CEP
    Given the following request body:
      """
      {
        "cep": "01153 000"
      }
      """
    When I send a POST request to "http://localhost:8080/cep-service"
    Then the response status code should be 200
    And the response body should contain the newly created CEP details
