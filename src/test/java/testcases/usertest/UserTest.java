package testcases.usertest;

import base.TestBase;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import payloads.Payload;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static routes.Routes.*;

public class UserTest extends TestBase {
    private static String token;
    private static int userId;

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
                .pathParam("id", getProperty("userId"))
                .cookie("token", token)
                .get(GET_SINGLE_USER)
                .then()
                .statusCode(200)
                .body("id", equalTo(Integer.parseInt(getProperty("userId"))))
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

    @Test(priority = 4, dependsOnMethods = {"loginCredentialTest"})
    public void createUserTest(){
        userId = given()
                .contentType(ContentType.JSON)
                .body(Payload.userPayload())
                .cookie("token", token)
                .when()
                .post(CREATE_USER)
                .then()
                .statusCode(200)
                .log().all()
                .extract().jsonPath().getInt("id");
    }

    @Test(priority = 5, dependsOnMethods = {"loginCredentialTest", "createUserTest"})
    public void getCreatedUserTest() {
        given()
                .when()
                .pathParam("id", userId)
                .cookie("token", token)
                .get(GET_SINGLE_USER)
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 6, dependsOnMethods = {"loginCredentialTest", "createUserTest"})
    public void updateUserTest(){
        given()
                .contentType(ContentType.JSON)
                .body(Payload.userPayload())
                .pathParam("id", userId)
                .cookie("cookie", token)
                .when()
                .put(UPDATE_USER)
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 7, dependsOnMethods = {"loginCredentialTest", "createUserTest"})
    public void updatePartialTest(){
        HashMap<String, Object> partialUpdate = new HashMap<>();
        partialUpdate.put("username", "icarthy0");
        partialUpdate.put("email", "icarthy0@nationalgeographic.com");
        partialUpdate.put("password", "tH5{Zn(,El'");
        partialUpdate.put("firstname", "Isaiah");
        partialUpdate.put("lastname", "Carthy");

        given()
                .contentType(ContentType.JSON)
                .body(partialUpdate)
                .pathParam("id", userId)
                .cookie("cookie", token)
                .when()
                .patch(UPDATE_USER)
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 8, dependsOnMethods = {"loginCredentialTest", "createUserTest"})
    public void deleteUserTest(){
        given()
                .pathParam("id", userId)
                .when()
                .delete(DELETE_USER)
                .then()
                .statusCode(200)
                .log().all();
    }
}
