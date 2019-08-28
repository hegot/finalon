package finalon.reportGeneration.interpreter.ReusableComponents.helpers;

import finalon.entities.Item;
import javafx.collections.ObservableList;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class SrtuctureItemsLoop {
    public static String loop(
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
                Double part = Calc.part(val, totall);
                treeMap.put(part, item.getName());
            }
        }
        StringBuilder result = new StringBuilder("");
        for (Map.Entry<Double, String> entry : treeMap.entrySet()) {
            Double key = entry.getKey();
            result.append(entry.getValue() + " (" + Calc.format(key) + "), ");
        }
        return start + result + end;
    }
}
