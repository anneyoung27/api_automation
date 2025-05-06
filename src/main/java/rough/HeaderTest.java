package rough;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class HeaderTest {
    @Test
    void testHeaders() {
        Response response =
                given()
                        .when()
                        .get("https://www.google.com/")
                        .then()
                        .statusCode(200)
                        .log().cookies()
                        .extract().response();

        Headers headers = response.getHeaders();
        for (Header header : headers){
            System.out.println(header.getName() + " : " + header.getValue());
        }
    }
}
