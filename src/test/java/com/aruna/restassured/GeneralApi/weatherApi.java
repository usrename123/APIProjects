package com.aruna.restassured.GeneralApi;

import com.aruna.restassured.Restassured_M.Utilities.Utilities.TestBase;
import com.relevantcodes.extentreports.LogStatus;

import org.apache.http.ParseException;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.apache.http.util.Asserts;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class weatherApi extends TestBase{
    Logger log = Logger.getLogger(getClass().getSimpleName());
  public static  String data;
    @Test
    public void weather_API() {

        Response response = get(
                "https://api.openweathermap.org/data/2.5/weather?q=san jose &appid=08879b09b61cbc80bb47626adcbd5331&");

       data = response.asString();
       
        int code=response.statusCode();
        Assert.assertEquals(code, 200);
    
        logger.log(LogStatus.INFO, "success");
        log.info("data is retrived");
        
   log.info("Data is successfully retrived from the weather API for san jose city");
    }
    @Test
    public void details_validation()
            throws FileNotFoundException, ParseException, IOException, org.json.simple.parser.ParseException {
        System.out.println(weather_payload());
        String weather_data=weather_payload();
        JsonPath jp = new JsonPath(weather_data);
          List<String> detail = jp.get("weather.main");
            System.out.println(detail);
            logger.log(LogStatus.INFO, "success"+detail);
            List<String> weatherdata = jp.get("weather.description");
            logger.log(LogStatus.INFO, "success"+weatherdata);
            int weatherid=jp.get("weather[0].id");
            logger.log(LogStatus.INFO, "success"+weatherid);
            int weatherpress=jp.get("main.pressure");
            logger.log(LogStatus.INFO, "success"+weatherpress);
        log.info("all the details were  varified and validated");

    }
    public static String weather_payload() throws FileNotFoundException, IOException, ParseException,
            org.json.simple.parser.ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(
                "C:\\restAssure\\src\\main\\java\\com\\aruna\\restassured\\Restassured_M\\Utilities\\Utilities\\weatherPayload.json"));
        JSONObject jsonObject = (JSONObject) obj;
    
        return jsonObject.toJSONString();
    
}
}
