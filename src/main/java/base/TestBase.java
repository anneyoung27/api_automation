package base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import pojo.Login;
import routes.Routes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public static boolean validateCartDatesWithinRange(List<String> cartDates, String startDate, String endDate) {
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate start_date = LocalDate.parse(startDate, FORMATTER);
        LocalDate end_date = LocalDate.parse(endDate, FORMATTER);

        for (String dateTime : cartDates) {
            LocalDate cartDate = LocalDate.parse(dateTime.substring(0, 10), FORMATTER);
            if (cartDate.isBefore(start_date) || cartDate.isAfter(end_date)) {
                return false;
            }
        }
        return true;
    }
}
