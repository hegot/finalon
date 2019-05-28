package reportGeneration.stepTwo;

import database.setting.DbSettingHandler;
import entities.Item;
import finalonWindows.reusableComponents.EditCell.EditCell;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;

import java.util.Arrays;


class Columns {

    private TextEditHandler textEditHandler;

    public Columns(
            TextEditHandler textEditHandler
    ) {
        this.textEditHandler = textEditHandler;
    }


    TreeTableColumn getNameCol() {
        TreeTableColumn<Item, String> col = new TreeTableColumn<Item, String>("Indicator");
        col.setMinWidth(350);
        col.setEditable(false);
        col.setCellValueFactory(new TreeItemPropertyValueFactory<Item, String>("name"));
        col.setCellFactory(TextFieldTreeTableCell.<Item>forTreeTableColumn());
        return col;
    }


    TreeTableColumn getCodeCol() {
        TreeTableColumn<Item, String> col = new TreeTableColumn<Item, String>("Indicator Code");
        col.setMinWidth(100);
        col.setEditable(false);
        col.setCellValueFactory(new TreeItemPropertyValueFactory<Item, String>("shortName"));
        col.setCellFactory(TextFieldTreeTableCell.<Item>forTreeTableColumn());
        return col;
    }


    TreeTableColumn getPeriodCol(String colname) {
        TreeTableColumn<Item, String> col = new TreeTableColumn<Item, String>(colname);
        col.setMinWidth(100);
        col.setCellFactory(column -> EditCell.createStringEditCell("integer"));
        textEditHandler.setColumnEventHandlers(col, colname);
        col.setCellValueFactory(cellData -> {
            TreeItem treeItem = cellData.getValue();
            if (treeItem != null) {
                Item item = (Item) treeItem.getValue();
                if (item != null) {
                    String[] arr = {"StatementOfFinancialPosition", "StatementOfComprehensiveIncome", "CashFlowStatement", "OtherData"};
                    if (Arrays.asList(arr).contains(item.getShortName())) {
                        return null;
                    }
                    if (item.getValues().size() > 0) {
                        Double dob = item.getValues().get(colname);
                        if (dob != null) {
                            String val = Double.toString(dob);
                            DbSettingHandler dbSettingHandler = new DbSettingHandler();
                            if (dbSettingHandler.getSetting("numberFormat").equals("comma")) {
                                val = val.replace('.', ',');
                            }
                            return new SimpleStringProperty(val);
                        }
                    }
                }
            }
            return null;
        });
        return col;
    }

}