package com.bestbuy.crudtest;

import com.bestbuy.info.ProductSteps;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class ProductCRUDTestWithSteps extends TestBase {

    static String name = "Duracell - AAA Batteries (4-pack)" + TestUtils.getRandomValue();
    static String type = "HardGood" + TestUtils.getRandomValue();
    static Integer price = 5;
    static String upc = "041333424019";
    static Integer shipping = 10;
    static String description = "Compatible with select electronic devices; AAA size; DURALOCK Power Preserve technology; 4-pack";
    static String manufacturer = "Duracell";
    static String model = TestUtils.getRandomValue();
    static String url = "http://www.bestbuy.com/site/duracell-aaa-batteries-4-pack/43900.p?id=1051384074145&skuId=43900&cmp=RMXCC";
    static String image = "http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg";
    static int productId;

    @Steps
    ProductSteps productSteps;

    @Title("This will create a new product")
    @Test
    public void test001() {

        ValidatableResponse response = productSteps.createProduct(name, type, price, shipping, upc, url, description, manufacturer, model, image);
        response.statusCode(201);

    }


    @Title("Verify if the product was added")
    @Test
    public void test002() {

        HashMap<String, Object> studentMap = productSteps.getProductInfoByName(name);
        Assert.assertThat(studentMap, hasValue(name));
        productId = (int) studentMap.get("id");

    }


    @Title("Update the product information and verify the updated information")
    @Test
    public void test003() {
        name = name + "_updated";
        productSteps.updateProduct(name, type, price, shipping, upc, url, description, manufacturer, model, image).log().all().statusCode(200);
        HashMap<String, Object> studentMap = productSteps.getProductInfoByName(name);
        Assert.assertThat(studentMap, hasValue(name));

    }


    @Title("Delete the student and verify if the student is deleted")
    @Test
    public void test004() {
        productSteps.deleteProduct(productId).statusCode(204);
        productSteps.getProductById(productId).statusCode(404);
    }

}
