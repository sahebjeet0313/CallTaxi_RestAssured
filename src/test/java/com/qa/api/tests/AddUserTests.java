package com.qa.api.tests;

import com.qa.api.utils.LoggerUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddUserTests extends TestBase {

    @Test(priority = 4)
    public void testAddUser() {
        LoggerUtil.info("Test 4 Started - Add User");
        LoggerUtil.info("Adding a new user...");

        Response response = given()
                .spec(requestSpec)
                .contentType("application/x-www-form-urlencoded")
                .accept(ContentType.JSON)
                .formParam("userId", "20001")
                .formParam("firstName", "John")
                .formParam("lastName", "Doe")
                .formParam("emailId", "newuser@test.com")
                .formParam("phoneNumber", "9876543210")
                .formParam("aadhaarNumber", "123456789012")
                .formParam("gender", "Male")
                .when()
                .post("/addUser")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .response();

        LoggerUtil.info("POST /addUser Response: " + response.asString());
        Assert.assertTrue(response.asString().contains("John"));
    }
}