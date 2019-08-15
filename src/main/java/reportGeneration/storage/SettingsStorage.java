package reportGeneration.storage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.util.Calendar;

public class SettingsStorage {
    private static ObservableMap<String, String> settings = initStorage();

    private static ObservableMap<String, String> initStorage() {
        ObservableMap<String, String> settings = FXCollections.observableHashMap();
        String year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
        settings.put("company", "");
        settings.put("step", "one");
        settings.put("year", year);
        settings.put("date", "31.12");
        settings.put("template", "");
        settings.put("standard", "");
        settings.put("defaultCurrency", "USD");
        settings.put("amount", "million");
        settings.put("reportStep", "year");
        settings.put("industry", "");
        return settings;
    }

    public static ObservableMap<String, String> getSettings() {
        return settings;
    }
}
