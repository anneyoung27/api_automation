package rough;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/* API Chaining: Proses memanggil beberapa API secara berurutan, di mana hasil dari satu API digunakan sebagai
input untuk API berikutnya.
cth) hasil nya berupa ID, dan ID tersebut akan digunakan di API request lainnya.
*/
public class APIChainingTest {
    static final String BASE_URL = "https://gorest.co.in/public/v2/users";
    static final String BEARER_TOKEN = "d9e474f15fc96b1913590564044750ea567c5b06d9ea5a68021076d954a208f1";
    int userId;

    Faker faker = new Faker();

    @Test
    void createUser() {
        JSONObject requestData = new JSONObject();
        requestData.put("name", faker.name().fullName());
        requestData.put("gender", "Male");
        requestData.put("email", faker.internet().emailAddress());
        requestData.put("status", "Inactive");

        userId = given()
                .headers("Authorization", "Bearer " + BEARER_TOKEN)
                .contentType(ContentType.JSON)
                .body(requestData.toString())
                .when()
                .post(BASE_URL)
                .then()
                .statusCode(201)
                .log().body()
                .extract().response().jsonPath().getInt("id");
    }

    @Test(dependsOnMethods = {"createUser"})
    void getUser() {
        given()
                .header("Authorization", "Bearer " + BEARER_TOKEN)
                .pathParam("id", userId)
                .when()
                .get(BASE_URL + "/{id}")
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test(dependsOnMethods = {"getUser"})
    void updateUser() {
        JSONObject updateData = new JSONObject();
        updateData.put("name", faker.name().fullName());
        updateData.put("gender", "Male");
        updateData.put("email", faker.internet().emailAddress());
        updateData.put("status", "Active");

        given()
                .headers("Authorization", "Bearer " + BEARER_TOKEN)
                .contentType(ContentType.JSON)
                .body(updateData.toString())
                .pathParam("id", userId)
                .when()
                .put(BASE_URL + "/{id}")
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test(dependsOnMethods = {"updateUser"})
    void deleteUser() {
        given()
                .header("Authorization", "Bearer " + BEARER_TOKEN)
                .pathParam("id", userId)
                .when()
                .delete(BASE_URL + "/{id}")
                .then()
                .statusCode(204);
    }
}
