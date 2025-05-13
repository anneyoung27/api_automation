package testcases.CartTest;

import base.TestBase;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static routes.Routes.GET_ALL_CARTS;

public class GetAllCartItemsTest extends TestBase {
    @Test
    public void getAllCartItemTest(){
        given()
                .when()
                .get(GET_ALL_CARTS)
                .then()
                .statusCode(200)
                .log().all();
    }
}
