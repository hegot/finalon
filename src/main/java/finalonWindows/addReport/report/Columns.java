package finalonWindows.addReport.report;

import database.setting.DbSettingHandler;
import entities.Item;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableMap;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;


class Columns {

    private TextEditHandler textEditHandler;
    private ObservableMap<String, String> settings;
    public Columns(
            TextEditHandler textEditHandler,
            ObservableMap<String, String> settings
    ) {
        this.textEditHandler = textEditHandler;
        this.settings = settings;
    }


    TreeTableColumn getNameCol() {
        TreeTableColumn<Item, String> col = new TreeTableColumn<Item, String>("Indicator");
        col.setMinWidth(350);
        col.setCellValueFactory(new TreeItemPropertyValueFactory<Item, String>("name"));
        col.setCellFactory(TextFieldTreeTableCell.<Item>forTreeTableColumn());
        return col;
    }


    TreeTableColumn getCodeCol() {
        TreeTableColumn<Item, String> col = new TreeTableColumn<Item, String>("Indicator Code");
        col.setMinWidth(100);
        col.setCellValueFactory(new TreeItemPropertyValueFactory<Item, String>("shortName"));
        col.setCellFactory(TextFieldTreeTableCell.<Item>forTreeTableColumn());
        return col;
    }


    TreeTableColumn getPeriodCol(String colname) {
        TreeTableColumn<Item, String> col = new TreeTableColumn<Item, String>(colname);
        col.setMinWidth(100);
        col.setCellFactory(column -> EditCell.createStringEditCell());
        textEditHandler.setColumnEventHandlers(col, colname);
        col.setCellValueFactory(cellData -> {
            Item item = (Item) cellData.getValue().getValue();
            if (item != null && item.getValues().size() > 0) {
                String  val = Double.toString(item.getValues().get(colname));
                DbSettingHandler dbSettingHandler = new DbSettingHandler();
                if(dbSettingHandler.getSetting("numberFormat").equals("comma")){
                    val = val.replace('.', ',');
                }
                return new SimpleStringProperty(val);
            }
            return null;
        });
        return col;
    }

}
