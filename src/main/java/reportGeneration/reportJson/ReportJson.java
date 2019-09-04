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

    public static String itemsToJson(){
        Gson gson = new Gson();
        return gson.toJson(ItemsStorage.getItems());
    }

    public static String settingsToJson(){
        Gson gson = new Gson();
        return gson.toJson(SettingsStorage.getSettings());
    }

    public static ObservableList<Item> jsonToItems(String json){
        ObservableList<Item> items = FXCollections.observableArrayList();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(json);
            JsonArray objArray = element.getAsJsonArray();
        for (int i = 0; i < objArray.size(); i++) {
            JsonObject dataset = objArray.get(i).getAsJsonObject();
            JsonObject vals = dataset.get("values").getAsJsonObject();
            ObservableMap<String, Double> values = getValues(vals);
            Item item = new Item(
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

    private static ObservableMap<String, Double> getValues(JsonObject vals){
        ObservableMap<String, Double> values = FXCollections.observableHashMap();
        for (Map.Entry<String, JsonElement> entry : vals.entrySet())
        {
            values.put(entry.getKey(), entry.getValue().getAsDouble());
        }
        return values;
    }
}
