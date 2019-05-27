package reportGeneration.storage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class SettingsStorage {

    private static ObservableMap<String, String> settings;
    private boolean initalized = false;

    private SettingsStorage() {
        if (!initalized) {
            try {
                init();
            } catch (Exception e) {
                System.out.println("Could not init settings storage");
            }
            initalized = true;
        }
    }

    public static SettingsStorage getInstance() {
        return SettingsStorage.SingletonHolder.INSTANCE;
    }

    public static ObservableMap<String, String> getSettings() {
        return settings;
    }

    public static void setSettings(ObservableMap<String, String> settingsMap) {
        settings = settingsMap;
    }

    private void init() {
        settings = FXCollections.observableHashMap();
        System.out.println("Settings Storage added!");
    }

    private static class SingletonHolder {
        public static final SettingsStorage INSTANCE = new SettingsStorage();
    }
}
