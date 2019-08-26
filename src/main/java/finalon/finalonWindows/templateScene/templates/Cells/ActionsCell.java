package finalon.finalonWindows.templateScene.templates.Cells;

import finalon.database.formula.DbFormulaHandler;
import finalon.entities.Item;
import finalon.finalonWindows.reusableComponents.ImageButton;
import finalon.finalonWindows.templateScene.templates.EditTemplate;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.util.List;
import java.util.Optional;

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
                                String usages = DbFormulaHandler.usagesString(selectedItem.getShortName());
                                if (usages.length() > 0) {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Index deletion");
                                    alert.setHeaderText("Index that you want to delete is used in such formulas: ");
                                    alert.setContentText(usages
                                            + "\n\nAre you sure you want to delete it?");
                                    Optional<ButtonType> option = alert.showAndWait();
                                    try {
                                        if (option.get() == ButtonType.OK) {
                                            table.getItems().remove(selectedItem);
                                            EditTemplate.getItems().remove(selectedItem);
                                        }
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                } else {
                                    table.getItems().remove(selectedItem);
                                    EditTemplate.getItems().remove(selectedItem);
                                }
                            }
                        });
                        return btn;
                    }

                    private ImageButton addBtn() {
                        ImageButton btn = new ImageButton("image/add-plus-button.png", 16);
                        btn.getStyleClass().add("img-btn");
                        btn.setOnAction((ActionEvent event) -> {
                            int index = getTableRow().getIndex();
                            Item item = (Item) this.getTableRow().getItem();
                            if (item != null) {
                                TableView table = this.getTableView();
                                Item itemNew = new Item(-1, "Set value here", "Set value here", true, false, item.getId(), item.getParentSheet(), 4, index);
                                table.getItems().add(index, itemNew);
                                EditTemplate.getItems().add(itemNew);
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
                                        hBox.getChildren().addAll(addBtn());
                                    } else if (level.equals(4)) {
                                        hBox.getChildren().add(removeBtn());
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
