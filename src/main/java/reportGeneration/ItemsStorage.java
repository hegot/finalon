package reportGeneration;

import entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ItemsStorage {
    private static ObservableList<Item> items;
    private boolean initalized = false;

    private ItemsStorage() {
        if (!initalized) {
            try {
                init();
            } catch (Exception e) {
                System.out.println("Could not init");
            }
            initalized = true;
        }
    }

    public static ItemsStorage getInstance() {
        return ItemsStorage.SingletonHolder.INSTANCE;
    }

    public static ObservableList<Item> getItems() {
        return items;
    }

    public static void setItems(ObservableList<Item> itemsList) {
        items = itemsList;
    }

    private void init() {
        items = FXCollections.observableArrayList();
        System.out.println("Items Storage added!");
    }

    private static class SingletonHolder {
        public static final ItemsStorage INSTANCE = new ItemsStorage();
    }
}
