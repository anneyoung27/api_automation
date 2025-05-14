package testcases.producttest;

import base.TestBase;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static routes.Routes.GET_ALL_CATEGORIES;

public class GetAllCategoriesTest extends TestBase {
    @Test
    public void getAllCategoriesTest() {
        given()
                .when()
                .get(GET_ALL_CATEGORIES)
                .then()
                .statusCode(200)
                .body("size()", equalTo(4))
                .body(notNullValue())
                .log().all();
    }
}
