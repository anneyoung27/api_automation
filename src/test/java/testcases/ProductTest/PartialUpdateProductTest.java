package testcases.ProductTest;

import base.TestBase;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static routes.Routes.UPDATE_PRODUCT;

public class PartialUpdateProductTest extends TestBase {
    @Test
    public void partialUpdateProductTest(){
        HashMap<String, Object> partialDataUpdate = new HashMap<>();
        partialDataUpdate.put("title", "Shirt in matte and shiny cassandre striped silk");
        partialDataUpdate.put("description", "Short-sleeve shirt made with certified silk, featuring a pointed collar and side slits.");
        partialDataUpdate.put("image", "https://saint-laurent.dam.kering.com/m/4ab364b6e788adfc/Medium2-601070Y6H391000_A.jpg?v=3");
        partialDataUpdate.put("category", "men's clothing");

        given()
                .contentType(ContentType.JSON)
                .body(partialDataUpdate)
                .pathParam("id", getProperty("productId"))
                .when()
                .patch(UPDATE_PRODUCT)
                .then()
                .statusCode(200)
                .log().all();
    }
}
