package reportGeneration.storage;

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
                System.out.println("Could not init ItemsStorage");
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

    public static void addItem(Item item) {
        items.add(item);
    }

    private void init() {
        items = FXCollections.observableArrayList();
        System.out.println("Items Storage added!");
    }

    public Item getItemByCode(String code) {
        for (Item item : getItems()) {
            if (item.getShortName().equals(code)) {
                return item;
            }
        }
        return null;
    }

    public ObservableList<Item> getItems(int id) {
        ObservableList<Item> Items = FXCollections.observableArrayList();
        for (Item item : getItems()) {
            if (item.getParent() == id) {
                Items.add(item);
            }
        }
        return Items;
    }

    public ObservableList<Item> getItemsDeep(int id) {
        ObservableList<Item> Items = FXCollections.observableArrayList();
        for (Item item : getItems()) {
            if (item.getParent() == id) {
                int deepId = item.getId();
                for (Item itemDeep : getItems()) {
                    if (itemDeep.getParent() == deepId) {
                        Items.add(itemDeep);
                    }
                }
            }
        }
        return Items;
    }

    private static class SingletonHolder {
        public static final ItemsStorage INSTANCE = new ItemsStorage();
    }
}
