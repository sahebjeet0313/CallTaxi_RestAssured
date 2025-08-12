package com.qa.api.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.api.utils.LoggerUtil;

import java.util.List;

import static io.restassured.RestAssured.given;

public class DeleteUserTests extends TestBase {

    @Test(priority = 6)
    public void testDeleteUser() {
        LoggerUtil.info("Test 6 Started - Delete User By Id");
        Response response = given()
                .spec(requestSpec)
                .accept(ContentType.JSON)
                .when()
                .delete("/deleteUserById/10002")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .response();

        LoggerUtil.info("DELETE /deleteUserById Response: " + response.asString());

        List<Integer> remainingIds = response.jsonPath().getList("userId");
        Assert.assertFalse(remainingIds.contains(10002), "User ID 10002 was not deleted");
    }
}
