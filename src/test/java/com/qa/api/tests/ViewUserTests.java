package com.qa.api.tests;

import com.qa.api.utils.LoggerUtil;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ViewUserTests extends TestBase {

    @Test(priority = 1)
    public void viewUserList() {
        LoggerUtil.info("Test 1 Started - Get Users (viewUserList)");
        LoggerUtil.info("Fetching all users...");
        Response getResponse = given()
                .spec(requestSpec)
                .accept(ContentType.JSON)
                .when()
                .get("/viewUserList")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .response();

        LoggerUtil.info("GET /viewUserList Response: " + getResponse.asString());
        List<Map<String, Object>> users = getResponse.as(new TypeRef<List<Map<String, Object>>>() {
        });

        LoggerUtil.info("Users fetched: " + users.size());
        Assert.assertTrue(users.size() > 0, "No users found");
    }

    @Test(priority = 2)
    public void viewUserById() {
        int userId = 10001;
        LoggerUtil.info("Test 2 Started - Get User By Id");
        LoggerUtil.info("Fetching user by ID: " + userId);
        Response getByIdResponse = given()
                .spec(requestSpec)
                .accept(ContentType.JSON)
                .when()
                .get("/viewUserById/" + userId)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .response();

        LoggerUtil.info("GET /viewUserById Response: " + getByIdResponse.asString());
        Map<String, Object> user = getByIdResponse.as(new TypeRef<Map<String, Object>>() {
        });

        LoggerUtil.info("User details: " + user);
        Assert.assertEquals(user.get("userId"), userId);
    }

    @Test(priority = 3)
    public void viewUserByGender() {
        String gender = "Male";
        LoggerUtil.info("Test 3 Started - Get Users By Gender");
        LoggerUtil.info("Fetching users by gender: " + gender);
        Response getByGenderResponse = given()
                .spec(requestSpec)
                .accept(ContentType.JSON)
                .when()
                .get("/viewUserByGender/" + gender)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .response();

        LoggerUtil.info("GET /viewUserByGender Response: " + getByGenderResponse.asString());
        List<Map<String, Object>> users = getByGenderResponse.as(new TypeRef<List<Map<String, Object>>>() {
        });

        Assert.assertTrue(users.stream().allMatch(u -> gender.equals(u.get("gender"))),
                "Some users are not " + gender);
    }
}
