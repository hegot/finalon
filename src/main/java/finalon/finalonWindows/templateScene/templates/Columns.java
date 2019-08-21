package finalon.finalonWindows.templateScene.templates;

import finalon.entities.Item;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
        col.setCellFactory(RemoveHandler.getRemoveFactory());
        return col;
    }

    static TableColumn getDragCol() {
        TableColumn<Item, Void> col = new TableColumn<>("");
        col.setPrefWidth(35);
        col.setCellFactory(DragHandler.getDragFactory());
        return col;
    }

    static TableColumn getNameCol() {
        TableColumn<Item, String> col = new TableColumn<Item, String>("Indicator");
        col.setMinWidth(450);
        col.setCellFactory(column -> new EditCell(new DefaultStringConverter()));
        col.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        col.setOnEditCommit(
                (TableColumn.CellEditEvent<Item, String> t) -> {
                    if (t.getTableView() != null) {
                        ((Item) t.getTableView().getItems()
                                .get(t.getTablePosition().getRow()))
                                .setName(t.getNewValue());
                    }
                });
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
