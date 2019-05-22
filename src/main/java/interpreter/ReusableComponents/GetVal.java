package interpreter.ReusableComponents;

import entities.Item;
import javafx.collections.ObservableMap;

public interface GetVal {
    default Double getVal(Item item, String period) {
        ObservableMap<String, Double> values = item.getValues();
        Double val = null;
        if (values.size() > 0) {
            val = values.get(period);
        }
        return val;
    }

    default String partStr(Double val, Double total) {
        return format(part(val, total));
    }

    default String format(Double input) {
        return String.format("%.2f", input) + '%';
    }

    default Double part(Double val, Double total) {
        return (val / total) * 100;
    }
}
