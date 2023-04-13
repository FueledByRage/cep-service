package com.example.cepservice.cucumber;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import io.restassured.specification.RequestSpecification;

public class CepSteps {
    private RequestSpecification request;
    private Response response;

    @Given("the following request body:")
    public void given_the_following_request_body(String requestBody) {
        request = RestAssured.given().contentType("application/json").body(requestBody);
    }

    @When("I send a POST request to {string}")
    public void when_i_send_a_post_request(String endpoint) {
        response = request.post(endpoint);
    }

    @Then("the response status code should be {int}")
    public void then_the_response_status_code_should_be(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        Assertions.assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Then("the response body should contain the newly created CEP details")
    public void then_the_response_body_should_contain_the_newly_created_cep_details() {
        // Write code here to verify the response body contains the newly created CEP details
    }
}
