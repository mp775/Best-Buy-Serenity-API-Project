package com.bestbuy.testbase;

import com.bestbuy.constants.ProductPath;
import com.bestbuy.constants.StorePath;
import com.bestbuy.utils.PropertyReader;
import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TestBase {
   public static PropertyReader propertyReader;
    @BeforeClass
    public static void init() {
        propertyReader = PropertyReader.getInstance();
        RestAssured.baseURI = propertyReader.getProperty("baseUrl");
        RestAssured.port = Integer.parseInt(propertyReader.getProperty("port"));
        RestAssured.basePath = StorePath.STORE;
        RestAssured.basePath = ProductPath.PRODUCT;
    }

}
