package rough;

import io.restassured.http.Cookie;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class CookieTest {
    @Test
    void testCookiesInResponse() {
        Response response =
                given()
                        .when()
                        .get("https://www.google.com/")
                        .then()
                        .statusCode(200)
                        .log().cookies()
                        .cookie("AEC", notNullValue())
                        .extract().response();

        // Extract specific cookies
        String cookieValue = response.getCookie("AEC");
        System.out.println("Value of 'AEC' Cookie: " + cookieValue);

        // Extract all cookies
        Map<String, String> allCookies =  response.getCookies();
        System.out.println("All the cookies: " + allCookies);

        // Extract all cookies with their values
        for (String key : allCookies.keySet()){
            System.out.println("Key (" + key + "): " + allCookies.get(key));
        }

        // Get detailed information about cookie
        Cookie cookie_info = response.getDetailedCookie("AEC");
        System.out.println("Expiry date: " + cookie_info.getExpiryDate());
        System.out.println("Expiry date exists: " + cookie_info.hasExpiryDate());
        System.out.println("Value: " + cookie_info.getValue());
        System.out.println("Value exists: " + cookie_info.hasValue());
        System.out.println("Is secured: " + cookie_info.isSecured());
    }
}
