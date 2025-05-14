package ddt;

import base.TestBase;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import pojo.Product;
import utils.DataProviderUtils;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static routes.Routes.CREATE_PRODUCT;
import static routes.Routes.DELETE_PRODUCT;

public class ProductDataDrivenWithJSONTest extends TestBase {

    @Test(dataProvider = "jsonDataProvider", dataProviderClass = DataProviderUtils.class)
    public void testAddNewProductThenDeleteIt(Map<String, Object> data) {

        String title = data.get("title").toString();
        double price = ((Number) data.get("price")).doubleValue();
        String category = data.get("category").toString();
        String description = data.get("description").toString();
        String image = data.get("image").toString();

        Product newProduct = new Product(title, price, description, image, category);

        int productId = given()
                .contentType(ContentType.JSON)
                .body(newProduct)
                .when()
                .post(CREATE_PRODUCT)
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("title", equalTo(title))
                .extract().jsonPath().getInt("id");

        given()
                .pathParam("id", productId)
                .when()
                .delete(DELETE_PRODUCT)
                .then()
                .statusCode(200)
                .log().all();
    }
}
