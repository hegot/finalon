package reportGeneration.stepTwo;

import database.template.DbItemHandler;
import defaultData.DefaultTemplate;
import entities.Item;
import globalReusables.ItemsGetter;
import globalReusables.SheetsGetter;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.SettingsStorage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class StepTwo {
    private ObservableList<Item> items;

    public StepTwo() {
        this.items = ItemsStorage.getItems();
    }

    public TabPane show() {
        setItems();
        return getTemplateEditable();
    }

    private void setItems() {
        if (items.size() == 0) {
            int tpl = Integer.parseInt(SettingsStorage.get("template"));
            ObservableList<Item> dbItems = DbItemHandler.getItems(tpl);
            if (dbItems.size() == 0) {
                dbItems = FXCollections.observableArrayList(
                        DefaultTemplate.getTpl()
                );
            } else {
                dbItems.add(DbItemHandler.getItem(tpl));
            }
            items.addAll(dbItems);
        }
    }


    TabPane getTemplateEditable() {
        TabPane tabs = new TabPane();
        tabs.getStyleClass().add("report-tabs");
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        SheetsGetter sheetsGetter = new SheetsGetter(items);
        ObservableList<Item> Sheets = sheetsGetter.getSheets();
        for (Item Sheet : Sheets) {
            Tab tab = new Tab();
            Item sheet = Sheet;
            tab.setText(sheet.getName());
            TableView<Item> table = getSingleTable(sheet.getId());
            tab.setContent(table);
            tabs.getTabs().add(tab);
        }
        return tabs;
    }

    private TableView<Item> getSingleTable(int Id) {
        TableView<Item> table = getTable(Id);
        table.getStyleClass().add("report-input");
        table.setEditable(true);
        table.setPrefHeight(1900);
        table.setPrefWidth(880);
        Columns cols = new Columns();
        table.getColumns().addAll(cols.getNameCol(), cols.getCodeCol());
        ArrayList<String> arr = Periods.getPeriodArr();
        for (String col : arr) {
            table.getColumns().add(cols.getPeriodCol(col));
        }
        return table;
    }

    private TableView<Item> getTable(int Id) {
        TableView<Item> table = new TableView<>();
        ItemsGetter itemsGetter = new ItemsGetter(Id, this.items);
        ObservableList<Item> items = itemsGetter.getItems();
        table.getItems().addAll(items);
        return table;
    }

}

