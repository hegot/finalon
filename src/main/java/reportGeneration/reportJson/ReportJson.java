package reportGeneration.reportJson;

import com.google.gson.*;
import entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.SettingsStorage;

import java.util.Map;

public class ReportJson {

    public static String itemsToJson() {
        Gson gson = new Gson();
        return gson.toJson(ItemsStorage.getItems());
    }

    public static String settingsToJson() {
        Gson gson = new Gson();
        return gson.toJson(SettingsStorage.getSettings());
    }

    public static ObservableMap<String, String> jsonToSettings(String json) {
        ObservableMap<String, String> settings = FXCollections.observableHashMap();
        JsonParser parser = new JsonParser();
        JsonObject element = parser.parse(json).getAsJsonObject();
        for (Map.Entry<String, JsonElement> entry : element.entrySet()) {
            settings.put(entry.getKey(), entry.getValue().getAsString());
        }
        return settings;
    }

    public static ObservableList<Item> jsonToItems(String json) {
        ObservableList<Item> items = FXCollections.observableArrayList();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(json);
        JsonArray objArray = element.getAsJsonArray();
        JsonObject dataset;
        JsonObject vals;
        ObservableMap<String, Double> values;
        Item item;
        for (int i = 0; i < objArray.size(); i++) {
            dataset = objArray.get(i).getAsJsonObject();
            vals = dataset.get("values").getAsJsonObject();
            values = getValues(vals);
            item = new Item(
                    dataset.get("id").getAsInt(),
                    dataset.get("name").getAsString(),
                    dataset.get("shortName").getAsString(),
                    dataset.get("isPositive").getAsBoolean(),
                    dataset.get("finResult").getAsBoolean(),
                    dataset.get("parent").getAsInt(),
                    dataset.get("parentSheet").getAsInt(),
                    values,
                    dataset.get("level").getAsInt(),
                    dataset.get("weight").getAsInt()
            );
            items.add(item);
        }
        return items;
    }

    private static ObservableMap<String, Double> getValues(JsonObject vals) {
        ObservableMap<String, Double> values = FXCollections.observableHashMap();
        for (Map.Entry<String, JsonElement> entry : vals.entrySet()) {
            values.put(entry.getKey(), entry.getValue().getAsDouble());
        }
        return values;
    }
}
