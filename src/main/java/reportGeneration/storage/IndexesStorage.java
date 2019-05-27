package reportGeneration.storage;

import entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class IndexesStorage {
    private static ObservableMap<String, Item> indexes;
    private boolean initalized = false;

    private IndexesStorage() {
        if (!initalized) {
            try {
                init();
            } catch (Exception e) {
                System.out.println("Could not init indexes storage");
            }
            initalized = true;
        }
    }

    public static IndexesStorage getInstance() {
        return IndexesStorage.SingletonHolder.INSTANCE;
    }

    public static Item get(String key) {
        return indexes.get(key);
    }

    public static void put(String key, Item item) {
        indexes.put(key, item);
    }

    private void init() {
        indexes = FXCollections.observableHashMap();
        System.out.println("Settings Storage added!");
    }

    private static class SingletonHolder {
        public static final IndexesStorage INSTANCE = new IndexesStorage();
    }
}
