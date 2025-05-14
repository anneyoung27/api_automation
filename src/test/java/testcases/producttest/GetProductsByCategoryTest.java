package testcases.producttest;

import base.TestBase;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static routes.Routes.GET_PRODUCTS_BY_CATEGORY;

public class GetProductsByCategoryTest extends TestBase {
    @Test
    public void getProductsByCategoryTest(){
        given()
                .pathParam("category", "electronics")
                .when()
                .get(GET_PRODUCTS_BY_CATEGORY)
                .then()
                .statusCode(200)
                .body("category", notNullValue())
                .body("size()", greaterThan(0))
                .log().all();
    }

    String getCategory(){
        Random rdm = new Random();

        List<String> product_categories = Arrays.asList("electronics", "jewelery", "men's clothing", "women's clothing");

        int index = rdm.nextInt(1, product_categories.size());
        return product_categories.get(index);
    }
}
