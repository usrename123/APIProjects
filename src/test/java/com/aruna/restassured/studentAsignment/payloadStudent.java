package com.aruna.restassured.studentAsignment;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.aruna.restassured.Restassured_M.Utilities.Utilities.TestBase;
import com.relevantcodes.extentreports.LogStatus;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import io.restassured.path.json.JsonPath;

public class payloadStudent extends TestBase {
    
    Logger log = Logger.getLogger(getClass().getSimpleName());
    @Test
    public void studentData_validation() throws FileNotFoundException, IOException, ParseException {
        System.out.println(StudentPayload());
        String S_payload_response = StudentPayload();
        JsonPath jp = new JsonPath(S_payload_response);
        // System.out.println(jp.get().toString());
        String username = jp.get("studentData.username");
        System.out.println(username);
        log.info("get the student username"+username);
        logger.log(LogStatus.PASS, "usrename was successfully extracted");
        List<Integer> session_id = jp.get("studentData.sessionid");
        System.out.println(session_id);
        log.info("get the student session-ID"+session_id);
        logger.log(LogStatus.PASS, "student sessions were successfully extracted and retrived in a list");
        int num = jp.get("studentData.sessionid[-1]");
        System.out.println(num);
        log.info("get the student last session-ID"+num);
        logger.log(LogStatus.PASS, "The last session  was successfully extracted");
        int count = jp.getInt("students.size()");
        System.out.println(count);
        log.info("number of students-ID"+count);
        List<String> student = jp.get("students.contact");
        System.out.println(student);
        log.info(" students contacts"+student);
        logger.log(LogStatus.PASS, "All contacts for all the students were successfully extracted");
        List<String> marks = jp.get("students[1].marks");
        System.out.println(marks);
        log.info(" Second students marks "+student);
        logger.log(LogStatus.PASS, "second student's marks were successfully extracted in a list");
        List<String> seconStuAdd = jp.get("students[1].adresss");
        System.out.println(seconStuAdd);
        log.info(" Second students address "+seconStuAdd);
        logger.log(LogStatus.PASS, "second student's adresss were successfully extracted in a list");
        List<String> FirstStu_addr = jp.get("students[0].adresss");
        System.out.println("First student address is :-" + FirstStu_addr);
        log.info("First student address is" + FirstStu_addr);
        logger.log(LogStatus.PASS, "First student's adresss were successfully extracted in a list");
        List<String> secon_stateValue = jp.get("students[1].adresss.state");
        log.info("second student's state" + secon_stateValue);
        System.out.println(secon_stateValue);
        logger.log(LogStatus.PASS, "states of second student were successfully extracted in a list");
        List<String> cities = jp.get("students[1].adresss.city");
        System.out.println(cities);
        log.info("get all the cities" + cities);
        
        logger.log(LogStatus.PASS, "Cities of second student were successfully extracted in a list");
        List<String> All_contacts = jp.get("students.contact");
        System.out.println(All_contacts);
        log.info("get all the contacts" + cities);
        logger.log(LogStatus.PASS, "All student contacts were successfully extracted in a list");

    }

        public static String StudentPayload() throws FileNotFoundException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(
                "C:\\restAssure\\src\\main\\java\\com\\aruna\\restassured\\Restassured_M\\Utilities\\Utilities\\Studentpayload.json"));
        JSONObject jsonObject = (JSONObject) obj;
        logger.log(LogStatus.INFO, "My path worked with double forward slashes");
        return jsonObject.toJSONString();

    }

}
