package reportGeneration.interpreter.ReusableComponents.helpers;

import entities.Item;
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
        Double val;
        Double part;
        for (Item item : items) {
            val = item.getVal(period);
            if (val != null) {
                part = Calc.part(val, totall);
                treeMap.put(part, item.getName());
            }
        }
        StringBuilder result = new StringBuilder("");
        for (Map.Entry<Double, String> entry : treeMap.entrySet()) {
            result.append(entry.getValue() + " ("
                    + Calc.format(entry.getKey()) +
                    "), ");
        }
        return start + result + end;
    }
}
