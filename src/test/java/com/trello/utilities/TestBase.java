package com.trello.utilities;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.*;

public class TestBase {
    @BeforeClass
    public static void setup(){
        baseURI = ConfigurationReader.get("baseURI");
       basePath = ConfigurationReader.get("basePath");
    }

    @AfterClass
    public static void tearDown(){
      reset();
    }
}
