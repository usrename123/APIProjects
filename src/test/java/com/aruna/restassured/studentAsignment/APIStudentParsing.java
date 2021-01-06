package com.aruna.restassured.studentAsignment;

import static io.restassured.RestAssured.given;

import java.util.List;

import com.aruna.restassured.Restassured_M.Utilities.Utilities.TestBase;
import com.relevantcodes.extentreports.LogStatus;

import org.apache.http.util.Asserts;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class APIStudentParsing extends TestBase {
    Logger log = Logger.getLogger(getClass().getSimpleName());
    @Test
    public void Student_payload_validation() {

        RestAssured.baseURI = "http://localhost:3000/";

        String response1 = given().headers("Content-Type", "application/json")

                .when().get("studentdata1").then().assertThat().statusCode(200).extract().response().asString();
        System.out.println(response1);
        JsonPath js = new JsonPath(response1);
        System.out.print(js.getString("studentdata1"));
        List<String> allstudents = js.get("Students");
        System.out.println(allstudents);
        int StudentsArray_size=js.get("Students.size()");
        System.out.println(StudentsArray_size);
        int ID_student = js.get("Students.UserId[8]");
        System.out.println(ID_student);
    Asserts.check(allstudents.toString().contains("UserId=7") , "success");  //Two ways of writing assretions
    Asserts.check(js.get("Students.UserId[0]").toString().contains("7"), "success");
    
    String title = js.get("Students.Title[8]");
    System.out.println(title);
    log.info("student payload was successfully parsed"+allstudents);
    log.info("student payload contained required user Id and was successfully validated"+ID_student);
    log.info("student payload contained required user Id and title was retrieved"+ID_student);
    logger.log(LogStatus.PASS, "stuedent list was retrieved");
    logger.log(LogStatus.PASS, "given student ID was validated ,title was retrieved");
    
        }
    
}
