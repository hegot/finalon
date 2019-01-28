package finalonWindows.TemplateScene.templates.eventHandlers;

import entities.Item;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;


public class RemoveHandler {


    public Callback<TreeTableColumn<Item, Void>, TreeTableCell<Item, Void>> getRemoveFactory() {
        return new Callback<TreeTableColumn<Item, Void>, TreeTableCell<Item, Void>>() {
            @Override
            public TreeTableCell<Item, Void> call(final TreeTableColumn<Item, Void> param) {
                final TreeTableCell<Item, Void> cell = new TreeTableCell<Item, Void>() {

                    private Button getBtn() {
                        Button btn = new Button("Del");
                        TreeItem treeItem = this.getTreeTableRow().getTreeItem();
                        if (treeItem != null) {
                            TreeItem parentTreeItem = treeItem.getParent();
                            btn.setOnAction((ActionEvent event) -> {
                                parentTreeItem.getChildren().remove(treeItem);
                            });
                        }
                        return btn;
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(getBtn());
                        }
                    }
                };
                return cell;
            }
        };
    }
}
