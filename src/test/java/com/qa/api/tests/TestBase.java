package com.qa.api.tests;

import com.qa.api.utils.AuthUtils;
import com.qa.api.utils.ConfigReader;
import com.qa.api.utils.LoggerUtil;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;

public class TestBase {

        protected static RequestSpecification requestSpec;
        protected static String accessToken;
        protected static String baseURI;
        private static volatile boolean suiteInitialized = false;

        @BeforeSuite
        public synchronized void setupSuite() {
                if (suiteInitialized) {
                        return;
                }
                LoggerUtil.info("===== Setting up API Test Base =====");

                baseURI = ConfigReader.getProperty("baseURI");
                RestAssured.baseURI = baseURI;
                LoggerUtil.info("Base URI set to: " + baseURI);

                String authBaseURI = ConfigReader.getProperty("authBaseURI");
                String loginPath = ConfigReader.getProperty("authLogin");
                String tokenPath = ConfigReader.getProperty("authToken");

                String username = ConfigReader.getProperty("username");
                String password = ConfigReader.getProperty("password");

                // Step 1 - Get Auth Code
                String authCode = AuthUtils.getAuthCodeFormUrlEncoded(
                                username,
                                password,
                                authBaseURI + loginPath);
                LoggerUtil.info("Auth Code: " + authCode);

                // Step 2 - Get Access Token
                accessToken = AuthUtils.getAccessTokenFormUrlEncoded(
                                authCode,
                                authBaseURI + tokenPath);
                LoggerUtil.info("Access Token: " + accessToken);

                // Create a reusable request spec for all tests
                requestSpec = RestAssured
                                .given()
                                .baseUri(baseURI)
                                .header("Authorization", "Bearer " + accessToken)
                                .log().ifValidationFails(LogDetail.ALL);
                suiteInitialized = true;
        }
}
