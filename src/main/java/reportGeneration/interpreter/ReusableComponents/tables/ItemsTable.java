package reportGeneration.interpreter.ReusableComponents.tables;

import database.setting.DbSettingHandler;
import entities.Item;
import globalReusables.ItemsGetter;
import globalReusables.Setting;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import reportGeneration.interpreter.ReusableComponents.helpers.Calc;
import reportGeneration.interpreter.ReusableComponents.helpers.Formatter;
import reportGeneration.storage.Periods;

import java.util.ArrayList;
import java.util.Collections;

public class ItemsTable {
    private ObservableList<Item> items;

    public ItemsTable(ObservableList<Item> items) {
        this.items = items;
    }

    public static TableColumn<Item, String> getPeriodCol(String colname) {
        String[] split = colname.split("-");
        TableColumn<Item, String> col = new TableColumn<Item, String>(split[1]);
        col.getStyleClass().add("period-col");
        col.setMinWidth(100);
        col.setSortable(false);
        col.setCellValueFactory(cellData -> {
            ObservableMap<String, Double> values = getValues(cellData);
            if (values != null) {
                Double dob = values.get(colname);
                if (dob != null) {
                    return new SimpleStringProperty(Formatter.finalNumberFormat(dob));
                }
            }
            return null;
        });
        return col;
    }

    public static TableColumn getNameCol() {
        TableColumn<Item, String> col = new TableColumn<Item, String>("Indicator");
        col.setId("name-column");
        col.setMinWidth(350);
        col.setSortable(false);
        col.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        col.setCellFactory(TextFieldTableCell.<Item>forTableColumn());
        return col;
    }

    public static TableColumn getAbsoluteComparisonCol(String colStart, String colEnd) {
        String colname = "Absolute Change\n" + Formatter.formatDate(colEnd) + " to \n" + Formatter.formatDate(colStart);
        TableColumn<Item, String> col = new TableColumn<Item, String>(colname);
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

    public static ArrayList<TableColumn> getPeriodCols() {
        ArrayList<TableColumn> colsArr = new ArrayList<>();
        ArrayList<String> arr = Periods.getPeriodArr();
        for (String col : arr) {
            colsArr.add(getPeriodCol(col));
        }
        String order = DbSettingHandler.getSetting(Setting.yearOrder);
        if (order.equals("DESCENDING")) {
            Collections.reverse(colsArr);
        }
        return colsArr;
    }

    protected static ObservableMap<String, Double> getValues(TableColumn.CellDataFeatures<Item, String> cellData) {
        Item item = (Item) cellData.getValue();
        if (item != null) {
            if (item.getValues().size() > 0) {
                return item.getValues();
            }
        }
        return null;
    }

    protected TableView<Item> getTable(int Id) {
        TableView<Item> table = new TableView<>();
        ItemsGetter itemsGetter = new ItemsGetter(Id, this.items);
        table.getItems().addAll(itemsGetter.getItems());
        return table;
    }
}