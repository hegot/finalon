package finalonWindows.templateScene.templates;

import entities.Item;
import finalonWindows.reusableComponents.ImageButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class RemoveHandler {


    public Callback<TableColumn<Item, Void>, TableCell<Item, Void>> getRemoveFactory() {
        return new Callback<TableColumn<Item, Void>, TableCell<Item, Void>>() {
            @Override
            public TableCell<Item, Void> call(final TableColumn<Item, Void> param) {
                final TableCell<Item, Void> cell = new TableCell<Item, Void>() {

                    private ImageButton removeBtn() {
                        ImageButton btn = new ImageButton("image/remove.png", 16);
                        btn.getStyleClass().add("img-btn");
                        TableView table = this.getTableView();
                        Item selectedItem = (Item) table.getSelectionModel().getSelectedItem();
                        table.getItems().remove(selectedItem);
                        return btn;
                    }

                    /*private ImageButton addBtn() {
                        ImageButton btn = new ImageButton("image/add-plus-button.png", 16);
                        btn.getStyleClass().add("img-btn");
                        Item item = (Item) this.getTableRow().getItem();
                        if (item != null) {
                            btn.setOnAction((ActionEvent event) -> {
                                Item itemNew = new Item(-1, "Set value here", "Set value here", true, false, item.getId(), -1);
                                TreeItem treeItemNew = new TreeItem<Item>(itemNew);
                                treeItemNew.setExpanded(true);
                                item.getChildren().add(treeItemNew);
                            });
                        }
                        return btn;
                    }*/

                    private HBox container() {
                        HBox hBox = new HBox(10);
                        hBox.getChildren().addAll(
                                //addBtn(),
                                removeBtn());
                        return hBox;
                    }


                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(container());
                        }
                    }
                };
                return cell;
            }
        };
    }
}
