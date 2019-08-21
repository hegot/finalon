package finalon.reportGeneration.interpreter.ReusableComponents.tables;

import finalon.entities.Item;
import finalon.globalReusables.ItemsGetter;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class ItemsTable {
    private ObservableList<Item> items;

    public ItemsTable(ObservableList<Item> items) {
        this.items = items;
    }

    protected TableView<Item> getTable(int Id) {
        TableView<Item> table = new TableView<>();
        ItemsGetter itemsGetter = new ItemsGetter(Id, this.items, true);
        table.getItems().addAll(itemsGetter.getItems());
        return table;
    }
}