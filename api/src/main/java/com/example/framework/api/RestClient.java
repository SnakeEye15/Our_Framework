package com.example.framework.api;

import com.example.framework.config.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

public class RestClient {
    private static final ThreadLocal<RequestSpecification> reqSpec = new ThreadLocal<>();

    public static RequestSpecification given() {
        if (reqSpec.get() == null) {
            RestAssured.baseURI = ConfigManager.get("api.baseUrl", "https://reqres.in");
            RequestSpecification r = RestAssured.given().relaxedHTTPSValidation().contentType("application/json");
            r.filter(new RequestLoggingFilter());
            r.filter(new ResponseLoggingFilter());
            reqSpec.set(r);
        }
        return reqSpec.get();
    }

    public static void reset() {
        reqSpec.remove();
    }
}
