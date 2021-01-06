package com.aruna.restassured.Restassured_testng;

import org.apache.http.util.Asserts;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import javax.lang.model.util.ElementScanner6;

import io.restassured.path.json.JsonPath;
import io.restassured.RestAssured;

public class Restassured_testng {
    String trimed_token;

    @Test(priority = 1)
    public void TC1_token() {
        RestAssured.baseURI = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
        String response = given().headers("Content-Type", "application/json")
                .body("{\"username\":\"Aruna.jul20qa@ta.com\",\"password\":\"Aruna\"}")

                .when().post("login").then().assertThat().statusCode(201).extract().response().asString();
        System.out.println(response);
        JsonPath js = new JsonPath(response);
        System.out.println(js.getString("token"));
        String Token = js.getString("token");
        trimed_token = Token.trim().toString();
System.out.println(trimed_token);

    }

    @Test
    public void TC2_GetData() {
        RestAssured.baseURI = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";

        String response1 = given().headers("Content-Type", "application/json", "token", trimed_token)

                .when().get("getdata").then().assertThat().statusCode(200).extract().response().asString();
        System.out.println(response1);
        JsonPath js = new JsonPath(response1);
        String data=js.getString("data");
        System.out.println(data);
    }

    @Test
    public void TC3_AddData() {
        RestAssured.baseURI = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";

        String post_data = "{\"accountno\":\"10203040B\",\"departmentno\":\"1\",\"salary\":\"102,030\",\"pincode\":\"95136\",\"userid\":\"IS23Y3QscNIjcCmGnAg2\"}";
System.out.println(post_data);
        String response3 = given().headers("Content-Type", "application/json", "token", trimed_token)

                .body(post_data).when().post("addData").then().assertThat().statusCode(201).extract().response()
                .asString();
        System.out.println( response3);
        JsonPath js = new JsonPath(response3);
        System.out.println(js.getString("status"));
        // TO VALIDATE
       
        String response4 = given().headers("Content-Type", "application/json", "token", trimed_token).when()
                .get("getdata").then().assertThat().statusCode(200).extract().response().asString();
        js = new JsonPath(response4);
        //System.out.println(response4.toString());
       Asserts.check(response4.toString().contains("10203040B"), "success");
       

      
    }

}
