package reportGeneration.stepTwo;

import database.setting.DbSettingHandler;
import database.template.DbItemHandler;
import defaultData.DefaultTemplate;
import entities.Item;
import globalReusables.ItemsGetter;
import globalReusables.Setting;
import globalReusables.SheetsGetter;
import globalReusables.StandardAndIndustry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.SettingsStorage;

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
            ObservableList<Item> dbItems = FXCollections.observableArrayList();
            Integer industryId = SettingsStorage.getInt("industry");
            if (industryId == null) {
                industryId = StandardAndIndustry.getIndustryId();
            }

            ObservableList<Item> tpls = DbItemHandler.getTemplateForIndustry(industryId);
            if (tpls.size() > 0) {
                Item tpl = tpls.get(0);
                dbItems = DbItemHandler.getItems(tpl.getId());
                dbItems.add(DbItemHandler.getItem(tpl.getId()));
            }
            if (dbItems.size() == 0) {
                dbItems = FXCollections.observableArrayList(
                        DefaultTemplate.getTpl()
                );
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
        String order = DbSettingHandler.getSetting(Setting.yearOrder);
        if (order.equals("ASCENDING")) {
            for (String col : arr) {
                table.getColumns().add(cols.getPeriodCol(col));
            }
        } else {
            for (int i = arr.size(); i-- > 0; ) {
                String col = arr.get(i);
                table.getColumns().add(cols.getPeriodCol(col));
            }
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

