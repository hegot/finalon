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
import reportGeneration.interpreter.ReusableComponents.interfaces.Diff;
import reportGeneration.interpreter.ReusableComponents.interfaces.JsCalcHelper;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;

import java.util.ArrayList;


public class IndexChangeTable extends ItemsTable implements JsCalcHelper, Diff {
    private DbSettingHandler dbSettingHandler = new DbSettingHandler();
    private int rootId;

    public IndexChangeTable(
            int rootId
    ) {
        super(ItemsStorage.getInstance().getItems());
        this.rootId = rootId;
    }

    public TableView<Item> get() {
        TableView<Item> table = getTable(rootId);
        table.getStyleClass().add("report-table");
        table.getColumns().addAll(getNameCol());
        ArrayList<String> arr = Periods.getInstance().getPeriodArr();
        for (String col : arr) {
            table.getColumns().add(getPeriodCol(col));
        }
        int count = arr.size() - 1;
        if (count > 0) {
            for (int j = 0; j < count; j++) {
                String colStart = arr.get(j);
                String colEnd = arr.get(j + 1);
                table.getColumns().add(getAbsoluteComparisonCol(colStart, colEnd));
            }
            for (int j = 0; j < count; j++) {
                String colStart = arr.get(j);
                String colEnd = arr.get(j + 1);
                table.getColumns().add(getRelativeComparisonCol(colStart, colEnd));
            }
        }

        return table;
    }

    protected TableColumn getNameCol() {
        TableColumn<Item, String> col = new TableColumn<Item, String>("Indicator");
        col.setMinWidth(350);
        col.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        col.setCellFactory(TextFieldTableCell.<Item>forTableColumn());
        return col;
    }

    protected ObservableMap<String, Double> getValues(TableColumn.CellDataFeatures<Item, String> cellData) {
        Item item = (Item) cellData.getValue();
        if (item != null) {
            if (item.getValues().size() > 0) {
                return item.getValues();
            }
        }
        return null;
    }

    protected String commaFormat(String value) {
        if (dbSettingHandler.getSetting(Setting.numberFormat).equals("comma")) {
            value = value.replace('.', ',');
        }
        return value;
    }


    TableColumn getAbsoluteComparisonCol(String colStart, String colEnd) {
        String colname = "Absolute Change\n" + formatDate(colEnd) + " to \n" + formatDate(colStart);
        TableColumn<Item, String> col = new TableColumn<Item, String>(colname);
        col.setMinWidth(150);
        col.setCellValueFactory(cellData -> {
            ObservableMap<String, Double> values = getValues(cellData);
            if (values != null) {
                return diff(
                        values.get(colStart),
                        values.get(colEnd)
                );
            }
            return null;
        });
        return col;
    }

    protected TableColumn getRelativeComparisonCol(String colStart, String colEnd) {
        String colname = "Percentage change\n" + formatDate(colEnd) + " to \n" + formatDate(colStart);
        TableColumn<Item, String> col = new TableColumn<Item, String>(colname);
        col.setMinWidth(150);
        col.setCellValueFactory(cellData -> {
            ObservableMap<String, Double> values = getValues(cellData);
            if (values != null) {
                Double colStartVAl = values.get(colStart);
                Double colEndVAl = values.get(colEnd);
                if (colStartVAl != null && colEndVAl != null) {
                    String relative = getRelativeChange(colStartVAl, colEndVAl);
                    return new SimpleStringProperty(commaFormat(relative) + "%");
                }
            }
            return null;
        });
        return col;
    }


    TableColumn getPeriodCol(String colname) {
        TableColumn<Item, String> col = new TableColumn<Item, String>(formatDate(colname));
        col.setMinWidth(100);
        col.setCellValueFactory(cellData -> {
            ObservableMap<String, Double> values = getValues(cellData);
            if (values != null) {
                Double dob = values.get(colname);
                if (dob != null) {
                    return new SimpleStringProperty(commaFormat(Double.toString(dob)));
                }
            }
            return null;
        });
        return col;
    }

}
