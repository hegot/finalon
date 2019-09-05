package reportGeneration.storage;

import database.setting.DbSettingHandler;
import globalReusables.Setting;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.util.Calendar;

public class SettingsStorage {
    private static ObservableMap<String, String> settings = initStorage();

    private static ObservableMap<String, String> initStorage() {
        ObservableMap<String, String> settings = FXCollections.observableHashMap();
        String year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
        String defaultCurrency = DbSettingHandler.getSetting(Setting.defaultCurrency);
        if (defaultCurrency.length() == 0) defaultCurrency = "USD";
        settings.put("reportId", "-1");
        settings.put("company", "");
        settings.put("step", "one");
        settings.put("year", year);
        settings.put("date", "31.12");
        settings.put("template", "");
        settings.put("standard", "1");
        settings.put("defaultCurrency", defaultCurrency);
        settings.put("amount", "million");
        settings.put("reportStep", "year");
        settings.put("industry", "");
        return settings;
    }

    public static ObservableMap<String, String> getSettings() {
        return settings;
    }

    public static void setSettings(ObservableMap<String, String> newSettings) {
        settings = newSettings;
    }

    public static String get(String key) {
        return settings.get(key);
    }

    public static int getInt(String key) {
        return Integer.parseInt(settings.get(key));
    }

    public static String put(String key, String value) {
        return settings.put(key, value);
    }

    public static void replace(String key, String value) {
        settings.replace(key, value);
    }

}
