package com.aruna.restassured.Restassured_M.Utilities.Utilities;

import java.util.Properties;
import java.io.FileInputStream;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class commonUtil {

    Logger log = Logger.getLogger(getClass().getSimpleName());
 
   static Properties props = new Properties();
   static FileInputStream fileIn = null;
   //----------------------------------------------------
 
   public void loadPropertyFiles(String propPath) throws Exception {
     fileIn = new FileInputStream(propPath);
     System.out.println(System.getProperty(propPath));
 
     props.load(fileIn);
     System.getProperties().putAll(props);
   }
  
 //--------------------------------------------------------
 
   public  void loadLog4jProperty(String PropertiesFilePath) throws Exception {
     log.info("Log4j Property file Path :" + PropertiesFilePath);
     fileIn = new FileInputStream(PropertiesFilePath);
     props.load(fileIn);
     PropertyConfigurator.configure(props);
   }
  
     
 }
