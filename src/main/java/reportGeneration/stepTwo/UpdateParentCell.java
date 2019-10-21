package reportGeneration.stepTwo;

import entities.Item;
import finalonWindows.reusableComponents.NumField;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.TextFieldStorage;

public class UpdateParentCell {

    public static void run(Item child, String param) {
        ObservableList<Item> items = ItemsStorage.getItems();
        for (Item parent : items) {
            if (child.getParent() == parent.getId()) {
                Double childVals = getChildVals(parent.getId(), param);
                if (childVals != null) {
                    parent.getValues().put(param, childVals);
                    NumField field = TextFieldStorage.get(parent.getShortName() + param);
                    if (field != null) {
                        field.setText(childVals.toString());
                    }
                }
                if (parent.getParent() > 0) {
                    run(parent, param);
                }
            }
        }
    }

    private static Double getChildVals(int id, String param) {
        Double val = 0.0;
        ObservableList<Item> items = ItemsStorage.getItems();
        for (Item item : items) {
            if (item.getParent() == id) {
                ObservableMap<String, Double> values = item.getValues();
                Boolean isPositive = item.getIsPositive();
                if (values.size() > 0) {
                    Double value = values.get(param);
                    if (value != null) {
                        val += isPositive ? value : value * -1;
                    }
                }
            }
        }
        return val;
    }
}
