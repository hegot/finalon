package reportGeneration.storage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class ResultsStorage {
    private static ObservableMap<String, ResultItem> items;
    private boolean initalized = false;

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

    public static ObservableMap<String, ResultItem> getItems() {
        return items;
    }


    public static void addStr(String type, String key, String val) {
        ResultItem<String> item = new ResultItem<>(val, type);
        items.put(key, item);
    }

    public static void addTable(String key, TwoDList val) {
        ResultItem<TwoDList> item = new ResultItem<TwoDList>(val, "table");
        items.put(key, item);
    }

    private void init() {
        items = FXCollections.observableHashMap();
    }


    private static class SingletonHolder {
        public static final ResultsStorage INSTANCE = new ResultsStorage();
    }
}


