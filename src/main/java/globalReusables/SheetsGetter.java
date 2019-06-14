package globalReusables;

import entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SheetsGetter {

    private ObservableList<Item> items;

    public SheetsGetter(ObservableList<Item> items) {
        this.items = items;
    }

    private int getRoot() {
        for (Item item : this.items) {
            if (item.getParent() == 0) {
                return item.getId();
            }
        }
        return 0;
    }


    public ObservableList<Item> getSheets() {
        int id = getRoot();
        ObservableList<Item> Items = FXCollections.observableArrayList();
        for (Item item : items) {
            if (item.getParent() == id) {
                Items.add(item);
            }
        }
        return Items;
    }
}
