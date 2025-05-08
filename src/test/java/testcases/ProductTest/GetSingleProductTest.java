package testcases.ProductTest;

import base.TestBase;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import static routes.Routes.GET_PRODUCT_BY_ID;

public class GetSingleProductTest extends TestBase {
    @Test
    public void getSingleProductTest(){
        given()
                .when()
                .pathParam("id", getProperty("productId"))
                .get(GET_PRODUCT_BY_ID)
                .then()
                .statusCode(200)
                .log().all();
    }
}
