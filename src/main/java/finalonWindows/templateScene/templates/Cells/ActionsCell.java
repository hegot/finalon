package finalonWindows.templateScene.templates.Cells;

import database.formula.DbFormulaHandler;
import entities.Item;
import finalonWindows.reusableComponents.ImageButton;
import finalonWindows.templateScene.templates.TemplateEditPage;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class ActionsCell {
    public static Callback<TableColumn<Item, Void>, TableCell<Item, Void>> getActionsFactory() {
        return new Callback<TableColumn<Item, Void>, TableCell<Item, Void>>() {
            @Override
            public TableCell<Item, Void> call(final TableColumn<Item, Void> param) {
                final TableCell<Item, Void> cell = new TableCell<Item, Void>() {

                    private ImageButton removeBtn() {
                        ImageButton btn = new ImageButton("image/remove.png", 16);
                        btn.getStyleClass().add("img-btn");
                        btn.setOnAction((ActionEvent event) -> {
                            TableView table = this.getTableView();
                            Item selectedItem = (Item) getTableRow().getItem();
                            if (selectedItem != null) {
                                String usages = DbFormulaHandler.usagesString(selectedItem.getShortName(), 0);
                                if (usages.length() > 0) {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Index deletion");
                                    alert.setHeaderText("Index that you want to delete is used in such formulas: ");
                                    alert.setContentText(usages
                                            + "\n\nPlease edit formula calculation and remove index" +
                                            "\nthere to be able to delete it.");
                                    alert.showAndWait();
                                } else {
                                    table.getItems().remove(selectedItem);
                                    TemplateEditPage.getItems().remove(selectedItem);
                                }
                            }
                        });
                        return btn;
                    }

                    private ImageButton addBtn(int level) {
                        ImageButton btn = new ImageButton("image/add-plus-button.png", 14);
                        btn.getStyleClass().add("img-btn");
                        btn.setOnAction((ActionEvent event) -> {
                            int index = getTableRow().getIndex();
                            Item item = (Item) this.getTableRow().getItem();
                            if (item != null) {
                                TableView table = this.getTableView();
                                Item itemNew = new Item(-1, "Set indicator name here", "CodeProperty", true, false, item.getId(), item.getParentSheet(), level, index);
                                table.getItems().add(index, itemNew);
                                TemplateEditPage.getItems().add(itemNew);
                            }
                        });
                        return btn;
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox hBox = new HBox(10);
                            TableRow row = this.getTableRow();
                            if (row != null) {
                                Item rowItem = (Item) row.getItem();
                                if (rowItem != null) {
                                    Integer level = rowItem.getLevel();
                                    if (level.equals(2) || level.equals(3)) {
                                        hBox.getChildren().addAll(addBtn(4));
                                    } else if (level.equals(4)) {
                                        hBox.getChildren().addAll(addBtn(5), removeBtn());
                                    } else if (level.equals(5)) {
                                        hBox.getChildren().addAll(removeBtn());
                                    }
                                }
                            }
                            setGraphic(hBox);
                        }
                    }
                };
                return cell;
            }
        };
    }
}
