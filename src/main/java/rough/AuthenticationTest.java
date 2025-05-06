package rough;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AuthenticationTest {

    // 1. Basic authentication
    @Test
    void verifyBasicAuth(){
        given()
                .auth().basic("postman", "password")
        .when()
                .get("https://postman-echo.com/basic-auth")
        .then()
                .statusCode(200)
                .body("authenticated", equalTo(true));
    }

    // 2. Basic preemptive
    @Test
    void verifyPreemptiveAuth(){
        given()
                .auth().preemptive().basic("postman","password")
        .when()
                .get("https://postman-echo.com/basic-auth")
        .then()
                .statusCode(200)
                .body("authenticated", equalTo(true));
    }

    // 3. Digest authentication
    @Test
    void verifyDigestAuth(){
        given()
                .auth().digest("postman", "password")
        .when()
                .get("https://postman-echo.com/basic-auth")
        .then()
                .statusCode(200)
                .body("authenticated", equalTo(true));
    }

    // 4. Bearer token authentication
    @Test
    void verifyBearerAuth(){
        String bearerToken = "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";

        given()
                .header("Authorization", "Bearer " + bearerToken)
        .when()
                .get("https://api.github.com/user/repos")
        .then()
                .statusCode(200)
                .log().body();
    }
}
