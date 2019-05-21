package interpreter.ReusableComponents;

import entities.Item;
import javafx.collections.ObservableMap;

public class GetVal {
    protected Double getVal(Item item, String period) {
        ObservableMap<String, Double> values = item.getValues();
        Double val = null;
        if (values.size() > 0) {
            val = values.get(period);
        }
        return val;
    }

    protected String partStr(Double val, Double total) {
        return format(part(val, total));
    }

    protected String format(Double input) {
        return String.format("%.2f", input) + '%';
    }

    protected Double part(Double val, Double total) {
        return (val / total) * 100;
    }
}
