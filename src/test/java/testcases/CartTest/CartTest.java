package testcases.CartTest;

import base.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import payloads.Payload;

import java.text.ParseException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static routes.Routes.*;

public class CartTest extends TestBase {
    private String cartId;

    @Test(priority = 1)
    public void getAllCartItemTest() {
        given()
                .when()
                .get(GET_ALL_CARTS)
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 2)
    public void getSingleCartItemTest() {
        given()
                .pathParam("id", getProperty("cartId"))
                .when()
                .get(GET_SINGLE_CART)
                .then()
                .statusCode(200)
                .body("id", equalTo(Integer.parseInt(getProperty("cartId"))))
                .log().all();
    }

    @Test(priority = 3)
    public void getLimitedCartItemTest(){
        given()
                .when()
                .pathParam("limit", 5)
                .get(GET_CART_WITH_LIMIT)
                .then()
                .statusCode(200)
                .body("size()", equalTo(5))
                .log().all();
    }

    @Test(priority = 4)
    public void getSortedCartTest(){
        Response response = given()
                .pathParam("order", "desc" )
                .when()
                .get(GET_CART_SORTED)
                .then()
                .statusCode(200)
                .extract().response();

        List<Integer> productId = response.jsonPath().getList("id", Integer.class);
        Assert.assertTrue(isSortedDescending(productId), "In descending order");
    }

    @Test(priority = 5)
    public void getCartItemsInDateRangeTest() {
        String startDate = getProperty("startDate");
        String endDate = getProperty("endDate");

        given()
                .pathParam("start_date", startDate)
                .pathParam("end_date", endDate)
                .when()
                .get(GET_CART_ITEM_IN_DATA_RANGE)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(1))
                .body("", is(not(empty())))
                .log().body();
    }

    @Test(priority = 6)
    public void getUserSpecificCartItemsTest(){
        given()
                .pathParam("user_id", getProperty("userId"))
                .when()
                .get(GET_USER_SPECIFIC_CART_ITEM)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(1))
                .log().all();
    }

    @Test(priority = 7)
    public void addToCartTest() throws ParseException {
        cartId = given()
                .contentType(ContentType.JSON)
                .body(Payload.cartPayload())
                .when()
                .post(CREATE_CART)
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("id");
    }

    @Test(priority = 8, dependsOnMethods = {"addToCartTest"})
    public void updateCartTest() throws ParseException {
        given()
                .pathParam("id", cartId)
                .contentType(ContentType.JSON)
                .body(Payload.cartPayload())
                .when()
                .put(UPDATE_CART)
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test(priority = 9, dependsOnMethods = {"addToCartTest"})
    public void partialUpdateCartTest(){

    }

    @Test(priority = 10, dependsOnMethods = {"addToCartTest", "updateCartTest"})
    public void deleteCartTest(){
        given()
                .pathParam("id", cartId)
                .when()
                .delete(DELETE_CART)
                .then()
                .statusCode(200)
                .log().all();
    }
}