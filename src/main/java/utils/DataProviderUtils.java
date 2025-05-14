package utils;


import helper.CsvHelper;
import helper.JsonHelper;
import org.testng.annotations.DataProvider;

import java.util.List;
import java.util.Map;

public class DataProviderUtils {

    @DataProvider(name = "jsonDataProvider")
    public Object[][] jsonDataProvider() {
        JsonHelper jsonHelper = new JsonHelper();

        jsonHelper.setJsonFile("src\\main\\resources\\ProductSchema.json"); // set json file path here!

        List<Map<String, Object>> dataList = (List<Map<String, Object>>) jsonHelper.getData("$");
        Object[][] result = new Object[dataList.size()][1];

        for (int i = 0; i < dataList.size(); i++) {
            result[i][0] = dataList.get(i);
        }

        return result;
    }

    @DataProvider(name = "csvDataProvider")
    public Object[][] csvDataProvider() {
        CsvHelper csvHelper = new CsvHelper();

        csvHelper.setCsvFile("src\\main\\resources\\ProductData.csv"); // set csv file path here!

        List<Map<String, Object>> dataList = csvHelper.getData();
        Object[][] result = new Object[dataList.size()][1];

        for (int i = 0; i < dataList.size(); i++) {
            result[i][0] = dataList.get(i);
        }
        return result;
    }


}
