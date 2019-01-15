package entities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Sheet {

    public int id;
    public String name;
    public int parentTpl;
    public ObservableList<Item> items;

    public Sheet(
            int id,
            String name,
            int parentTpl,
            ObservableList<Item> items
    ) {
        this.id = id;
        this.name = name;
        this.parentTpl = parentTpl;
        this.items = items;
    }

    public Sheet(
            int id,
            String name,
            int parentTpl
    ) {
        this.id = id;
        this.name = name;
        this.parentTpl = parentTpl;
        this.items = FXCollections.observableArrayList();
    }

}
