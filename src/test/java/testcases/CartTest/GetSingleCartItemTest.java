package testcases.CartTest;

import base.TestBase;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static routes.Routes.GET_SINGLE_CART;

public class GetSingleCartItemTest extends TestBase {
    @Test
    public void getSingleCartItemTest(){
        given()
                .pathParam("id", getProperty("cartId"))
                .when()
                .get(GET_SINGLE_CART)
                .then()
                .statusCode(200)
                .body("id", equalTo(Integer.parseInt(getProperty("cartId"))))
                .log().all();
    }
}
