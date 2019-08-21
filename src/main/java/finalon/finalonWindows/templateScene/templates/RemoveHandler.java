package finalon.finalonWindows.templateScene.templates;

import finalon.database.formula.DbFormulaHandler;
import finalon.entities.Item;
import finalon.finalonWindows.reusableComponents.ImageButton;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.util.List;
import java.util.Optional;

class RemoveHandler {
    static Callback<TableColumn<Item, Void>, TableCell<Item, Void>> getRemoveFactory() {
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
                                String code = selectedItem.getShortName();
                                List<String> usages = DbFormulaHandler.findUsage(code);
                                if (usages.size() > 0) {
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Index deletion");
                                    alert.setHeaderText("Index that you want to delete is used in such formulas: ");
                                    alert.setContentText(String.join(";\n", usages)
                                            + "\n\nAre you sure you want to delete it?");
                                    Optional<ButtonType> option = alert.showAndWait();
                                    try {
                                        if (option.get() == ButtonType.OK) {
                                            table.getItems().remove(selectedItem);
                                        }
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
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
                            hBox.getChildren().add(removeBtn());
                            setGraphic(hBox);
                        }
                    }
                };
                return cell;
            }
        };
    }
}
