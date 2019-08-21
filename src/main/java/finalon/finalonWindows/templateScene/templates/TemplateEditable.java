package finalon.finalonWindows.templateScene.templates;

import finalon.entities.Item;
import finalon.globalReusables.ItemsGetter;
import finalon.globalReusables.SheetsGetter;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;

import java.util.Collections;


public class TemplateEditable {

    private ObservableList<Item> items;

    public TemplateEditable(ObservableList<Item> items) {
        this.items = items;
    }

    public TabPane getTemplateEditable() {
        TabPane tabs = new TabPane();
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        SheetsGetter sheetsGetter = new SheetsGetter(items);
        ObservableList<Item> Sheets = sheetsGetter.getSheets();
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
        table.setPrefHeight(1900);
        if (SheetName.equals("Statement of Financial Position \n (Balance Sheet)") || SheetName.equals("Other Data")) {
            table.getColumns().addAll(
                    Columns.getDragCol(),
                    Columns.getNameCol(),
                    Columns.getCodeCol(),
                    Columns.isPositiveCol(),
                    Columns.buttonCol());
        } else {
            table.getColumns().addAll(
                    Columns.getDragCol(),
                    Columns.getNameCol(),
                    Columns.getCodeCol(),
                    Columns.isPositiveCol(),
                    Columns.finResultCol(),
                    Columns.buttonCol());
        }
        ItemsGetter itemsGetter = new ItemsGetter(Id, this.items);
        ObservableList<Item> unsortedItems = itemsGetter.getItems();
        Collections.sort(unsortedItems);
        table.getItems().addAll(unsortedItems);
        return table;
    }


}

