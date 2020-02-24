package reportGeneration.interpreter.ReusableComponents.tables;

import database.setting.DbSettingHandler;
import entities.Item;
import globalReusables.ItemsGetter;
import globalReusables.Setting;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import reportGeneration.interpreter.ReusableComponents.helpers.Calc;
import reportGeneration.interpreter.ReusableComponents.helpers.Formatter;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StructureTable {
    private Map<String, Double> totalVals;
    private ObservableList<Item> items;
    private ArrayList<String> periodsArr;

    public StructureTable(
            Item root
    ) {
        ItemsGetter itemsGetter = new ItemsGetter(root, ItemsStorage.getItems(), true);
        this.items = itemsGetter.getItems();
        this.periodsArr = Periods.getPeriodArr();
        this.totalVals = getTotals(root);
    }


    private ObservableList<StructureItem> getStructureItems() {
        ObservableList<StructureItem> StructureItems = FXCollections.observableArrayList();
        for (Item item : items) {
            StructureItems.add(new StructureItem(item, totalVals));
        }
        return StructureItems;
    }

    private Map<String, Double> getTotals(Item root) {
        Map<String, Double> totalVals = new HashMap<>();
        for (String col : periodsArr) {
            totalVals.put(col, root.getVal(col));
        }
        return totalVals;
    }


    public TableView<StructureItem> get() {
        TableView<StructureItem> table = new TableView<>();
        table.getItems().addAll(getStructureItems());
        table.getStyleClass().add("report-table");
        table.getColumns().addAll(getNameCol());
        for (TableColumn col : getStructureCols()) {
            table.getColumns().add(col);
        }
        for (TableColumn col : getAbsoluteChangeCols()) {
            table.getColumns().add(col);
        }
        if (Periods.getPeriodArr().size() > 2) {
            table.getColumns().add(getFirstLastComparisonCol());
        }
        return table;
    }


    private ArrayList<TableColumn> getAbsoluteChangeCols() {
        ArrayList<TableColumn> colsArr = new ArrayList<>();
        int count = periodsArr.size() - 1;
        if (count > 0) {
            String colStart;
            String colEnd;
            for (int j = 0; j < count; j++) {
                colStart = periodsArr.get(j);
                colEnd = periodsArr.get(j + 1);
                colsArr.add(absoluteChangeCol(colStart, colEnd));
            }
        }
        String order = DbSettingHandler.getSetting(Setting.yearOrder);
        if (order.equals("DESCENDING")) {
            Collections.reverse(colsArr);
        }
        return colsArr;
    }

    private ArrayList<TableColumn> getStructureCols() {
        ArrayList<TableColumn> colsArr = new ArrayList<>();
        for (String col : periodsArr) {
            colsArr.add(structureCol(col));
        }
        String order = DbSettingHandler.getSetting(Setting.yearOrder);
        if (order.equals("DESCENDING")) {
            Collections.reverse(colsArr);
        }
        return colsArr;
    }

    private TableColumn absoluteChangeCol(String colStart, String colEnd) {
        String colname = "Absolute Change\n" + Formatter.formatDate(colEnd) + " to \n" + Formatter.formatDate(colStart);
        TableColumn<StructureItem, String> column = new TableColumn<StructureItem, String>(colname);
        column.setMinWidth(150);
        column.getStyleClass().add("period-col");
        column.setCellValueFactory(cellData -> {
            ObservableMap<String, Double> values = getValues(cellData);
            if (values != null) {
                return Calc.diff(
                        values.get(colStart),
                        values.get(colEnd)
                );
            }
            return null;
        });
        return column;
    }

    protected TableColumn getNameCol() {
        TableColumn<StructureItem, String> col = new TableColumn<>("Indicator");
        col.setId("name-column");
        col.setMinWidth(350);
        col.setCellValueFactory(new PropertyValueFactory<>("name"));
        col.setCellFactory(TextFieldTableCell.<StructureItem>forTableColumn());
        return col;
    }

    private TableColumn structureCol(String col) {
        TableColumn<StructureItem, String> column = new TableColumn<StructureItem, String>(Formatter.formatDate(col));
        column.setMinWidth(150);
        column.getStyleClass().add("period-col");
        column.setCellValueFactory(cellData -> {
            ObservableMap<String, Double> values = getValues(cellData);
            if (values != null) {
                Double itemVAl = values.get(col);
                if (itemVAl != null) {
                    return new SimpleStringProperty(Formatter.finalNumberFormat(itemVAl) + "%");
                }
            }
            return null;
        });
        return column;
    }


    protected ObservableMap<String, Double> getValues(TableColumn.CellDataFeatures<StructureItem, String> cellData) {
        StructureItem item = (StructureItem) cellData.getValue();
        if (item != null) {
            if (item.getStructureValues().size() > 0) {
                return item.getStructureValues();
            }
        }
        return null;
    }

    protected TableColumn getFirstLastComparisonCol() {
        ArrayList<String> periods = Periods.getPeriodArr();
        String colStart = periods.get(0);
        String colEnd = periods.get(periods.size() - 1);
        String colname = "Absolute Change\n" + Formatter.formatDate(colEnd) +
                " to \n" + Formatter.formatDate(colStart);
        TableColumn<StructureItem, String> col = new TableColumn<StructureItem, String>(colname);
        col.getStyleClass().add("period-col");
        col.setMinWidth(150);
        col.setSortable(false);
        col.setCellValueFactory(cellData -> {
            ObservableMap<String, Double> values = getValues(cellData);
            if (values != null) {
                return Calc.diff(
                        values.get(colStart),
                        values.get(colEnd)
                );
            }
            return null;
        });
        return col;
    }
}
