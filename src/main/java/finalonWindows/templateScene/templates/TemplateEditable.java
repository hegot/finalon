package finalonWindows.templateScene.templates;

import entities.Item;
import globalReusables.ItemsGetter;
import globalReusables.SheetsGetter;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;


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




}

