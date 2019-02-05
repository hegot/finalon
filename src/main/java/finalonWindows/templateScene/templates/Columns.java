package finalonWindows.templateScene.templates;

import entities.Item;
import finalonWindows.templateScene.templates.eventHandlers.RemoveHandler;
import finalonWindows.templateScene.templates.eventHandlers.TextEditHandler;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
        TreeTableColumn<Item, Void> col = new TreeTableColumn<>("");
        col.setMinWidth(50);
        col.setCellFactory(removeHandler.getRemoveFactory());
        return col;
    }


    TreeTableColumn getNameCol() {
        TreeTableColumn<Item, String> col = new TreeTableColumn<Item, String>("Indicator");
        col.setMinWidth(450);
        col.setCellValueFactory(new TreeItemPropertyValueFactory<Item, String>("name"));
        col.setCellFactory(TextFieldTreeTableCell.<Item>forTreeTableColumn());
        textEditHandler.setColumnEventHandlers(col, "name");
        return col;
    }


    TreeTableColumn getCodeCol() {
        TreeTableColumn<Item, String> col = new TreeTableColumn<Item, String>("Indicator Code");
        col.setMinWidth(200);
        col.setCellValueFactory(new TreeItemPropertyValueFactory<Item, String>("shortName"));
        col.setCellFactory(TextFieldTreeTableCell.<Item>forTreeTableColumn());
        textEditHandler.setColumnEventHandlers(col, "shortName");
        return col;
    }


    TreeTableColumn isPositiveCol() {
        TreeTableColumn<Item, Boolean> col = new TreeTableColumn<>("Positive");
        col.setMinWidth(90);

        col.setCellValueFactory((TreeTableColumn.CellDataFeatures<Item, Boolean> param) -> {
            Item item = param.getValue().getValue();
            return item.isPositive();
        });
        col.setCellValueFactory((TreeTableColumn.CellDataFeatures<Item, Boolean> param) -> {
            Item item = param.getValue().getValue();
            SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(item.getIsPositive());
            booleanProp.addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
                                    Boolean newValue) {
                    item.setIsPositive(newValue);
                }
            });
            return booleanProp;
        });

        setCellFactory(col);

        return col;
    }

    TreeTableColumn finResultCol() {
        TreeTableColumn<Item, Boolean> col = new TreeTableColumn<>("Result");
        col.setMinWidth(80);

        col.setCellValueFactory((TreeTableColumn.CellDataFeatures<Item, Boolean> param) -> {
            Item item = param.getValue().getValue();
            SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(item.getFinResult());
            booleanProp.addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
                                    Boolean newValue) {
                    item.setFinResult(newValue);
                }
            });
            return booleanProp;
        });

        setCellFactory(col);
        return col;
    }

    private void setCellFactory(TreeTableColumn col) {
        col.setCellFactory(column -> {
            CheckBoxTreeTableCell<Item, Boolean> checkBoxTreeTableCell = new CheckBoxTreeTableCell<>();
            checkBoxTreeTableCell.setEditable(true);
            checkBoxTreeTableCell.setAlignment(Pos.CENTER);
            return checkBoxTreeTableCell;
        });
    }


}
