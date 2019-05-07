package interpreter.AssetsReport;

import database.setting.DbSettingHandler;
import entities.Item;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableMap;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;

import java.text.DecimalFormat;


class Columns {
    private ObservableMap<String, String> settings;
    private DbSettingHandler dbSettingHandler = new DbSettingHandler();

    public Columns(
            ObservableMap<String, String> settings
    ) {
        this.settings = settings;
    }


    TreeTableColumn getNameCol() {
        TreeTableColumn<Item, String> col = new TreeTableColumn<Item, String>("Indicator");
        col.setMinWidth(350);
        col.setCellValueFactory(new TreeItemPropertyValueFactory<Item, String>("name"));
        col.setCellFactory(TextFieldTreeTableCell.<Item>forTreeTableColumn());
        return col;
    }

    private String formatDate(String date) {
        return date.replace("\n", "");
    }

    private Double formatDouble(Double num) {
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(num));
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

    private String commaFormat(Double val) {
        String value = Double.toString(val);
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
                    Double absolute = colEndVAl - colStartVAl;
                    return new SimpleStringProperty(commaFormat(absolute));
                }
            }
            return null;
        });
        return col;
    }

    TreeTableColumn getRelativeComparisonCol(String colStart, String colEnd) {
        String colname = "Percentage change\n" + formatDate(colEnd) + " to \n" + formatDate(colStart);
        TreeTableColumn<Item, String> col = new TreeTableColumn<Item, String>(colname);
        col.setMinWidth(150);
        col.setCellValueFactory(cellData -> {
            ObservableMap<String, Double> values = getValues(cellData);
            if (values != null) {
                Double colStartVAl = values.get(colStart);
                Double colEndVAl = values.get(colEnd);
                if (colStartVAl != null && colEndVAl != null) {
                    Double relative = formatDouble((colEndVAl - colStartVAl) / colStartVAl) * 100;
                    return new SimpleStringProperty(commaFormat(relative) + "%");
                }
            }
            return null;
        });
        return col;
    }


    TreeTableColumn getPeriodCol(String colname) {
        TreeTableColumn<Item, String> col = new TreeTableColumn<Item, String>(colname);
        col.setMinWidth(100);
        col.setCellValueFactory(cellData -> {
            ObservableMap<String, Double> values = getValues(cellData);
            if (values != null) {
                Double dob = values.get(colname);
                if (dob != null) {
                    return new SimpleStringProperty(commaFormat(dob));
                }
            }
            return null;
        });
        return col;
    }

}
