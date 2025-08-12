package com.qa.api.invalidtests;

import com.qa.api.tests.TestBase;
import com.qa.api.utils.LoggerUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddInvalidUserTests extends TestBase {

    @Test(priority = 1)
    public void testAddUserWithInvalidData() {
        LoggerUtil.info("Test 7 Started - Add User with Invalid Email Format");
        LoggerUtil.info("Testing with invalid email format...");

        // Test 1: Invalid Email Format
        LoggerUtil.info("Attempting to add user with invalid email format (invalid-email-format)...");
        Response response2 = given()
                .spec(requestSpec)
                .contentType("application/x-www-form-urlencoded")
                .accept(ContentType.JSON)
                .formParam("userId", "20002")
                .formParam("firstName", "Jane")
                .formParam("lastName", "Smith")
                .formParam("emailId", "invalid-email-format") // Invalid email format
                .formParam("phoneNumber", "9876543210")
                .formParam("aadhaarNumber", "123456789012")
                .formParam("gender", "Female")
                .when()
                .post("/addUser")
                .then()
                .statusCode(400) // Expecting bad request for invalid email
                .contentType(ContentType.JSON)
                .extract()
                .response();

        LoggerUtil.info("POST /addUser Response (Invalid Email): " + response2.asString());
        LoggerUtil.info("Expected Status Code: 400, Actual Status Code: " + response2.getStatusCode());
        Assert.assertTrue(response2.getStatusCode() == 400, "Expected 400 status code for invalid email format");
        LoggerUtil.info("Assertion passed: Invalid email format correctly rejected with 400 status code");
    }
}
