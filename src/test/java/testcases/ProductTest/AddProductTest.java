package testcases.ProductTest;

import base.TestBase;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import payloads.Payload;


import static io.restassured.RestAssured.given;
import static routes.Routes.CREATE_PRODUCT;

public class AddProductTest extends TestBase {
    @Test
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
}
