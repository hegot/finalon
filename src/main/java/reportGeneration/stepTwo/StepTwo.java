package reportGeneration.stepTwo;

import database.setting.DbSettingHandler;
import entities.Item;
import globalReusables.Setting;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import reportGeneration.AddReportScene;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;

import java.util.ArrayList;
import java.util.Comparator;

public class StepTwo {

    public TabPane show() {
        return getTemplateEditable();
    }


    TabPane getTemplateEditable() {
        TabPane tabs = new TabPane();
        tabs.getStyleClass().add("report-tabs");
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        ObservableList<Item> sheets = ItemsStorage.getSheets();
        Tab tab;
        Item sheet;
        for (Item Sheet : sheets) {
            tab = new Tab();
            sheet = Sheet;
            tab.setText(sheet.getName());
            tab.setContent(getSingleTable(sheet.getId()));
            tabs.getTabs().add(tab);
        }
        return tabs;
    }

    private VBox getSingleTable(int Id) {
        VBox wrap = new VBox(20);
        TableView<Item> table = getTable(Id);
        table.setSelectionModel(null);
        table.getStyleClass().add("report-input");
        table.setEditable(true);
        table.setPrefWidth(880);
        table.setFixedCellSize(30);

        table.prefHeightProperty().bind(table.fixedCellSizeProperty().multiply(Bindings.size(table.getItems()).add(2.01)));
        table.minHeightProperty().bind(table.prefHeightProperty());
        table.maxHeightProperty().bind(table.prefHeightProperty());


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
                table.getColumns().add(cols.getPeriodCol(arr.get(i)));
            }
        }
        wrap.getChildren().addAll(table, AddReportScene.headerMenu());
        return wrap;
    }

    private TableView<Item> getTable(int Id) {
        TableView<Item> table = new TableView<>();
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

        addAllChilds(Id, table);
        return table;
    }


    private static void addAllChilds(int Id, TableView<Item> table) {
        ObservableList<Item> items = ItemsStorage.getItems(Id);
        if (items.size() > 0) {
            items.sort(Comparator.comparing(Item::getWeight));
            for (int i = 0; i < items.size(); i++) {
                Item item = items.get(i);
                if (item.getLevel() > 3) {
                    table.getItems().add(item);
                    addAllChilds(item.getId(), table);
                } else {
                    addAllChilds(item.getId(), table);
                    table.getItems().add(item);
                }
            }
        }
    }
}

