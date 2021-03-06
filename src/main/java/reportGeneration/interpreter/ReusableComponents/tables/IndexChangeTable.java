package reportGeneration.interpreter.ReusableComponents.tables;

import database.setting.DbSettingHandler;
import entities.Item;
import globalReusables.Setting;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableMap;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import reportGeneration.interpreter.ReusableComponents.helpers.Calc;
import reportGeneration.interpreter.ReusableComponents.helpers.Formatter;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;

import java.util.ArrayList;
import java.util.Collections;


public class IndexChangeTable extends ItemsTable {
    private int rootId;
    private int periodsSize;
    private ArrayList<String> periods;

    public IndexChangeTable(
            int rootId
    ) {
        super(ItemsStorage.getItems());
        this.rootId = rootId;
        this.periods = Periods.getPeriodArr();
        this.periodsSize = periods.size();
    }

    public TableView<Item> get() {
        TableView<Item> table = getTable(rootId);
        Item totall = getTotall(rootId);
        table.getItems().add(totall);
        table.getStyleClass().add("report-table");
        table.getColumns().addAll(getNameCol());
        ArrayList<TableColumn> colsArr = getPeriodCols();
        for (TableColumn col : colsArr) {
            table.getColumns().add(col);
        }
        if (periodsSize > 3) {
            String colStart = periods.get(periodsSize - 2);
            String colEnd = periods.get(periodsSize - 1);
            table.getColumns().addAll(
                    getAbsoluteComparisonCol(colStart, colEnd),
                    getFirstLastAbsComparisonCol(),
                    getRelativeComparisonCol(colStart, colEnd),
                    getFirstLastPercentComparisonCol()
            );
        } else {
            for (TableColumn col : getCols("Absolute")) {
                table.getColumns().add(col);
            }
            if (periodsSize > 2) {
                table.getColumns().add(getFirstLastAbsComparisonCol());
            }
            for (TableColumn col : getCols("Relative")) {
                table.getColumns().add(col);
            }
            if (periodsSize > 2) {
                table.getColumns().add(getFirstLastPercentComparisonCol());
            }
        }
        return table;
    }


    private ArrayList<TableColumn> getCols(String type) {
        ArrayList<TableColumn> colsArr = new ArrayList<>();
        ArrayList<String> arr = Periods.getPeriodArr();
        int count = arr.size() - 1;
        if (count > 0) {
            String colStart;
            String colEnd;
            TableColumn col;
            for (int j = 0; j < count; j++) {
                colStart = arr.get(j);
                colEnd = arr.get(j + 1);
                col = (type.equals("Absolute")) ? getAbsoluteComparisonCol(colStart, colEnd) : getRelativeComparisonCol(colStart, colEnd);
                colsArr.add(col);
            }
        }
        String order = DbSettingHandler.getSetting(Setting.yearOrder);
        if (order.equals("DESCENDING")) {
            Collections.reverse(colsArr);
        }
        return colsArr;
    }

    private Item getTotall(int id) {
        for (Item item : ItemsStorage.getItems()) {
            if (item.getId() == id) {
                Item parent = new Item(
                        0,
                        "Totall: ",
                        item.getShortName(),
                        item.getIsPositive(),
                        item.getFinResult(),
                        item.getParent(),
                        item.getLevel()
                );
                parent.setValues(item.getValues());
                return parent;
            }
        }
        return null;
    }

    protected TableColumn getRelativeComparisonCol(String colStart, String colEnd) {
        String colname = "Percentage change\n" + Formatter.formatDate(colEnd) + " to \n" + Formatter.formatDate(colStart);
        TableColumn<Item, String> col = new TableColumn<Item, String>(colname);
        col.setMinWidth(150);
        col.getStyleClass().add("period-col");
        col.setCellValueFactory(cellData -> {
            ObservableMap<String, Double> values = getValues(cellData);
            if (values != null) {
                Double colStartVAl = values.get(colStart);
                Double colEndVAl = values.get(colEnd);
                if (colStartVAl != null && colEndVAl != null) {
                    String relative = Calc.getRelativeChange(colStartVAl, colEndVAl);
                    if (relative.length() > 0) {
                        return new SimpleStringProperty(Formatter.stringCommaFormat(relative) + "%");
                    }
                }
            }
            return null;
        });
        return col;
    }

    protected TableColumn getFirstLastAbsComparisonCol() {
        ArrayList<String> periods = Periods.getPeriodArr();
        String colStart = periods.get(0);
        String colEnd = periods.get(periods.size() - 1);
        String colname = "Absolute Change\n" + Formatter.formatDate(colEnd) +
                " to \n" + Formatter.formatDate(colStart);
        TableColumn<Item, String> col = new TableColumn<Item, String>(colname);
        col.getStyleClass().add("period-col");
        col.setMinWidth(150);
        col.setSortable(false);
        col.setCellValueFactory(cellData -> {
            Item item = (Item) cellData.getValue();
            if (item != null) {
                return Calc.diff(
                        item.getVal(colStart),
                        item.getVal(colEnd)
                );
            }
            return null;
        });
        return col;
    }

    protected TableColumn getFirstLastPercentComparisonCol() {
        ArrayList<String> periods = Periods.getPeriodArr();
        String colStart = periods.get(0);
        String colEnd = periods.get(periods.size() - 1);
        String colname = "Percentage change\n" + Formatter.formatDate(colEnd) + " to \n" + Formatter.formatDate(colStart);
        TableColumn<Item, String> col = new TableColumn<Item, String>(colname);
        col.setMinWidth(150);
        col.getStyleClass().add("period-col");
        col.setCellValueFactory(cellData -> {
            ObservableMap<String, Double> values = getValues(cellData);
            if (values != null) {
                Double colStartVAl = values.get(colStart);
                Double colEndVAl = values.get(colEnd);
                if (colStartVAl != null && colEndVAl != null) {
                    String relative = Calc.getRelativeChange(colStartVAl, colEndVAl);
                    if (relative.length() > 0) {
                        return new SimpleStringProperty(Formatter.stringCommaFormat(relative) + "%");
                    }
                }
            }
            return null;
        });
        return col;
    }
}
