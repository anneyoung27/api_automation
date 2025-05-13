package testcases.CartTest;

import base.TestBase;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static routes.Routes.GET_CART_WITH_LIMIT;

public class GetLimitedCartItemTest extends TestBase {
    @Test
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
}
