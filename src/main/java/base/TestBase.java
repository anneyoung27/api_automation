package base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import routes.Routes;

public class TestBase {
    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = Routes.BASE_URL;

    }
}
