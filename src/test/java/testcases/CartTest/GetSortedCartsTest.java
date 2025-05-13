package testcases.CartTest;

import base.TestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static routes.Routes.GET_CART_SORTED;

public class GetSortedCartsTest extends TestBase {
    @Test
    public void getSortedCartTest(){
        Response response = given()
                .pathParam("order", "desc" )
                .when()
                .get(GET_CART_SORTED)
                .then()
                .statusCode(200)
                .extract().response();

        List<Integer> productId = response.jsonPath().getList("id", Integer.class);
        Assert.assertTrue(isSortedDescending(productId), "In descending order");
    }
}
