package com.bestbuy.info;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.StorePojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import java.util.HashMap;

public class StoreSteps {

    @Step("Creating store with name: {0}, type: {0}, address: {0}, address2: {0}, city: {0}, state: {0}, zip: {0}, lat: {0}, lng: {0}, hours: {0}")
    public ValidatableResponse createStore(String name, String type, String address, String address2, String city, String state,
                                           String zip, Double lat, Double lng, String hours) {
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .body(storePojo)
                .post(EndPoints.POST_A_STORE)
                .then();
    }

    @Step("Getting the store information with Name: {0}")
    public HashMap<String, Object> getStoreInfoByName(String name) {
        String p1 = "findAll{it.firstName == '";
        String p2 = "'}.get(0)";

        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_STORE)
                .then()
                .statusCode(200)
                .extract()
                .path(p1 + name + p2);
    }

    @Step("Updating store with name : {0}, type: {1}, address: {2}, address2: {3}, city: {4}, state: {5}, zip: {6}, lat: {7}, lng: {8}, hours: {9}")
    public ValidatableResponse updateStore(int storeID, String name, String type, String address, String address2, String city, String state,
                                           String zip, double lat, double lng, String hours) {
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("storeID", storeID)
                .when()
                .body(storePojo)
                .post(EndPoints.UPDATE_STORE_BY_ID)
                .then();

    }

    @Step("Deleting store information with storename: {0}")
    public ValidatableResponse deleteStore(int storeId) {
        return SerenityRest.given().log().all()
                .pathParam("storeId", storeId)
                .when()
                .delete(EndPoints.DELETE_STORE_BY_ID)
                .then()
                .statusCode(204);
    }

    @Step("Getting store information with studentId: {0}")
    public ValidatableResponse getStudentById(int storeId) {
        return SerenityRest.given().log().all()
                .pathParam("storeId", storeId)
                .when()
                .get(EndPoints.GET_SINGLE_STORE_BY_ID)
                .then();
    }
}
