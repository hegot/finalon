package finalonWindows.templateScene.templates.eventHandlers;

import entities.Item;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;

public class TextEditHandler {
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


    public void setColumnEventHandlers(TreeTableColumn column, String param) {
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
