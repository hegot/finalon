package reportGeneration.storage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class ResultsStorage {
    private boolean initalized = false;
    private static ObservableMap<String, String> items;

    private ResultsStorage() {
        if (!initalized) {
            try {
                init();
            } catch (Exception e) {
                System.out.println("Could not init ResultsStorage");
            }
            initalized = true;
        }
    }

    public static ResultsStorage getInstance() {
        return ResultsStorage.SingletonHolder.INSTANCE;
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
        public static final ResultsStorage INSTANCE = new ResultsStorage();
    }
}
