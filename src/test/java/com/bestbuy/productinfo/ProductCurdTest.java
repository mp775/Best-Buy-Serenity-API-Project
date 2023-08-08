package com.bestbuy.productinfo;

import com.bestbuy.testbase.ProductTestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class ProductCurdTest extends ProductTestBase {
    static String  name= "Pen";

    static String  type= "Hard Good";

    static String  upc= "12345676";

    static  double price =  98.99;

    static String description = "This is a cucumber.api.java.lu.a request for creating a new product.";

    static String model = "MN908760";

    static int id = 9999682;

    @Steps

    ProductSteps productSteps;


    @Title("Create Product")

    @Test

    public void test002()
    {

        productSteps.createProduct( name, type, upc, price,description, model);
    }
    @Title("GetProductName")

    @Test

    public void test003()

    {
        productSteps.getProductName( name,description);

    }

    @Title("UpdateProduct")

    @Test

    public void test004()

    {
        productSteps. UpdateProduct( name, type, upc, price, description, model,id);

    }

    @Title("delete Product")

    @Test

    public void test005()

    {

        productSteps.deleteProduct(id);

    }
}
