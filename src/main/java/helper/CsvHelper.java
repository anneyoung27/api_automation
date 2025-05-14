package helper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvHelper {
    private List<Map<String, Object>> csvData = new ArrayList<>();

    public void setCsvFile(String csvPath) {
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(SystemHelper.getCurrentDir() + csvPath), StandardCharsets.UTF_8))
        ) {
            String line;
            String[] headers = null;

            int lineNumber = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",", -1); // handle empty values

                if (lineNumber == 0) {
                    headers = values;
                } else {
                    Map<String, Object> row = new HashMap<>();
                    for (int i = 0; i < headers.length && i < values.length; i++) {
                        String value = values[i].trim();
                        Object parsedValue = parseValue(value);
                        row.put(headers[i].trim(), parsedValue);
                    }
                    csvData.add(row);
                }
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Map<String, Object>> getData() {
        return csvData;
    }

    private Object parseValue(String value) {
        if (value == null || value.isEmpty()) {
            return "";
        }

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ignored) {
        }

        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException ignored) {
        }

        if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
            return Boolean.parseBoolean(value);
        }

        return value;
    }
}

