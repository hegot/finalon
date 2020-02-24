package reportGeneration.stepTwo;

import database.setting.DbSettingHandler;
import database.template.DbItemHandler;
import defaultData.DefaultTemplate;
import entities.Item;
import globalReusables.ItemsGetter;
import globalReusables.Setting;
import globalReusables.SheetsGetter;
import globalReusables.StandardAndIndustry;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import reportGeneration.AddReportScene;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.SettingsStorage;

import java.util.ArrayList;
import java.util.Collections;

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
        Tab tab;
        Item sheet;
        TableView<Item> table;
        for (Item Sheet : Sheets) {
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
        ItemsGetter itemsGetter = new ItemsGetter(Id, this.items);
        ObservableList<Item> items = itemsGetter.getItems();
        Collections.sort(items);
        table.getItems().addAll(items);
        return table;
    }

}

