package finalon.reportGeneration.stepTwo.hooks;

import finalon.entities.Item;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class UpdateParentHook {

    private ObservableList<Item> items;

    public UpdateParentHook(ObservableList<Item> items) {
        this.items = items;
    }

    public void run(Item child, String param) {
        for (Item parent : items) {
            if (child.getParent() == parent.getId()) {
                Double childVals = getChildVals(parent.getId(), param);
                if (childVals != null) {
                    parent.getValues().put(param, childVals);
                    if (parent.getParent() > 0) {
                        run(parent, param);
                    }
                }
            }
        }
    }

    private Double getChildVals(int id, String param) {
        Double val = 0.0;
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
