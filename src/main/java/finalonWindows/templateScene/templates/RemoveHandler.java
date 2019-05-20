package finalonWindows.templateScene.templates;

import entities.Item;
import finalonWindows.reusableComponents.ImageButton;
import javafx.event.ActionEvent;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class RemoveHandler {


    public Callback<TreeTableColumn<Item, Void>, TreeTableCell<Item, Void>> getRemoveFactory() {
        return new Callback<TreeTableColumn<Item, Void>, TreeTableCell<Item, Void>>() {
            @Override
            public TreeTableCell<Item, Void> call(final TreeTableColumn<Item, Void> param) {
                final TreeTableCell<Item, Void> cell = new TreeTableCell<Item, Void>() {

                    private ImageButton removeBtn() {
                        ImageButton btn = new ImageButton("image/remove.png", 16);
                        btn.getStyleClass().add("img-btn");
                        TreeItem treeItem = this.getTreeTableRow().getTreeItem();
                        if (treeItem != null) {
                            TreeItem parentTreeItem = treeItem.getParent();
                            if (parentTreeItem != null) {
                                btn.setOnAction((ActionEvent event) -> {
                                    parentTreeItem.getChildren().remove(treeItem);
                                });
                            }
                        }
                        return btn;
                    }

                    private ImageButton addBtn() {
                        ImageButton btn = new ImageButton("image/add-plus-button.png", 16);
                        btn.getStyleClass().add("img-btn");
                        TreeItem treeItem = this.getTreeTableRow().getTreeItem();
                        if (treeItem != null) {
                            btn.setOnAction((ActionEvent event) -> {
                                Item node = (Item) treeItem.getValue();
                                Item itemNew = new Item(-1, "Set value here", "Set value here", true, false, node.getId(), -1);
                                TreeItem treeItemNew = new TreeItem<Item>(itemNew);
                                treeItemNew.setExpanded(true);
                                treeItem.getChildren().add(treeItemNew);
                            });
                        }
                        return btn;
                    }

                    private HBox container() {
                        HBox hBox = new HBox(10);
                        hBox.getChildren().addAll(addBtn(), removeBtn());
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
