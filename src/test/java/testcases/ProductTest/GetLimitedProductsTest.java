package testcases.ProductTest;

import base.TestBase;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static routes.Routes.GET_PRODUCT_WITH_LIMIT;

public class GetLimitedProductsTest extends TestBase {
    @Test
    public void getLimitedProductTest(){
        given()
                .when()
                .pathParam("limit", 5)
                .get(GET_PRODUCT_WITH_LIMIT)
                .then()
                .statusCode(200)
                .body("size()", equalTo(3))
                .log().all();
    }
}