package testcases.ProductTest;

import base.TestBase;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static routes.Routes.DELETE_PRODUCT;

public class DeleteProductTest extends TestBase {
    @Test
    public void deleteProductTest(){
        given()
                .pathParam("id", getProperty("productId"))
                .when()
                .delete(DELETE_PRODUCT)
                .then()
                .statusCode(200);
    }
}
