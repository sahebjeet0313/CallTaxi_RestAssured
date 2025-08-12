package com.qa.api.utils;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class AuthUtils {

    public static String getAuthCodeFormUrlEncoded(String username, String password, String loginUrl) {
        LoggerUtil.info("Requesting Auth Code from: " + loginUrl);

        Response response = given()
                .contentType("application/x-www-form-urlencoded")
                .accept("application/json")
                .formParam("username", username)
                .formParam("password", password)
                .when()
                .post(loginUrl);

        LoggerUtil.info("Auth Code Response Status: " + response.getStatusCode());
        LoggerUtil.info("Auth Code Response Body: " + response.getBody().asString());

        return response.jsonPath().getString("auth_code");
    }

    public static String getAccessTokenFormUrlEncoded(String authCode, String tokenUrl) {
        LoggerUtil.info("Requesting Access Token from: " + tokenUrl + " using Auth Code: " + authCode);

        Response response = given()
                .contentType("application/x-www-form-urlencoded")
                .accept("application/json")
                .formParam("auth_code", authCode)
                .when()
                .post(tokenUrl);

        LoggerUtil.info("Access Token Response Status: " + response.getStatusCode());
        LoggerUtil.info("Access Token Response Body: " + response.getBody().asString());

        return response.jsonPath().getString("access_token");
    }
}
