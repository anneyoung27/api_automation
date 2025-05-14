package helper;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class JsonHelper {

    private DocumentContext jsonContext;

    public void setJsonFile(String jsonPath) {
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(SystemHelper.getCurrentDir() + jsonPath), StandardCharsets.UTF_8))
        ) {
            StringBuilder stringBuffer = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            jsonContext = JsonPath.parse(stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object getData(String key) {
        //JsonPath.read(getJsonDataSourceString(), key);
        return jsonContext.read(key);
    }

    public static String readFileContent(File file) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))
        ) {
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            return json.toString();
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + file.getName(), e);
        }
    }
}
