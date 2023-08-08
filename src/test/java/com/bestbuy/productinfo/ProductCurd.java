package com.bestbuy.productinfo;

import com.bestbuy.constants.ProductEndPoint;
import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.ProductTestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import static net.serenitybdd.rest.RestRequests.given;

@RunWith(SerenityRunner.class)
public class ProductCurd extends ProductTestBase {
    static String name = "lipstic" + TestUtils.getRandomValue();
    static String type = "cosmetic" + TestUtils.getRandomValue();
    static String upc = "1233456" + TestUtils.getRandomValue();
    static String price = "99.99";
    static String description = "message" + TestUtils.getRandomValue();
    static String model = "lc34";
    static int id = 9999688;


    @Title("this will create new product")
    @Test
    public void test001() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setModel(model);
        productPojo.setPrice(Double.valueOf(price));
        productPojo.setDescription(description);
        productPojo.setUpc(upc);

        SerenityRest.given()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .when()
                .body(productPojo)
                .post()
                .then().log().all().statusCode(201);
    }
    @Title("Get the specific store")
    @Test
    public void getProductName(String name, String description) {
        Response response = given().queryParam("$select[]", "name").queryParam("$select[]", "description")
                .basePath("/products")
                .header("Content-Type", "application/json")
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();

    }
    @Title("Update the user Information and verify the updated information")

    @Test

    public void updateID(){

        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setModel(model);
        productPojo.setPrice(Double.valueOf(price));
        productPojo.setDescription(description);
        productPojo.setUpc(upc);

        SerenityRest.given()
                .header("Content-Type", "application/json")
                .pathParams("id", id)
                .body(productPojo)
                .when()
                .patch(ProductEndPoint.UPDATE_PRODUCT_BY_ID)
                .then().log().all().statusCode(200);
    }
    @Title("Delete the product")
    @Test
    public void deleteId(){
        int id = 9999685;
        given()
                .pathParam("id", id)
                .when()
                .delete()
                .then()
                .statusCode(200);
        given()
                .pathParam("id", id)
                .when()
                .get(ProductEndPoint.DELETE_PRODUCT_BY_ID)
                .then().statusCode(404);

    }

}

