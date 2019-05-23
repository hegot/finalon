package reportGeneration.interpreter.ReusableComponents;

import entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import reportGeneration.ItemsStorage;

public class ReportHelper {

    protected Item getItemByCode(String code) {
        for (Item item : ItemsStorage.getItems()) {
            if (item.getShortName().equals(code)) {
                return item;
            }
        }
        return null;
    }

    protected ObservableList<Item> getItems(int id) {
        ObservableList<Item> Items = FXCollections.observableArrayList();
        for (Item item : ItemsStorage.getItems()) {
            if (item.getParent() == id) {
                Items.add(item);
            }
        }
        return Items;
    }

    protected ObservableList<Item> getItemsDeep(int id) {
        ObservableList<Item> Items = FXCollections.observableArrayList();
        for (Item item : ItemsStorage.getItems()) {
            if (item.getParent() == id) {
                int deepId = item.getId();
                for (Item itemDeep : ItemsStorage.getItems()) {
                    if (itemDeep.getParent() == deepId) {
                        Items.add(itemDeep);
                    }
                }
            }
        }
        return Items;
    }
}
