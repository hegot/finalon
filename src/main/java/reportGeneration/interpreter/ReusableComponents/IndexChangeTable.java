package reportGeneration.interpreter.ReusableComponents;

import database.setting.DbSettingHandler;
import entities.Item;
import finalonWindows.reusableComponents.ItemsTable.ItemsTable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableMap;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import reportGeneration.ItemsStorage;
import reportGeneration.Periods;
import reportGeneration.interpreter.ReusableComponents.interfaces.JsCalcHelper;

import java.util.ArrayList;


public class IndexChangeTable extends ItemsTable implements JsCalcHelper {
    private DbSettingHandler dbSettingHandler = new DbSettingHandler();
    private int rootId;

    public IndexChangeTable(
            int rootId
    ) {
        super(ItemsStorage.getItems());
        this.rootId = rootId;
    }

    public TreeTableView<Item> get() {
        TreeTableView<Item> table = getTable(rootId);
        table.getStyleClass().add("assets-report");
        table.setPrefWidth(880);
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

    TreeTableColumn getNameCol() {
        TreeTableColumn<Item, String> col = new TreeTableColumn<Item, String>("Indicator");
        col.setMinWidth(350);
        col.setCellValueFactory(new TreeItemPropertyValueFactory<Item, String>("name"));
        col.setCellFactory(TextFieldTreeTableCell.<Item>forTreeTableColumn());
        return col;
    }

    private ObservableMap<String, Double> getValues(TreeTableColumn.CellDataFeatures<Item, String> cellData) {
        TreeItem treeItem = cellData.getValue();
        if (treeItem != null) {
            Item item = (Item) treeItem.getValue();
            if (item != null) {
                if (item.getValues().size() > 0) {
                    return item.getValues();
                }
            }
        }
        return null;
    }

    private String commaFormat(String value) {
        if (dbSettingHandler.getSetting("numberFormat").equals("comma")) {
            value = value.replace('.', ',');
        }
        return value;
    }


    TreeTableColumn getAbsoluteComparisonCol(String colStart, String colEnd) {
        String colname = "Absolute Change\n" + formatDate(colEnd) + " to \n" + formatDate(colStart);
        TreeTableColumn<Item, String> col = new TreeTableColumn<Item, String>(colname);
        col.setMinWidth(150);
        col.setCellValueFactory(cellData -> {
            ObservableMap<String, Double> values = getValues(cellData);
            if (values != null) {
                Double colStartVAl = values.get(colStart);
                Double colEndVAl = values.get(colEnd);
                if (colStartVAl != null && colEndVAl != null) {
                    String absolute = Double.toString(colEndVAl - colStartVAl);
                    return new SimpleStringProperty(commaFormat(absolute));
                }
            }
            return null;
        });
        return col;
    }

    private TreeTableColumn getRelativeComparisonCol(String colStart, String colEnd) {
        String colname = "Percentage change\n" + formatDate(colEnd) + " to \n" + formatDate(colStart);
        TreeTableColumn<Item, String> col = new TreeTableColumn<Item, String>(colname);
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


    TreeTableColumn getPeriodCol(String colname) {
        TreeTableColumn<Item, String> col = new TreeTableColumn<Item, String>(formatDate(colname));
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
