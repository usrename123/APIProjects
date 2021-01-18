package com.aruna.restassured.GeneralApi;

import com.aruna.restassured.Restassured_M.Utilities.Utilities.TestBase;
import com.relevantcodes.extentreports.LogStatus;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class weatherApi extends TestBase {
    Logger log = Logger.getLogger(getClass().getSimpleName());

    @Test
    public void weather_API() {

        Response response = RestAssured.get(
                "https://api.openweathermap.org/data/2.5/weather?q=san jose &appid=08879b09b61cbc80bb47626adcbd5331&");

        String data = response.asString();
        System.out.println(data);
        int code=response.statusCode();
        Assert.assertEquals(code, 200);
        logger.log(LogStatus.INFO, "success");
   log.info("Data is successfully retrived from the weather API for san jose city");
    }
    
}
