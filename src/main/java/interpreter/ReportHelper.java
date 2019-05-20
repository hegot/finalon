package interpreter;

import entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReportHelper {
    private ObservableList<Item> items;

    public ReportHelper(ObservableList<Item> items) {
        this.items = items;
    }

    protected Item getItemByCode(String code) {
        for (Item item : this.items) {
            if (item.getShortName().equals(code)) {
                return item;
            }
        }
        return null;
    }

    protected ObservableList<Item> getItems(int id) {
        ObservableList<Item> Items = FXCollections.observableArrayList();
        for (Item item : items) {
            if (item.getParent() == id) {
                Items.add(item);
            }
        }
        return Items;
    }
}
