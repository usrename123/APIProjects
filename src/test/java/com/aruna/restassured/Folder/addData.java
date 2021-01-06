package com.aruna.restassured.Folder;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


public class addData {
    public static void main(String[] args) {
        RestAssured.baseURI="https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
String response=given()
.headers("Content-Type","application/json","token","vM7Q5RGI3thO8K0UYyUEGOJpWfizXfkX6L3NVF2wKZbnYXfeon2gfqS7rCE2bmQqECKQXH00sik6tONeCofaah1ILycqeQRjollC","userid","IS23Y3QscNIjcCmGnAg2")

.body("{\"accountno\":\"10203040A\",\"departmentno\":\"1\",\"salary\":\"102,030\",\"pincode\":\"95136\",\"userid\":\"IS23Y3QscNIjcCmGnAg2\"}")
.when().post("addData")
.then()
.assertThat().statusCode(201)
.extract().response().asString();
System.out.println(response);
JsonPath js= new JsonPath(response);
System.out.println( js.getString("status"));
        
    }
    
}