package reportGeneration.storage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class TableDataStorage {
    private boolean initalized = false;
    private static ObservableMap<String, String> items;

    private TableDataStorage() {
        if (!initalized) {
            try {
                init();
            } catch (Exception e) {
                System.out.println("Could not init TableDataStorage");
            }
            initalized = true;
        }
    }

    public static TableDataStorage getInstance() {
        return TableDataStorage.SingletonHolder.INSTANCE;
    }

    public static ObservableMap<String, String> getItems() {
        return items;
    }


    public static void add(String key, String val){
        items.put(key, val);
    }

    private void init() {
        items = FXCollections.observableHashMap();
    }


    private static class SingletonHolder {
        public static final TableDataStorage INSTANCE = new TableDataStorage();
    }
}
