package reportGeneration;

import entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class GlobalStorage {
    private static ObservableMap<String, Item> itemsStorage;
    private boolean initalized = false;

    private GlobalStorage() {
        if (!initalized) {
            try {
                init();
            } catch (Exception e) {
                System.out.println("Could not init");
            }
            initalized = true;
        }
    }

    public static GlobalStorage getInstance() {
        return GlobalStorage.SingletonHolder.INSTANCE;
    }

    public static Item getItem(String key) {
        return itemsStorage.get(key);
    }

    public static void addItem(String key, Item item) {
        itemsStorage.put(key, item);
    }

    private void init() {
        itemsStorage = FXCollections.observableHashMap();
        System.out.println("Items Storage added!");
    }

    private static class SingletonHolder {
        public static final GlobalStorage INSTANCE = new GlobalStorage();
    }
}
