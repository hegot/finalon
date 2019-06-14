package finalonWindows.reusableComponents.ItemsTable;

import entities.Item;
import globalReusables.ItemsGetter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class ItemsTable {
    private ObservableList<Item> items;

    public ItemsTable(ObservableList<Item> items) {
        this.items = items;
    }

    protected TableView<Item> getTable(int Id) {
        TableView<Item> table = new TableView<>();
        ItemsGetter itemsGetter = new ItemsGetter(Id, this.items);
        table.getItems().addAll(itemsGetter.getItems());
        return table;
    }
}
