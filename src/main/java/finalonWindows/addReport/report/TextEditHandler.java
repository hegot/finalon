package finalonWindows.addReport.report;

import entities.Item;
import javafx.collections.ObservableMap;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;

public class TextEditHandler {
    private void updateCell(TreeTableColumn.CellEditEvent<Item, String> t, String param) {
        String value = t.getNewValue();
        TreeItem<Item> treeItem = t.getRowValue();
        if (treeItem != null && value != null) {
            Item item = treeItem.getValue();
            ObservableMap<String, Integer> values = item.getValues();
            for (String key : values.keySet()) {
                System.out.println(key + values.get(key));
            }
            int val = Integer.parseInt(value);
            values.put(param, val);
            item.setValues(values);
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
                        updateCell(t, param);
                    }
                }
        );

    }
}
