package com.aruna.restassured.Folder;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.path.json.JsonPath;
import  io.restassured.RestAssured;

/**
 * given => All input details
 * when => submission of API
 * then => vallidaton of response
 */
public class Get_Token 
{
    public static void main( String[] args ){
RestAssured.baseURI="https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
String response=given()
.headers("Content-Type","application/json")
.body("{\"username\":\"Aruna.jul20qa@ta.com\",\"password\":\"Aruna\"}")

.when().post("login")
.then()
.assertThat().statusCode(201)
.extract().response().asString();
 System.out.println(response);
JsonPath js= new JsonPath(response);

String token= js.getString("token");


        
        
    }
    
}