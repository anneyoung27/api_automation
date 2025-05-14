package testcases.producttest;

import base.TestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static routes.Routes.GET_PRODUCTS_SORTED;

public class GetSortedProductsTest extends TestBase {
    @Test
    public void getSortedProductsTest(){
        Response response = given()
                .pathParam("order", "desc" )
                .when()
                .get(GET_PRODUCTS_SORTED)
                .then()
                .statusCode(200)
                .extract().response();

        List<Integer> productId = response.jsonPath().getList("id", Integer.class);
        Assert.assertTrue(isSortedDescending(productId), "In descending order");
    }
}
