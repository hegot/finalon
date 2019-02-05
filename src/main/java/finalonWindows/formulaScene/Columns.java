package finalonWindows.formulaScene;

import entities.Formula;
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
        col.setMinWidth(200);
        col.setCellValueFactory(new TreeItemPropertyValueFactory<Formula, String>("value"));
        col.setCellFactory(TextFieldTreeTableCell.<Formula>forTreeTableColumn());
        return col;
    }

    TreeTableColumn getUnitCol() {
        TreeTableColumn<Formula, String> col = new TreeTableColumn<Formula, String>("Unit");
        col.setMinWidth(200);
        col.setCellValueFactory(new TreeItemPropertyValueFactory<Formula, String>("unit"));
        col.setCellFactory(TextFieldTreeTableCell.<Formula>forTreeTableColumn());
        return col;
    }
}
