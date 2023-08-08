package com.bestbuy.productinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.constants.ProductEndPoint;
import com.bestbuy.model.ProductPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.rest.RestRequests.given;

public class ProductSteps {


    @Step("Creating Product :name:{0},type :{1},upc :{2},price :{3},description :{4},model :{5}")

    public void createProduct(String name, String type, String upc, double price, String description, String model) {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setUpc(upc);
        productPojo.setPrice(price);
        productPojo.setDescription(description);
        productPojo.setModel(model);
        SerenityRest.given()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .when()
                .body(productPojo)
                .post()
                .then().log().body().statusCode(201);
    }
    @Step("GetProductName")
    public void getProductName(String name, String description) {
        Response response = given().queryParam("$select[]", "name").queryParam("$select[]", "description")
                .basePath("/products")
                .header("Content-Type", "application/json")
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Step("UpdateProduct")
    public void UpdateProduct(String name, String type, String upc, double price, String description, String model, int id) {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setUpc(upc);
        productPojo.setPrice(price);
        productPojo.setDescription(description);
        productPojo.setModel(model);
        SerenityRest.given()

                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .pathParams("id", id)
                .when()
                .body(productPojo)
                .patch(ProductEndPoint.UPDATE_PRODUCT_BY_ID)
                .then().log().body().statusCode(200);

    }

    @Step("DeleteProduct")

    public void deleteProduct(int id) {
        given()
                .pathParam("id", id)
                .when()
                .delete(EndPoints.DELETE_STORE_BY_ID)
                .then()
                .statusCode(200);
        given()
                .pathParam("id", id)
                .when()
                .get(ProductEndPoint.DELETE_PRODUCT_BY_ID)
                .then().statusCode(404);

    }
}