package finalonWindows.reusableComponents.ItemsTable;

import entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class ItemsTable {

    private ObservableList<Item> items;
    private ArrayList<Item> roots;

    public ItemsTable(ObservableList<Item> items) {
        this.items = items;
        this.roots = new ArrayList<>();
    }

    protected TableView<Item> getTable(int Id) {
        TableView<Item> table = new TableView<>();
        ItemsGetter itemsGetter = new ItemsGetter(Id, this.items);
        table.getItems().addAll(itemsGetter.getItems());
        return table;
    }


    protected ObservableList<Item> getChildren(int id) {
        ObservableList<Item> Items = FXCollections.observableArrayList();
        for (Item item : items) {
            if (item.getParent() == id) {
                Items.add(item);
            }
        }
        return Items;
    }


}
