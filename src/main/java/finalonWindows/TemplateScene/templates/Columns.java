package finalonWindows.TemplateScene.templates;

import entities.Item;
import finalonWindows.TemplateScene.templates.eventHandlers.RemoveHandler;
import finalonWindows.TemplateScene.templates.eventHandlers.TextEditHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.CheckBoxTreeTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;


class Columns {

    private TextEditHandler textEditHandler;
    private RemoveHandler removeHandler;

    public Columns(
            TextEditHandler textEditHandler,
            RemoveHandler removeHandler
    ) {
        this.textEditHandler = textEditHandler;
        this.removeHandler = removeHandler;
    }

    TreeTableColumn buttonCol() {
        TreeTableColumn<Item, Void> col = new TreeTableColumn<>("delete");
        col.setMinWidth(45);
        col.setCellFactory(removeHandler.getRemoveFactory());
        return col;
    }


    TreeTableColumn getNameCol() {
        TreeTableColumn<Item, String> col = new TreeTableColumn<Item, String>("Indicator");
        col.setMinWidth(400);
        col.setCellValueFactory(new TreeItemPropertyValueFactory<Item, String>("name"));
        col.setCellFactory(TextFieldTreeTableCell.<Item>forTreeTableColumn());
        textEditHandler.setColumnEventHandlers(col, "name");
        return col;
    }


    TreeTableColumn getCodeCol() {
        TreeTableColumn<Item, String> col = new TreeTableColumn<Item, String>("Indicator Code");
        col.setMinWidth(300);
        col.setCellValueFactory(new TreeItemPropertyValueFactory<Item, String>("shortName"));
        col.setCellFactory(TextFieldTreeTableCell.<Item>forTreeTableColumn());
        textEditHandler.setColumnEventHandlers(col, "shortName");
        return col;
    }


    TreeTableColumn isPositiveCol() {
        TreeTableColumn<Item, Boolean> col = new TreeTableColumn<>("Positive");
        col.setMinWidth(50);

        col.setCellValueFactory((TreeTableColumn.CellDataFeatures<Item, Boolean> param) -> {
            Item item = param.getValue().getValue();
            return item.isPositive();
        });

        col.setCellFactory(column -> {
            CheckBoxTreeTableCell<Item, Boolean> checkBoxTreeTableCell = new CheckBoxTreeTableCell<>();
            checkBoxTreeTableCell.setEditable(true);
            checkBoxTreeTableCell.setAlignment(Pos.CENTER);
            return checkBoxTreeTableCell;
        });

        return col;
    }


}
