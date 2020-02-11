package reportGeneration.interpreter.ReusableComponents.helpers;

import entities.Item;
import globalReusables.MapUtil;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.Map;

public class SrtuctureItemsLoop {
    public static String loop(
            ObservableList<Item> items,
            Double totall,
            String start,
            String end,
            String period
    ) {
        Map<String, Double> map = new HashMap<>();
        Double val;
        Double part;
        for (Item item : items) {
            val = item.getVal(period);
            if (val != null && val > 0) {
                part = Calc.part(val, totall);
                map.put(item.getName(), part);
            }
        }
        Map<String, Double> sorted = MapUtil.sortByValue(map);
        StringBuilder result = new StringBuilder("");
        for (Map.Entry<String, Double> entry : sorted.entrySet()) {
            result.append(entry.getKey() + " ("
                    + Calc.format(entry.getValue()) +
                    "), ");
        }
        return start + result + end;
    }
}
