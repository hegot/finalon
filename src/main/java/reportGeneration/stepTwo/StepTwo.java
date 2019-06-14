package reportGeneration.stepTwo;

import database.template.DbItemHandler;
import defaultData.DefaultTemplate;
import entities.Item;
import finalonWindows.SceneBase;
import finalonWindows.reusableComponents.ItemsTable.ItemsTable;
import globalReusables.SheetsGetter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.SettingsStorage;

import java.util.ArrayList;

public class StepTwo extends ItemsTable {
    private ObservableList<Item> items;

    public StepTwo() {
        super(ItemsStorage.getItems());
        Periods.getInstance();
        this.items = ItemsStorage.getItems();
    }

    public TabPane show() {
        setItems();
        return getTemplateEditable();
    }

    private void setItems() {
        if (items.size() == 0) {
            int tpl = Integer.parseInt(SettingsStorage.getSettings().get("template"));
            DbItemHandler itemsHandler = new DbItemHandler();
            ObservableList<Item> dbItems = itemsHandler.getItems(tpl);
            if (dbItems.size() == 0) {
                dbItems = FXCollections.observableArrayList(
                        DefaultTemplate.getTpl()
                );
            } else {
                dbItems.add(itemsHandler.getItem(tpl));
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
        Periods periods = new Periods();
        ArrayList<String> arr = periods.getPeriodArr();
        for (String col : arr) {
            table.getColumns().add(cols.getPeriodCol(col));
        }
        return table;
    }
}

