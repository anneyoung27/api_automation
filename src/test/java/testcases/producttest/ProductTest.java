package testcases.producttest;

import base.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import payloads.Payload;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static routes.Routes.*;

public class ProductTest extends TestBase {

    @Test(priority = 1)
    public void getSingleProductTest(){
        given()
                .when()
                .pathParam("id", getProperty("productId"))
                .get(GET_PRODUCT_BY_ID)
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 2)
    public void getAllProductTest(){
        given()
                .when()
                .get(GET_ALL_PRODUCTS)
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 3)
    public void getLimitedProductTest(){
        given()
                .when()
                .pathParam("limit", 5)
                .get(GET_PRODUCT_WITH_LIMIT)
                .then()
                .statusCode(200)
                .body("size()", equalTo(5))
                .log().all();
    }

    @Test(priority = 4)
    public void getSortedProductsTest(){
        Response response = given()
                .pathParam("order", "desc" )
                .when()
                .get(GET_PRODUCTS_SORTED)
                .then()
                .statusCode(200)
                .extract().response();

        List<Integer> productId = response.jsonPath().getList("id", Integer.class);
        Assert.assertTrue(isSortedDescending(productId), "In descending order");
    }

    @Test(priority = 5)
    public void getProductWithLimitAndSortTest() {
        Response response = given()
                .pathParam("limit", 5)
                .pathParam("order", "desc")
                .when()
                .get(GET_PRODUCTS_LIMIT_AND_SORTED)
                .then()
                .statusCode(200)
                .extract()
                .response();

        List<Integer> productIds = response.jsonPath().getList("id", Integer.class);

        Assert.assertNotNull(productIds, "Product ID list should not be null");
        Assert.assertEquals(productIds.size(), 5, "Product ID list should contain 5 elements");
        Assert.assertTrue(isSortedDescending(productIds), "Product IDs are not in descending order");
    }

    @Test(priority = 6)
    public void getAllCategoriesTest() {
        given()
                .when()
                .get(GET_ALL_CATEGORIES)
                .then()
                .statusCode(200)
                .body("size()", equalTo(4))
                .body(notNullValue())
                .log().all();
    }

    @Test(priority = 7)
    public void getProductsByCategoryTest(){
        given()
                .pathParam("category", "electronics")
                .when()
                .get(GET_PRODUCTS_BY_CATEGORY)
                .then()
                .statusCode(200)
                .body("category", notNullValue())
                .body("size()", greaterThan(0))
                .log().all();
    }

    @Test(priority = 8)
    public void addProductTest() {
        given()
                .contentType(ContentType.JSON)
                .body(Payload.productPayload())
                .when()
                .post(CREATE_PRODUCT)
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 9)
    public void updateProductTest(){
        given()
                .contentType(ContentType.JSON)
                .body(Payload.productPayload())
                .pathParam("id", getProperty("productId"))
                .when()
                .put(UPDATE_PRODUCT)
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 10)
    public void partialUpdateProductTest(){
        HashMap<String, Object> partialDataUpdate = new HashMap<>();
        partialDataUpdate.put("title", "Shirt in matte and shiny cassandre striped silk");
        partialDataUpdate.put("description", "Short-sleeve shirt made with certified silk, featuring a pointed collar and side slits.");
        partialDataUpdate.put("image", "https://saint-laurent.dam.kering.com/m/4ab364b6e788adfc/Medium2-601070Y6H391000_A.jpg?v=3");
        partialDataUpdate.put("category", "men's clothing");

        given()
                .contentType(ContentType.JSON)
                .body(partialDataUpdate)
                .pathParam("id", getProperty("productId"))
                .when()
                .patch(UPDATE_PRODUCT)
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 11)
    public void deleteProductTest(){
        given()
                .pathParam("id", getProperty("productId"))
                .when()
                .delete(DELETE_PRODUCT)
                .then()
                .statusCode(200);
    }

}
