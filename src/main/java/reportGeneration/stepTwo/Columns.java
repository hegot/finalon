package reportGeneration.stepTwo;

import entities.Item;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;


class Columns {

    TableColumn getNameCol() {
        TableColumn<Item, String> col = new TableColumn<Item, String>("Indicator");
        col.setMinWidth(350);
        col.setEditable(false);
        col.setSortable(false);
        col.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        col.setCellFactory(column -> {
            return new TableCell<Item, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        setText(item);
                        Item index = getTableView().getItems().get(getTableRow().getIndex());
                        Integer level = index.getLevel();
                        if (level.equals(1) || level.equals(2) || level.equals(3)) {
                            setStyle("-fx-padding: 5 0 5 50; -fx-font-weight: bold;");
                        }
                    }
                }
            };
        });
        return col;
    }


    TableColumn getCodeCol() {
        TableColumn<Item, String> col = new TableColumn<Item, String>("Indicator Code");
        col.setMinWidth(100);
        col.setSortable(false);
        col.setEditable(false);
        col.setCellValueFactory(new PropertyValueFactory<Item, String>("shortName"));
        col.setCellFactory(TextFieldTableCell.<Item>forTableColumn());
        return col;
    }


    TableColumn getPeriodCol(String colname) {
        TableColumn<Item, String> col = new TableColumn<Item, String>(colname.replace("-", "-\n"));
        col.setMinWidth(100);
        col.setSortable(false);
        col.setCellFactory(column -> new ItemPeriodEditCell(colname));
        return col;
    }


}
