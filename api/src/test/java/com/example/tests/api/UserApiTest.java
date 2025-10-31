package com.example.tests.api;

import com.aventstack.extentreports.Status;
import com.example.framework.api.RestClient;
import com.example.framework.config.ConfigManager;
import com.example.framework.reporting.ExtentManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserApiTest {

    @BeforeClass
    public void before() {
        ExtentManager.initReports(ConfigManager.get("reports.dir", "target/api-reports"));
    }

    @Test(description = "GET /api/users?page=2 (reqres.in)")
    public void testGetUsers() {
        Response res = RestClient.given()
                .when()
                .get("/api/users?page=2")
                .then()
                .extract().response();

        ExtentManager.createTest("API: GET /api/users?page=2").log(Status.INFO, "Status code: " + res.getStatusCode());
        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertTrue(res.jsonPath().getList("data").size() > 0);
        RestClient.reset();
    }

    @Test(description = "POST /api/login success (reqres.in)")
    public void testLoginSuccessful() {
        String payload = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";
        Response res = RestClient.given()
                .body(payload)
                .when()
                .post("/api/login")
                .then()
                .extract().response();

        ExtentManager.createTest("API: POST /api/login").log(Status.INFO, "Status code: " + res.getStatusCode());
        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertNotNull(res.jsonPath().getString("token"));
        RestClient.reset();
    }
}
