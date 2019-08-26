package finalon.finalonWindows.templateScene.templates;

import finalon.database.formula.DbFormulaHandler;
import finalon.entities.Item;
import finalon.finalonWindows.templateScene.templates.Cells.ActionsCell;
import finalon.finalonWindows.templateScene.templates.Cells.DragCell;
import finalon.finalonWindows.templateScene.templates.Cells.EditCell;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
                        String value = t.getNewValue();
                        if (value != null) {
                            Item item = ((Item) t.getTableView().getItems()
                                    .get(t.getTablePosition().getRow()));
                            if (item != null) {
                                item.setName(value);
                                item.setUpdated(true);
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
        col.setCellFactory(column -> new EditCell());
        col.setOnEditCommit(
                (TableColumn.CellEditEvent<Item, String> t) -> {
                    if (t != null && t.getTableView() != null) {
                        String value = t.getNewValue();
                        if (value != null) {
                            Item item = ((Item) t.getTableView().getItems()
                                    .get(t.getTablePosition().getRow()));
                            if (item != null) {
                                String usages = DbFormulaHandler.usagesString(item.getShortName());
                                if (usages.length() > 0) {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Index code change");
                                    alert.setHeaderText("Index code that you want to change is used in such formulas: ");
                                    alert.setContentText(usages
                                            + "\n\n Formulas will get automatically updated with new code value. " +
                                            "Are you sure you want to change it?");
                                    Optional<ButtonType> option = alert.showAndWait();
                                    try {
                                        if (option.get() == ButtonType.OK) {
                                            item.setShortName(value);
                                            item.setUpdated(true);
                                            item.setShortNameUpdated(true);
                                            t.getTableView().refresh();
                                        }
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                            }
                        }
                    }
                });
        col.setCellValueFactory(new PropertyValueFactory<Item, String>("shortName"));
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
                    item.setUpdated(true);
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
                    item.setUpdated(true);
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
