package com.aruna.restassured.Restassured_M.Utilities.Utilities;

import com.relevantcodes.extentreports.LogStatus;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportListeners extends TestBase implements ITestListener {
   
    @Override
	  public synchronized void onStart(ITestContext context) {
	    System.out.println("Test Suite started!");
	  }

	  @Override
	  public synchronized void onFinish(ITestContext context) {
	    System.out.println(("Test Suite is ending!"));
	    reports.flush();
	  }

	  @Override
	  public synchronized void onTestStart(ITestResult result) {
	    logger = reports.startTest(result.getMethod().getMethodName());
	    System.out.println("onTestStart called");
	    System.out.println(result.getMethod().getMethodName() + " started");

	    System.out.println("Logger initialized");
	  }

	  @Override
	  public synchronized void onTestSuccess(ITestResult result) {
	    System.out.println("onTestSuccess called");
	    logger.log(LogStatus.PASS, result.getMethod().getMethodName() + " Passes");
	    // Add screenshots
	    reports.endTest(logger);
	  }

	  @Override
	  public synchronized void onTestFailure(ITestResult result) {
	    System.out.println("onTestFailure called");
	    logger.log(LogStatus.FAIL, result.getMethod().getMethodName() + "Failed");
	    // Add screenshots
	    reports.endTest(logger);
	  }

	  @Override
	  public synchronized void onTestSkipped(ITestResult result) {
	    System.out.println("onTestSkipped called");
	    reports.endTest(logger);
	  }
    
}
