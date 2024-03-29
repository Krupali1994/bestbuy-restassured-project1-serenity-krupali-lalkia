package com.bestbuy.info;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import java.util.HashMap;

public class ProductSteps {

    @Step("Creating product with name: {0}, type: {1}, price: {2}, upc: {3}, shipping: {4}, description: {5}, manufacturer: {6}, model: {7}, url: {8}, image: {9}")
    public ValidatableResponse createProduct(String name, String type, Integer price, Integer upc, String shipping, String description, String manufacturer,
                                             String model, String url, String image) {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setUpc(upc);
        productPojo.setShipping(shipping);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .body(productPojo)
                .post(EndPoints.POST_ALL_PRODUCT)
                .then();
    }

    @Step("Getting the product information with firstName: {0}")
    public HashMap<String, Object> getProductInfoByName(String name) {
        String p1 = "findAll{it.firstName == '";
        String p2 = "'}.get(0)";

        return SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_PRODUCT)
                .then()
                .statusCode(200)
                .extract()
                .path(p1 + name + p2);
    }

    @Step("Updating product information with name: {0}, type: {1}, price: {2}, upc: {3}, shipping: {4}, description: {5}, manufacturer: {6}, model: {7}, url: {8}, image: {9}")
    public ValidatableResponse updateProduct(String name, String type, Integer price, Integer upc, String shipping, String description, String manufacturer,
                                             String model, String url, String image) {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setUpc(upc);
        productPojo.setShipping(shipping);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("productID", name)
                .when()
                .body(productPojo)
                .post(EndPoints.UPDATE_PRODUCT_BY_ID)
                .then();

    }

    @Step("Deleting product information with productId: {0}")
    public ValidatableResponse deleteProduct(int productId) {
        return SerenityRest.given().log().all()
                .pathParam("productId", productId)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then()
                .statusCode(204);
    }

    @Step("Getting product information with productId: {0}")
    public ValidatableResponse getProductById(int studentId) {
        return SerenityRest.given().log().all()
                .pathParam("studentID", studentId)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then();
    }

}
