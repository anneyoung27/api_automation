package base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import pojo.Login;
import routes.Routes;

import java.util.List;
import java.util.Properties;

import static helper.PropertiesHelper.loadFile;

public class TestBase {

    Properties setUp;

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = Routes.BASE_URL;
        setUp = loadFile();
    }

    public String getProperty(String key){
        return setUp.getProperty(key);
    }

    public int getIntProperty(String key){
        return Integer.parseInt(setUp.getProperty(key));
    }

    public boolean isSortedDescending(List<Integer> list){
        for (int i = 0; i < list.size() - 1; i++){
            if (list.get(i) < list.get(i + 1)){
                return false;
            }
        }
        return true;
    }

    public boolean isSortedAscending(List<Integer> list){
        for (int i = 0; i < list.size() - 1; i++){
            if (list.get(i) > list.get(i + 1)){
                return false;
            }
        }
        return true;
    }

    public Login userLogin(String userName, String password){
        return new Login(userName, password);
    }
}
