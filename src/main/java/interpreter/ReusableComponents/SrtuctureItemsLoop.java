package interpreter.ReusableComponents;

import entities.Item;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public interface SrtuctureItemsLoop extends JsCalcHelper, GetVal, LabelWrap {
    default Label loopItems(
            ObservableList<Item> items,
            Double totall,
            String start,
            String end,
            String period
    ) {
        TreeMap<Double, String> treeMap = new TreeMap<>(Collections.reverseOrder());
        for (Item item : items) {
            Double val = getVal(item, period);
            if (val != null) {
                Double part = part(val, totall);
                treeMap.put(part, item.getName());
            }
        }
        StringBuilder result = new StringBuilder("");
        for (Map.Entry<Double, String> entry : treeMap.entrySet()) {
            Double key = entry.getKey();
            result.append(entry.getValue() + " (" + format(key) + " of total assets), ");
        }
        return labelWrap(start + result + end);
    }
}
