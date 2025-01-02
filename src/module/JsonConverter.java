package module;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonConverter {

    public static JsonObject convertToJson(String jsonString) {
        String validJsonString = jsonString.replace("'", "\"");
        JsonObject jsonObject = JsonParser.parseString(validJsonString).getAsJsonObject();

        return jsonObject;
    }
}