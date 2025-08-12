package com.qa.api.tests;

import com.qa.api.utils.LoggerUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateUserTests extends TestBase {

    @Test(priority = 5)
    public void testUpdateUserPhoneNumber() {
        LoggerUtil.info("Test 5 Started - Update User Phone Number");
        LoggerUtil.info("Updating phone number for user ID 10001...");

        Response response = given()
                .spec(requestSpec)
                .contentType("application/x-www-form-urlencoded")
                .accept(ContentType.JSON)
                .formParam("userId", "10001")
                .formParam("firstName", "Raj")
                .formParam("lastName", "Kumar")
                .formParam("emailId", "user1@test.com")
                .formParam("phoneNumber", "9999999999")
                .formParam("aadhaarNumber", "123400000011")
                .formParam("gender", "Male")
                .when()
                .put("/updateUserNumber")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .response();

        LoggerUtil.info("PUT /updateUserNumber Response: " + response.asString());
        Assert.assertTrue(response.asString().contains("9999999999"));
    }
}
