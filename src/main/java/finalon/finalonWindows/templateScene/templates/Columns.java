package finalon.finalonWindows.templateScene.templates;

import finalon.entities.Item;
import finalon.finalonWindows.templateScene.templates.Cells.ActionsCell;
import finalon.finalonWindows.templateScene.templates.Cells.DragCell;
import finalon.finalonWindows.templateScene.templates.Cells.EditCell;
import finalon.reportGeneration.stepTwo.hooks.UpdateParentHook;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableMap;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DefaultStringConverter;

class Columns {

    static TableColumn buttonCol() {
        TableColumn<Item, Void> col = new TableColumn<>("");
        col.setMinWidth(50);
        col.setCellFactory(ActionsCell.getActionsFactory());
        return col;
    }

    public static TableColumn getDragCol() {
        TableColumn<Item, Void> col = new TableColumn<>("");
        col.setPrefWidth(35);
        col.setCellFactory(DragCell.getDragFactory());
        return col;
    }

    static TableColumn getNameCol() {
        TableColumn<Item, String> col = new TableColumn<Item, String>("Indicator");
        col.setMinWidth(450);
        col.setCellFactory(column -> new EditCell());
        col.setOnEditCommit(
                (TableColumn.CellEditEvent<Item, String> t) -> {
                    if (t != null && t.getTableView() != null) {
                        String value = t.getNewValue().replace(',', '.');
                        if (value != null) {
                            Item item = ((Item) t.getTableView().getItems()
                                    .get(t.getTablePosition().getRow()));
                            if (item != null) {
                                item.setName(value);
                                t.getTableView().refresh();
                            }
                        }
                    }
                });
        col.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        return col;
    }

    static TableColumn getCodeCol() {
        TableColumn<Item, String> col = new TableColumn<Item, String>("Indicator Code");
        col.setMinWidth(200);
        col.setEditable(false);
        col.setCellValueFactory(new PropertyValueFactory<Item, String>("shortName"));
        col.setCellFactory(TextFieldTableCell.<Item>forTableColumn());
        return col;
    }

    static TableColumn isPositiveCol() {
        TableColumn<Item, Boolean> col = new TableColumn<>("Positive");
        col.setMinWidth(90);
        col.setCellValueFactory((TableColumn.CellDataFeatures<Item, Boolean> param) -> {
            Item item = param.getValue();
            return item.isPositive();
        });
        col.setCellValueFactory((TableColumn.CellDataFeatures<Item, Boolean> param) -> {
            Item item = param.getValue();
            SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(item.getIsPositive());
            booleanProp.addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    item.setIsPositive(newValue);
                }
            });
            return booleanProp;
        });
        setCellFactory(col);
        return col;
    }

    static TableColumn finResultCol() {
        TableColumn<Item, Boolean> col = new TableColumn<>("Result");
        col.setMinWidth(80);
        col.setCellValueFactory((TableColumn.CellDataFeatures<Item, Boolean> param) -> {
            Item item = param.getValue();
            SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(item.getFinResult());
            booleanProp.addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    item.setFinResult(newValue);
                }
            });
            return booleanProp;
        });
        setCellFactory(col);
        return col;
    }

    private static void setCellFactory(TableColumn col) {
        col.setCellFactory(column -> {
            CheckBoxTableCell<Item, Boolean> checkBoxTableCell = new CheckBoxTableCell<>();
            checkBoxTableCell.setEditable(true);
            checkBoxTableCell.setAlignment(Pos.CENTER);
            return checkBoxTableCell;
        });
    }
}
