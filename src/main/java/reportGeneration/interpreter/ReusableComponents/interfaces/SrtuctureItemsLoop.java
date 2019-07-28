package reportGeneration.interpreter.ReusableComponents.interfaces;

import entities.Item;
import globalReusables.LabelWrap;
import javafx.collections.ObservableList;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public interface SrtuctureItemsLoop extends JsCalcHelper, GetVal, LabelWrap {
    default String loopItems(
            ObservableList<Item> items,
            Double totall,
            String start,
            String end,
            String period
    ) {
        TreeMap<Double, String> treeMap = new TreeMap<>(Collections.reverseOrder());
        for (Item item : items) {
            Double val = item.getVal(period);
            if (val != null) {
                Double part = part(val, totall);
                treeMap.put(part, item.getName());
            }
        }
        StringBuilder result = new StringBuilder("");
        for (Map.Entry<Double, String> entry : treeMap.entrySet()) {
            Double key = entry.getKey();
            result.append(entry.getValue() + " (" + format(key) + "), ");
        }
        return start + result + end;
    }
}
