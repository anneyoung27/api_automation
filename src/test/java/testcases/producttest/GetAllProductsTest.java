package testcases.producttest;

import base.TestBase;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static routes.Routes.GET_ALL_PRODUCTS;

public class GetAllProductsTest extends TestBase {
    @Test
    public void getAllProductTest(){
        given()
                .when()
                .get(GET_ALL_PRODUCTS)
                .then()
                .statusCode(200)
                .log().all();
    }
}
