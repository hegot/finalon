package finalonWindows.templateScene.templates;

import entities.Item;
import finalonWindows.reusableComponents.ItemsTable.ItemsGetter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;

import java.util.ArrayList;


public class TemplateEditable {

    private ObservableList<Item> items;
    private int rootId;
    private ArrayList<TreeItem> roots;

    public TemplateEditable(ObservableList<Item> items) {
        this.items = items;
        this.roots = new ArrayList<>();
        setRoot();
    }

    public TabPane getTemplateEditable() {
        TabPane tabs = new TabPane();
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        ObservableList<Item> Sheets = getChildren(rootId);
        for (Item Sheet : Sheets) {
            Tab tab = new Tab();
            Item sheet = Sheet;
            tab.setText(sheet.getName());
            tab.setContent(getSingleTable(sheet.getId(), sheet.getName()));
            tabs.getTabs().add(tab);
        }
        return tabs;
    }

    private TableView<Item> getSingleTable(int Id, String SheetName) {
        TableView<Item> table = new TableView<>();
        table.setEditable(true);
        table.setPrefSize(300, 1900);
        RemoveHandler removeHandler = new RemoveHandler();
        Columns cols = new Columns(removeHandler);
        if (SheetName.equals("Statement of Financial Position \n (Balance Sheet)") || SheetName.equals("Other Data")) {
            table.getColumns().addAll(cols.getNameCol(), cols.getCodeCol(), cols.isPositiveCol(), cols.buttonCol());
        } else {
            table.getColumns().addAll(cols.getNameCol(), cols.getCodeCol(), cols.isPositiveCol(), cols.finResultCol(), cols.buttonCol());
        }
        ItemsGetter itemsGetter = new ItemsGetter(Id, this.items);
        table.getItems().addAll(itemsGetter.getItems());


        return table;
    }


    public void setRoot() {
        for (Item item : this.items) {
            if (item.getParent() == 0) {
                this.rootId = item.getId();
            }
        }
    }


    private ObservableList<Item> getChildren(int id) {
        ObservableList<Item> Items = FXCollections.observableArrayList();
        for (Item item : items) {
            if (item.getParent() == id) {
                Items.add(item);
            }
        }
        return Items;
    }


}

