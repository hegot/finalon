package finalonWindows.formulaScene;

import entities.Formula;
import finalonWindows.formulaScene.eventHandlers.EditHandler;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;


class Columns {


    TreeTableColumn getNameCol() {
        TreeTableColumn<Formula, String> col = new TreeTableColumn<Formula, String>("Indicator");
        col.setMinWidth(350);
        col.setCellValueFactory(new TreeItemPropertyValueFactory<Formula, String>("name"));
        col.setCellFactory(TextFieldTreeTableCell.<Formula>forTreeTableColumn());
        return col;
    }


    TreeTableColumn getCodeCol() {
        TreeTableColumn<Formula, String> col = new TreeTableColumn<Formula, String>("Code");
        col.setMinWidth(200);
        col.setCellValueFactory(new TreeItemPropertyValueFactory<Formula, String>("shortName"));
        col.setCellFactory(TextFieldTreeTableCell.<Formula>forTreeTableColumn());
        return col;
    }

    TreeTableColumn getValueCol() {
        TreeTableColumn<Formula, String> col = new TreeTableColumn<Formula, String>("Value");
        col.setMinWidth(280);
        col.setCellValueFactory(new TreeItemPropertyValueFactory<Formula, String>("value"));
        col.setCellFactory(TextFieldTreeTableCell.<Formula>forTreeTableColumn());
        return col;
    }

    EditHandler editHandler = new EditHandler();
    TreeTableColumn buttonCol() {
        TreeTableColumn<Formula, Void> col = new TreeTableColumn<>("");
        col.setMinWidth(50);
        col.setCellFactory(editHandler.getBtnFactory());
        return col;
    }
}
