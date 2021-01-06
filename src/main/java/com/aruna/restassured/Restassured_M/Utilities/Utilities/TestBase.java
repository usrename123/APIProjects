package com.aruna.restassured.Restassured_M.Utilities.Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;


@Listeners(com.aruna.restassured.Restassured_M.Utilities.Utilities.ExtentReportListeners.class)

public class TestBase {
    public static commonUtil ComUtil=new commonUtil();
    public static ExtentReports reports;
    public static ExtentTest logger;

    public  void InitializeReport() {
        
        reports = new ExtentReports(System.getProperty("user.dir") + "/report/"
            + new SimpleDateFormat("'Test_reports'YYYYMMddHHmmss'.html'").format(new Date()));
      }
      @BeforeSuite
      
        
        public void InitializeDependencies() throws Exception {
        
        
            ComUtil.loadLog4jProperty(
              System.getProperty("user.dir") + "\\src\\main\\java\\com\\aruna\\restassured\\Restassured_M\\Utilities\\config.properties\\lo4j.properties");
        InitializeReport();

      }
      @AfterSuite
    public void ended(){
        reports.flush();
    }
}
