package finalonWindows.templateScene.templates;

import entities.Item;
import globalReusables.ItemsGetter;
import globalReusables.SheetsGetter;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

import java.util.Collections;

class TemplateEditTable {

    static TabPane getTemplateEditable() {
        TabPane tabs = new TabPane();
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        SheetsGetter sheetsGetter = new SheetsGetter(TemplateEditPage.getItems());
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

    private static TableView<Item> getSingleTable(int Id, String SheetName) {
        TableView<Item> table = new TableView<>();
        table.setEditable(true);
        table.setMaxHeight(780);
        table.setPrefHeight(780);
        if (SheetName.equals("Statement of Financial Position \n (Balance Sheet)") || SheetName.equals("Other Data")) {
            table.getColumns().addAll(
                    TemplateTableColumns.getDragCol(),
                    TemplateTableColumns.getNameCol(),
                    TemplateTableColumns.getCodeCol(),
                    TemplateTableColumns.isPositiveCol(),
                    TemplateTableColumns.buttonCol());
        } else {
            table.getColumns().addAll(
                    TemplateTableColumns.getDragCol(),
                    TemplateTableColumns.getNameCol(),
                    TemplateTableColumns.getCodeCol(),
                    TemplateTableColumns.isPositiveCol(),
                    TemplateTableColumns.finResultCol(),
                    TemplateTableColumns.buttonCol());
        }
        ItemsGetter itemsGetter = new ItemsGetter(Id, TemplateEditPage.getItems());
        ObservableList<Item> unsortedItems = itemsGetter.getItems();
        Collections.sort(unsortedItems);
        table.getItems().addAll(unsortedItems);

        table.setRowFactory(row -> new TableRow<Item>() {
            @Override
            public void updateItem(Item item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null && !empty) {
                    Integer level = item.getLevel();
                    getStyleClass().remove("templates-row-level-max");
                    getStyleClass().remove("templates-row-level-4");
                    if (level.equals(1) || level.equals(2) || level.equals(3)) {
                        getStyleClass().add("templates-row-level-max");
                    }
                    if (level.equals(4)) {
                        getStyleClass().add("templates-row-level-4");
                    }
                }
            }
        });
        return table;
    }


}

