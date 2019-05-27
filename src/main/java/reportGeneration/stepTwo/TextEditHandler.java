package reportGeneration.stepTwo;

import entities.Item;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import reportGeneration.storage.ItemsStorage;

class TextEditHandler {

    private ObservableList<Item> items;

    TextEditHandler() {
        this.items = ItemsStorage.getItems();
    }

    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void updateCell(TreeTableColumn.CellEditEvent<Item, String> t, String param) {
        String value = t.getNewValue().replace(',', '.');
        if (value != null) {
            TreeItem<Item> treeItem = t.getRowValue();
            if (treeItem != null) {
                Item item = treeItem.getValue();
                ObservableMap<String, Double> values = item.getValues();
                updateItem(item, values, value, param);
                updateParent(item, param);
            }
        }
    }

    private void updateItem(Item item, ObservableMap<String, Double> values, String value, String param) {
        if (value.length() > 0 && isNumeric(value)) {
            values.put(param, Double.parseDouble(value));
        } else {
            values.remove(param);
        }
        item.setValues(values);
    }

    private void updateParent(Item child, String param) {
        for (Item parent : items) {
            if (child.getParent() == parent.getId()) {
                ObservableMap<String, Double> values = parent.getValues();
                values.put(param, getChildVals(parent.getId(), param));
                updateParent(parent, param);
            }
        }
    }

    private Double getChildVals(int id, String param) {
        Double val = 0.0;
        for (Item item : items) {
            if (item.getParent() == id) {
                ObservableMap<String, Double> values = item.getValues();
                if (values.size() > 0) {
                    Double value = values.get(param);
                    if (value != null) {
                        val += value;
                    }
                }
            }
        }
        return val;
    }


    void setColumnEventHandlers(TreeTableColumn column, String param) {
        column.setOnEditCommit(
                new EventHandler<TreeTableColumn.CellEditEvent<Item, String>>() {
                    @Override
                    public void handle(TreeTableColumn.CellEditEvent<Item, String> t) {
                        updateCell(t, param);
                    }
                }
        );
    }
}
