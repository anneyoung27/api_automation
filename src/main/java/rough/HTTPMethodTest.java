package rough;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class HTTPMethodTest {
    @Test(priority = 1)
    public void getListOfAllObjects(){
        given()
                .when()
                .get("https://api.restful-api.dev/objects")
                .then()
                .statusCode(200)
                .header("Content-Type", equalTo("application/json"))
                .log().all();
    }

    @Test(priority = 2)
    public void getListOfObjectsByIds(){
        given()
                .when()
                .get("https://api.restful-api.dev/objects?id=3&id=5&id=10")
                .then()
                .statusCode(200)
                .header("Content-Type", equalTo("application/json"))
                .log().all();
    }

    @Test(priority = 3)
    public void addObject(){
        HashMap<String, Object> innerDataTest = new HashMap<>();
        innerDataTest.put("year", 2019);
        innerDataTest.put("price", 1849.99);
        innerDataTest.put("CPU model", "Intel Core i9");
        innerDataTest.put("Hard disk size", "1 TB");

        HashMap<String, Object> dataTest = new HashMap<>();
        dataTest.put("name", "Apple MacBook Pro 16");
        dataTest.put("data", innerDataTest);

        Gson gson = new Gson();
        String jsonBody = gson.toJson(dataTest); // ✅ Ubah ke JSON String

        given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .post("https://api.restful-api.dev/objects")
                .then()
                .statusCode(200)
                .header("Content-Type", equalTo("application/json"))
                .body("name", equalTo("Apple MacBook Pro 16"))
                .log().all();
    }
}
