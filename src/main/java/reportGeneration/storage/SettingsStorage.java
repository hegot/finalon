package reportGeneration.storage;

import database.setting.DbSettingHandler;
import globalReusables.Setting;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.util.Calendar;

public class SettingsStorage {
    private static ObservableMap<String, String> settings = initStorage();


    private static ObservableMap<String, String> initStorage() {
        ObservableMap<String, String> settingsMap = FXCollections.observableHashMap();
        String year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
        String defaultCurrency = DbSettingHandler.getSetting(Setting.defaultCurrency);
        String numberFormat = DbSettingHandler.getSetting(Setting.numberFormat);
        if (defaultCurrency.length() == 0) defaultCurrency = "USD";

        settingsMap.put("reportId", "-1");
        settingsMap.put("company", "");
        settingsMap.put("step", "one");
        settingsMap.put("year", year);
        settingsMap.put("date", "31.12");
        settingsMap.put("template", "");
        settingsMap.put("standard", "1");
        settingsMap.put("defaultCurrency", defaultCurrency);
        settingsMap.put("amount", "million");
        settingsMap.put("reportStep", "year");
        settingsMap.put("industry", "");
        settingsMap.put("numberFormat", numberFormat);
        return settingsMap;
    }

    public static void reInitStorage() {
        settings = initStorage();
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

    public static Integer getInt(String key) {
        try {
            return Integer.parseInt(settings.get(key));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String put(String key, String value) {
        return settings.put(key, value);
    }

    public static void replace(String key, String value) {
        settings.replace(key, value);
    }

}
