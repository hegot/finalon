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
        Double childVals;
        for (Item parent : items) {
            if (child.getParent() == parent.getId()) {
                childVals = getChildVals(parent.getId(), param);
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
        Boolean isPositive;
        ObservableMap<String, Double> values;
        Double value;
        for (Item item : items) {
            if (item.getParent() == id) {
                values = item.getValues();
                isPositive = item.getIsPositive();
                if (values.size() > 0) {
                    value = values.get(param);
                    if (value != null) {
                        val += isPositive ? value : value * -1;
                    }
                }
            }
        }
        return val;
    }
}
