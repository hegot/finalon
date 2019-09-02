package reportGeneration.interpreter.ReusableComponents.tables;

import database.setting.DbSettingHandler;
import entities.Item;
import globalReusables.Setting;
import javafx.beans.property.SimpleStringProperty;
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


public class IndexChangeTable extends ItemsTable {
    private int rootId;

    public IndexChangeTable(
            int rootId
    ) {
        super(ItemsStorage.getItems());
        this.rootId = rootId;
    }

    public TableView<Item> get() {
        TableView<Item> table = getTable(rootId);
        table.getStyleClass().add("report-table");
        table.getColumns().addAll(getNameCol());
        ArrayList<TableColumn> colsArr = getPeriodCols();
        for (TableColumn col : colsArr) {
            table.getColumns().add(col);
        }
        for (TableColumn col : getCols("Absolute")) {
            table.getColumns().add(col);
        }
        for (TableColumn col : getCols("Relative")) {
            table.getColumns().add(col);
        }

        return table;
    }


    private ArrayList<TableColumn> getCols(String type) {
        ArrayList<TableColumn> colsArr = new ArrayList<>();
        ArrayList<String> arr = Periods.getPeriodArr();
        int count = arr.size() - 1;
        if (count > 0) {
            for (int j = 0; j < count; j++) {
                String colStart = arr.get(j);
                String colEnd = arr.get(j + 1);
                TableColumn col = (type.equals("Absolute")) ? getAbsoluteComparisonCol(colStart, colEnd) : getRelativeComparisonCol(colStart, colEnd);
                colsArr.add(col);
            }
        }
        String order = DbSettingHandler.getSetting(Setting.yearOrder);
        if (order.equals("DESCENDING")) {
            Collections.reverse(colsArr);
        }
        return colsArr;
    }




    protected TableColumn getRelativeComparisonCol(String colStart, String colEnd) {
        String colname = "Percentage change\n" + Formatter.formatDate(colEnd) + " to \n" + Formatter.formatDate(colStart);
        TableColumn<Item, String> col = new TableColumn<Item, String>(colname);
        col.setMinWidth(150);
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
