package rough;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class HTTPMethodTest {

    String objectId;

    @BeforeMethod
    public void setUp(){
        RestAssured.baseURI = "https://api.restful-api.dev";
    }

    @Test(priority = 1)
    public void getListOfAllObjects(){
        given()
                .when()
                .get("/objects")
                .then()
                .statusCode(200)
                .header("Content-Type", equalTo("application/json"))
                .log().all();
    }

    @Test(priority = 2)
    public void getListOfObjectsByIds(){
        given()
                .queryParam("id", 3)
                .queryParam("id", 5)
                .queryParam("id", 10)
                .when()
                .get("/objects") // https://api.restful-api.dev/objects?id=3&id=5&id=10
                .then()
                .statusCode(200)
                .header("Content-Type", equalTo("application/json"))
                .log().all();
    }

    @Test(priority = 3)
    public void getSingleObject(){
        given()
                .pathParam("id", 7)
                .when()
                .get("/objects?{id}")
                .then()
                .statusCode(200)
                .header("Content-Type", equalTo("application/json"))
                .log().all();
    }

    @Test(priority = 4)
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
        String jsonBody = gson.toJson(dataTest);

        objectId = given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .post("/objects")
                .then()
                .statusCode(200)
                .header("Content-Type", equalTo("application/json"))
                .body("name", equalTo("Apple MacBook Pro 16"))
                .log().all()
                .extract().jsonPath().getString("id");
    }

    @Test(priority = 5, dependsOnMethods = {"addObject"})
    public void updateObject(){
        HashMap<String, Object> innerUpdateData= new HashMap<>();
        innerUpdateData.put("year", 2025);
        innerUpdateData.put("price", 1849.99);
        innerUpdateData.put("CPU model", "Intel Core i9");
        innerUpdateData.put("Hard disk size", "2 TB");

        HashMap<String, Object> dataTest = new HashMap<>();
        dataTest.put("name", "Apple MacBook Pro M4");
        dataTest.put("data", innerUpdateData);

        Gson gson = new Gson();
        String jsonBody = gson.toJson(dataTest);

        objectId = given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .put("/objects/"+objectId)
                .then()
                .statusCode(200)
                .header("Content-Type", equalTo("application/json"))
                .body("data.year", equalTo(2025))
                .body("data['Hard disk size']", equalTo("2 TB"))
                .body("name", equalTo("Apple MacBook Pro M4"))
                .log().all()
                .extract().jsonPath().getString("id");
    }

    @Test(priority = 6, dependsOnMethods = {"updateObject"})
    public void partiallyUpdateObject(){
        HashMap<String, Object> partialUpdateData = new HashMap<>();
        partialUpdateData.put("name", "Apple MacBook Pro M4 Max (Update Name)");

        given()
                .contentType(ContentType.JSON)
                .body(partialUpdateData)
                .when()
                .patch("/objects/"+objectId)
                .then()
                .statusCode(200)
                .header("Content-Type", equalTo("application/json"))
                .body("name", equalTo("Apple MacBook Pro M4 Max (Update Name)"))
                .log().all();
    }

    @Test(priority = 7, dependsOnMethods = {"partiallyUpdateObject"})
    public void deleteObject(){
        given()
                .when()
                .delete("/objects/"+objectId)
                .then()
                .statusCode(200)
                .log().all();
    }
}
