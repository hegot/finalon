package finalonWindows.TemplateScene.templates;

import entities.Item;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.util.Callback;


class Columns {

    TreeTableColumn buttonCol() {
        TreeTableColumn<Item, Void> col = new TreeTableColumn<>("add");
        col.setMinWidth(50);

        Callback<TreeTableColumn<Item, Void>, TreeTableCell<Item, Void>> cellFactory = new Callback<TreeTableColumn<Item, Void>, TreeTableCell<Item, Void>>() {
            @Override
            public TreeTableCell<Item, Void> call(final TreeTableColumn<Item, Void> param) {
                final TreeTableCell<Item, Void> cell = new TreeTableCell<Item, Void>() {

                    private final Button btn = new Button("Action");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            System.out.println("selectedData: ");
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        col.setCellFactory(cellFactory);
        return col;
    }


    TreeTableColumn getNameCol() {
        TreeTableColumn<Item, String> col = new TreeTableColumn<Item, String>("Indicator");
        col.setMinWidth(300);
        col.setCellValueFactory(new TreeItemPropertyValueFactory<Item, String>("name"));
        col.setCellFactory(TextFieldTreeTableCell.<Item>forTreeTableColumn());
        setColumnEventHandlers(col, "name");
        return col;
    }


    TreeTableColumn getCodeCol() {
        TreeTableColumn<Item, String> col = new TreeTableColumn<Item, String>("Indicator Code");
        col.setMinWidth(300);
        col.setCellValueFactory(new TreeItemPropertyValueFactory<Item, String>("shortName"));
        col.setCellFactory(TextFieldTreeTableCell.<Item>forTreeTableColumn());
        setColumnEventHandlers(col, "shortName");
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


    private void updateCell(TreeTableColumn.CellEditEvent<Item, String> t, String param) {
        String value = t.getNewValue();
        TreeItem<Item> treeItem = t.getRowValue();
        Item item = treeItem.getValue();
        if (param.equals("name")) {
            item.setName(value);
        }
        if (param.equals("shortName")) {
            item.setShortName(value);
        }
    }


    private void setColumnEventHandlers(TreeTableColumn column, String param) {
        column.setOnEditCommit(
                new EventHandler<TreeTableColumn.CellEditEvent<Item, String>>() {
                    @Override
                    public void handle(TreeTableColumn.CellEditEvent<Item, String> t) {
                        updateCell(t, param);
                    }
                }
        );
        column.setOnEditCancel(
                new EventHandler<TreeTableColumn.CellEditEvent<Item, String>>() {
                    @Override
                    public void handle(TreeTableColumn.CellEditEvent<Item, String> t) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText("To save changes press 'Enter'");
                        alert.show();
                    }
                }
        );

    }

}
