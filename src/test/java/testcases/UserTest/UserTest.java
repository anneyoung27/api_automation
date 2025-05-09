package testcases.UserTest;

import base.TestBase;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import payloads.Payload;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static routes.Routes.*;

public class UserTest extends TestBase {
    private static String token;

    @Test(priority = 1)
    public void loginCredentialTest() {
        token = given()
                .contentType(ContentType.JSON)
                .body(Payload.loginPayload(getProperty("userName"), getProperty("password")))
                .when()
                .post(LOGIN)
                .then()
                .statusCode(200)
                .log().all().extract().jsonPath().getString("token");
    }

    @Test(priority = 2, dependsOnMethods = {"loginCredentialTest"})
    public void getSingleUserTest() {
        given()
                .when()
                .pathParam("id", 1)
                .cookie("token", token)
                .get(GET_SINGLE_USER)
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("size()", greaterThan(1))
                .log().all();
    }

    @Test(priority = 3, dependsOnMethods = {"loginCredentialTest"})
    public void getAllUsers(){
        given()
                .cookie("token", token)
                .when()
                .get(GET_ALL_USER)
                .then()
                .statusCode(200);
    }
}
