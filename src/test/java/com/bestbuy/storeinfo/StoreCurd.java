package com.bestbuy.storeinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.StorePojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static net.serenitybdd.rest.SerenityRest.given;

@RunWith(SerenityRunner.class)

public class StoreCurd extends TestBase {
    static String name = "Sorath" + TestUtils.getRandomValue();
    static String type = "BigBox" + TestUtils.getRandomValue();
    static String address = "3 Plashet Road" + TestUtils.getRandomValue();
    static String city = "London" + TestUtils.getRandomValue();
    static String state = "England" + TestUtils.getRandomValue();
    static String zip = "e130qa" + TestUtils.getRandomValue();
    static double lat = 44.969658;
    static double lng = 93.449539;
    static String address2 = "East london" + TestUtils.getRandomValue();
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8" + TestUtils.getRandomValue();
    static int id = 8933;
    String storeName = "Apple Shop";


    @Title("This will create new store")
    @Test
    public void test01() {
        StorePojo storePojo = new StorePojo();
        storePojo.setAddress(address);
        storePojo.setCity(city);
        storePojo.setHours(hours);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setState(state);
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setZip(zip);
        storePojo.setAddress2(address2);
        given()
                .contentType(ContentType.JSON)
                .when()
                .body(storePojo)
                .post()
                .then().log().all().statusCode(201);
    }


    @Title("Find Store that sell Apple")
    @Test
    public void test002() {
        int sId = given()
                .queryParam("service.name", storeName)
                .when()
                .get()
                .then().statusCode(200)
                .extract()
                .path("total");
        System.out.println("value of ID" + sId);
        Assert.assertEquals(sId, 0);
    }

    @Title("Update the user Information and verify the updated information")

    @Test

    public void updateID() {

        StorePojo storePojo = new StorePojo();
        storePojo.setName("Prime");
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours("Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8");
        given()
                .header("Content-Type", "application/json")
                .pathParams("id", id)
                .body(storePojo)
                .when()
                .patch(EndPoints.UPDATE_STORE_BY_ID)
                .then().log().all().statusCode(200);
    }

    @Title("Delete the store")
    @Test
    public void deleteId() {
        int id = 8951;
        given()
                .pathParam("id", id)
                .when()
                .delete(EndPoints.DELETE_STORE_BY_ID)
                .then()
                .statusCode(200);
        given()
                .pathParam("id", id)
                .when()
                .get(EndPoints.DELETE_STORE_BY_ID)
                .then().statusCode(404);

    }
}