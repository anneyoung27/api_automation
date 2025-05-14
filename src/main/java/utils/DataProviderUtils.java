package utils;


import helper.JsonHelper;
import org.testng.annotations.DataProvider;

import java.util.List;
import java.util.Map;

public class DataProviderUtils {

    @DataProvider(name = "jsonDataProvider")
    public Object[][] jsonDataProvider() {
        JsonHelper jsonHelper = new JsonHelper();
        jsonHelper.setJsonFile("src\\main\\resources\\ProductSchema.json"); // set json file here!

        List<Map<String, Object>> dataList = (List<Map<String, Object>>) jsonHelper.getData("$");
        Object[][] result = new Object[dataList.size()][1];

        for (int i = 0; i < dataList.size(); i++){
            result[i][0] = dataList.get(i);
        }

        return result;

    }
}
