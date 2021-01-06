package com.aruna.restassured.complexJsonValidations;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.aruna.restassured.Restassured_M.Utilities.Utilities.TestBase;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class payload_validation extends TestBase {
    @Test
    public void PayLoadValidation() throws FileNotFoundException, IOException, ParseException {
        System.out.println(payload());
        String response = payload();
        JsonPath jp = new JsonPath(response);   

        int count = jp.getInt("courses.size()");
        System.out.println("The total count of courses in Array are = " + count);

        int ActualtotalAmount = 0;
        System.out.println("The list of  prices for all the courses repectively is as follows");
        for (int i = 0; i < count; i++) {


            System.out.println(jp.getInt("courses[" + i + "].price"));//This is the way of defining the value of i by putting "+i+"

            int value = jp.getInt("courses[" + i + "].price") * jp.getInt("courses[" + i + "].copies");

            ActualtotalAmount = ActualtotalAmount + value;
        }
        System.out.println("The total value of copies is  $" + ActualtotalAmount);

        int ExpecPurAmount = jp.getInt("dashboard.purchaseAmount");
        // System.out.println(ExpecPurAmount);

        Assert.assertEquals(ActualtotalAmount, ExpecPurAmount, "true");
        
        
        System.out.println("===");
        String title_firstcourse = jp.getString("courses[0].title");
        System.out.println("This tittle is at the index no.[0]  " + title_firstcourse);
        System.out.println("Name of all the titles are as follows");
        for (int i = 0; i < count; i++) {

            System.out.println(jp.getString("courses[" + i + "].title"));
        }
        System.out.println("total number of copies are for each course respectively are as follows");
        for (int j = 0; j < count; j++) {

            System.out.println(jp.getInt("courses[" + j + "].copies"));
        }
        System.out.println("=====");
        int courseValue = jp.getInt("courses[0].price");

        System.out.println("This is the value of the course at the index no.[0] $ " + courseValue);
        
    }

    public static String payload() throws FileNotFoundException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(
                "C:\\TecArcTrainning\\TAGitHubTraining\\Aruna_Bindra\\restAssure\\src\\main\\java\\com\\aruna\\restassured\\Restassured_M\\Utilities\\complex.json"));
        JSONObject jsonObject = (JSONObject) obj;

        return jsonObject.toJSONString();
    }

}
