package com.aruna.restassured.jira_restAPI;
import org.apache.http.util.Asserts;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import javax.lang.model.util.ElementScanner6;

import io.restassured.path.json.JsonPath;
import io.restassured.RestAssured;


import io.restassured.RestAssured;

public class postman_jira_restAPI {
   static String API_Token="Basic YW5udS5ydW5hQGdtYWlsLmNvbTp5dFZZb2lnNkZEaUt0STUzUEVhNDNBQTI=";
    @Test
    public void Test_case1(){
        RestAssured.baseURI="https://arunaqa.atlassian.net/rest/agile/1.0";
        String response= given().headers("Content-Type", "application/json","Authorization",API_Token).when().get("board/1").then().assertThat().statusCode(200).extract().response().asString();
        System.out.println(response);
        JsonPath js = new JsonPath(response);
        System.out.print(js.getString("data"));

    }

    
    }
    
