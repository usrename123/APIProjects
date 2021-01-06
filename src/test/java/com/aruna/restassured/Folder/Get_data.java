package com.aruna.restassured.Folder;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.apache.http.util.Asserts;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Get_data {
    public static void main(String[] args) {
        
        
        RestAssured.baseURI="https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";

        String response=given()
        .headers("Content-Type","application/json","token","SiOYM36JRGdnD9VOlMXVhffQLE8U9SyYG1tKJ7tPk0ad1VAeJhuitLqP84cng8kCXpFx427OI4oU0xAMOhPUgh70ZMjz2v3JQQBs")
        
        
.when().get("getdata")
.then()
.assertThat().statusCode(200)
.extract().response().asString();
System.out.println(response);
JsonPath js= new JsonPath(response);
System.out.print( js.getString("data"));
Assert.assertEquals(response.contains("{\"accountno\":\"10203040A\",\"departmentno\":\"1\",\"salary\":\"102,030\",\"pincode\":\"95136\",\"userid\":\"IS23Y3QscNIjcCmGnAg2\"}"),true);

    }
    
}