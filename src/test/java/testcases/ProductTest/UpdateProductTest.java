package testcases.ProductTest;

import base.TestBase;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import payloads.Payload;

import static io.restassured.RestAssured.given;
import static routes.Routes.UPDATE_PRODUCT;

public class UpdateProductTest extends TestBase {
    @Test
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
}
