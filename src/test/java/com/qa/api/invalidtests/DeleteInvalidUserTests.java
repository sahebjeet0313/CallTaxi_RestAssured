package com.qa.api.invalidtests;

import com.qa.api.tests.TestBase;
import com.qa.api.utils.LoggerUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteInvalidUserTests extends TestBase {

    @Test(priority = 1)
    public void testDeleteUserWithInvalidData() {
        LoggerUtil.info("Test 10 Started - Delete User with Non-Existent ID");
        LoggerUtil.info("Testing with non-existent user ID...");

        // Test 1: Non-existent user ID
        LoggerUtil.info("Attempting to delete user with non-existent ID (00000)...");
        int nonExistentUserId = 00000;
        Response response1 = given()
                .spec(requestSpec)
                .accept(ContentType.JSON)
                .when()
                .delete("/deleteUserById/" + nonExistentUserId)
                .then()
                .statusCode(404) // Expecting not found for non-existent user
                .contentType(ContentType.JSON)
                .extract()
                .response();

        LoggerUtil.info("DELETE /deleteUserById Response (Non-existent ID): " + response1.asString());
        LoggerUtil.info("Expected Status Code: 404, Actual Status Code: " + response1.getStatusCode());
        Assert.assertTrue(response1.getStatusCode() == 404, "Expected 404 status code for non-existent user ID");
        LoggerUtil.info("Assertion passed: Non-existent user ID correctly rejected with 404 status code");

    }
}
