package testcases.ProductTest;

import base.TestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static routes.Routes.GET_PRODUCTS_LIMIT_AND_SORTED;

public class GetProductWithLimitAndSortTest extends TestBase {
    @Test
    public void getProductWithLimitAndSortTest() {
        Response response = given()
                .pathParam("limit", 5)
                .pathParam("order", "desc")
                .when()
                .get(GET_PRODUCTS_LIMIT_AND_SORTED)
                .then()
                .statusCode(200)
                .extract()
                .response();

        List<Integer> productIds = response.jsonPath().getList("id", Integer.class);

        Assert.assertNotNull(productIds, "Product ID list should not be null");
        Assert.assertEquals(productIds.size(), 5, "Product ID list should contain 5 elements");
        Assert.assertTrue(isSortedDescending(productIds), "Product IDs are not in descending order");
    }
}