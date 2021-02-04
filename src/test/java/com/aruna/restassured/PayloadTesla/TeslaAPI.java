package com.aruna.restassured.PayloadTesla;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.aruna.restassured.Restassured_M.Utilities.Utilities.TestBase;
import com.relevantcodes.extentreports.LogStatus;

import org.apache.http.util.Asserts;

import org.testng.annotations.Test;
import org.apache.log4j.Logger;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class TeslaAPI extends TestBase{
    Logger log = Logger.getLogger(getClass().getSimpleName());
    int count = 0;
        public static String server= "http://localhost:3000/";
        public static String response;
        public static String Parsed_cardata;
        
    @Test(priority=0)
    public void Parsing_data() {
      
        RestAssured.baseURI = server;
        response= given().headers("Content-Type", "application/json")
        .when().get("cars").then().assertThat().statusCode(200).extract().response().asString();
        log.info("get the status code of the response");
        System.out.println(response);
        JsonPath jp = new JsonPath(response);
         Parsed_cardata = jp.get().toString();
        System.out.println(Parsed_cardata);
        logger.log(LogStatus.PASS, " local server @port 3000 was started and Data was successfully parsed");
        log.info("local server @port 3000 was started and Data was successfully parsed");

       

    }

   @Test(priority=1)
   
    public void Get_All_BlueCars() {
        System.out.println("===============================");
        String make="Tesla";
        String carColor="Blue";
        RestAssured.baseURI = server;
        response= given().headers("Content-Type", "application/json")
        .when().get("cars").then().assertThat().statusCode(200).extract().response().asString();
        
        System.out.println(response);
        JsonPath jp = new JsonPath(response);
         Parsed_cardata = jp.get().toString();
        List<String> cList= jp.get("Car");
        int carSize=jp.getInt("Car.size()");
        System.out.println(carSize);
        log.info("number of cars available are"+carSize);
        List<String>metadata= jp.get("Car.metadata");
        log.info("Print the metadata"+metadata);
        Asserts.check(cList.toString().contains(make), "true");
        System.out.println(metadata);
        List<String> allCarmakesList=jp.get("Car.make");
        log.info("get list of all the makes"+allCarmakesList);
        System.out.println(allCarmakesList);
        
        for (int i = 0; i < allCarmakesList.size(); i++) {
            if (allCarmakesList.get(i).equalsIgnoreCase(make)) {
                count = i;
                //System.out.println(i);
                String Col_AllCars = jp.getString("Car[" + count + "].metadata.Color");  //will get color of cars with the make Tesla cause of count=i
                System.out.println("Printing  cars make equals  : " + Col_AllCars);
                log.info("print the number of blue tesla cars"+Col_AllCars);
                System.out.println("Printing the number of blue tesla cars : " + Col_AllCars.length());
                log.info("print the number of blue tesla cars"+Col_AllCars.length());
                if (Col_AllCars.contains(carColor)) {
                    System.out.println("Printing the car colors that equals: " + allCarmakesList.get(i));
                    System.out.println("printing only "+make+" Of  "+ Col_AllCars+" colour");
                    String CarNotes = jp.getString("Car[" + count + "].metadata.Notes"); 
                    System.out.println("printing notes for :"+make+CarNotes+ " :whoes colour is: "+Col_AllCars);
                    log.info("print notes"+make+CarNotes);
                    logger.log(LogStatus.PASS, "details");
                    
                 }

             }
        }
      
}
@Test(priority=2)
public void  PerDay_Rental(){
    System.out.println("===============================");
       
        RestAssured.baseURI = server;
        response= given().headers("Content-Type", "application/json")
        .when().get("cars").then().assertThat().statusCode(200).extract().response().asString();
        
        System.out.println(response);
        JsonPath jp = new JsonPath(response);
        List<Float> allCarsRents= jp.get("Car.perdayrent");
        log.info("print notes"+allCarsRents);
        logger.log(LogStatus.PASS, "got perday rent of all the cars");
        System.out.println(allCarsRents);
        //price only=140
        //discount=15
        //price after discount=140-15=125
        List<Float> price= jp.get("Car.perdayrent.Price");
        System.out.println(price);
        List<Float> discount= jp.get("Car.perdayrent.Discount");
        System.out.println(discount);
        log.info("print price and discount amount"+price+discount);
        logger.log(LogStatus.PASS, "got perday rent and discount of all the cars");
        ArrayList<Float>discounList = new ArrayList<>();
        ArrayList<Float>priceList = new ArrayList<>();
         count=0;
         for(int i=0;i<allCarsRents.size();i++){
             String vinNumber=jp.get("Car["+i+"].perdayrent.vin");
             Float perdayPrice=jp.getFloat("Car["+i+"].perdayrent.Price");
             Float PerdayDiscount=jp.getFloat("Car["+i+"].perdayrent.Discount");
             Float PreDayRentAfterDiscount=(perdayPrice-(perdayPrice*PerdayDiscount/100));
            
             priceList.add(perdayPrice);
             discounList.add(PreDayRentAfterDiscount);
             Collections.sort(priceList);
             System.out.println(priceList);
             Collections.sort(discounList);
             System.out.println(discounList);
  }
         log.info("perdayRent after discount");
         log.info("priceList and the discountlist were sorted and printed on console");
         logger.log(LogStatus.PASS, "got perday rent and discount of all the cars");
    
}
    
}

//I am using my project for interviewDemo purpose;


