package com.employeeapi.testCases;

import com.employeeapi.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC002_Get_Single_Employee_Record extends TestBase {

    @BeforeClass
    void getEmployeeData() throws InterruptedException {
        logger.info("*********Started TC002_Get_Single_Employee_Record **********");

        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET, "/employee/"+empID);
        Thread.sleep(5);
    }

    @Test
    void checkResponseBody()
    {
        logger.info("*********Checking ResponseBody **********");
        String responseBody = response.getBody().asString();
        Assert.assertEquals(responseBody.contains(empID), true);
    }

    @Test
    void checkStatusCode()
    {
        logger.info("*********Checking StatusCode **********");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,200);
    }
    @Test
    void responseTime()
    {
        logger.info("*********Checking ResponseTime **********");
        long time = response.getTime();
        Assert.assertTrue(time<6000);
    }
    @Test
    void checkstatusLine()
    {
        String statusLine = response.getStatusLine(); // Gettng status Line
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

    }

    @Test
    void checkContentType()
    {
        String contentType = response.header("Content-Type");
        Assert.assertEquals(contentType,"application/json;charset=utf-8");
    }

    @Test
    void checkserverType()
    {
        String serverType = response.header("Server");
        Assert.assertEquals(serverType, "nginx/1.16.0");
    }

    @Test
    void checkContentLenght()
    {
        String contentLength = response.header("Content-Length");
        Assert.assertTrue(Integer.parseInt(contentLength)<1500);
    }

    @AfterClass
    void tearDown()
    {
        logger.info("*********  Finished TC001_Get_All_Employees **********");
    }

}
