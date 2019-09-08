package reportGeneration.storage;

import entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class ItemsStorage {
    private static ObservableList<Item> items = FXCollections.observableArrayList();
    private static ObservableMap<String, Item> indexes = FXCollections.observableHashMap();

    public static ObservableList<Item> getItems() {
        return items;
    }

    public static void emptyItems() {
        items = FXCollections.observableArrayList();
    }

    public static void setItems(ObservableList<Item> itemsList) {
        items = itemsList;
    }

    public static void addItem(Item item) {
        items.add(item);
    }


    public static Item get(String code) {
        Item index = indexes.get(code);
        if (index != null) {
            return index;
        } else {
            for (Item item : getItems()) {
                if (item.getShortName().equals(code)) {
                    indexes.put(item.getShortName(), item);
                    return item;
                }
            }
        }
        return null;
    }

    public static ObservableList<Item> getItems(int id) {
        ObservableList<Item> Items = FXCollections.observableArrayList();
        for (Item item : items) {
            if (item.getParent() == id) {
                Items.add(item);
            }
        }
        return Items;
    }

    public static ObservableList<Item> getItemsDeep(int id) {
        ObservableList<Item> Items = FXCollections.observableArrayList();
        for (Item item : items) {
            if (item.getParent() == id) {
                int deepId = item.getId();
                for (Item itemDeep : items) {
                    if (itemDeep.getParent() == deepId) {
                        Items.add(itemDeep);
                    }
                }
            }
        }
        return Items;
    }

}
