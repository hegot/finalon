package interpreter.ReusableComponents;

import interpreter.JsCalcHelper;
import javafx.collections.ObservableMap;

import java.util.AbstractMap;
import java.util.Map;

public class OutcomeBase implements JsCalcHelper {


    public Map.Entry<String, Double> getLast(ObservableMap<String, Double> values) {
        Map.Entry<String, Double> last = null;
        for (Map.Entry<String, Double> entry : values.entrySet()) {
            last = entry;
        }
        return last;
    }

    public Double getLastVal(ObservableMap<String, Double> values){
        Map.Entry<String, Double> last = getLast(values);
        if(last != null){
            return last.getValue();
        }
        return null;
    }

    public Double getFirstVal(ObservableMap<String, Double> values){
        Map.Entry<String, Double> first = getFirst(values);
        if(first != null){
            return first.getValue();
        }
        return null;
    }

    public Map.Entry<String, Double> getFirst(ObservableMap<String, Double> values) {
        return values.entrySet().iterator().next();
    }

    public Map.Entry<String, Double> nullEntry(String key) {
        return new AbstractMap.SimpleEntry<String, Double>(key, 0.0);
    }
}
