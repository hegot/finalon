package finalonWindows.addReport.report;

import entities.Item;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;


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
        col.setCellFactory(TextFieldTreeTableCell.<Item>forTreeTableColumn());
        textEditHandler.setColumnEventHandlers(col, colname);


        return col;
    }


}
