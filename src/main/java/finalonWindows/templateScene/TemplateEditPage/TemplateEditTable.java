package finalonWindows.templateScene.TemplateEditPage;

import database.template.DbItemHandler;
import entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

public class TemplateEditTable {

    private static ObservableList<Item> itemsList;
    private static int root;

    public static int getRoot() {
        return root;
    }

    public static ObservableList<Item> getItems() {
        return itemsList;
    }

    static TabPane getTemplateEditable(int id) {
        root = id;
        itemsList = FXCollections.observableArrayList();
        Item root =  DbItemHandler.getItem(id);
        if(root != null){
            itemsList.add(root);
        }
        TabPane tabs = new TabPane();
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        ObservableList<Item> Sheets = DbItemHandler.getItems(id);
        for (Item sheet : Sheets) {
            Tab tab = new Tab();
            itemsList.add(sheet);
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

        addAllChilds(Id, table);

        table.setRowFactory(row -> new TableRow<Item>() {
            @Override
            public void updateItem(Item item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null && !empty) {
                    Integer level = item.getLevel();
                    getStyleClass().removeAll("templates-row-level-1", "templates-row-level-2", "templates-row-level-3", "templates-row-level-4", "templates-row-level-5");
                    if (level.equals(1)) {
                        getStyleClass().add("templates-row-level-1");
                    }
                    if (level.equals(2)) {
                        getStyleClass().add("templates-row-level-2");
                    }
                    if (level.equals(3)) {
                        getStyleClass().add("templates-row-level-3");
                    }
                    if (level.equals(4)) {
                        getStyleClass().add("templates-row-level-4");
                    }
                    if (level.equals(5)) {
                        getStyleClass().add("templates-row-level-5");
                    }
                }
            }
        });
        return table;
    }

    private static void addAllChilds(int Id, TableView<Item> table) {
        ObservableList<Item> items = DbItemHandler.getItems(Id);
        if (items.size() > 0) {
            for(int i = 0; i< items.size(); i++){
                Item item = items.get(i);
                addAllChilds(item.getId(), table);
                table.getItems().add(item);
                itemsList.add(item);
            }
        }
    }

}

