package interpreter.AssetsReport.Outcomes;

import interpreter.JsCalcHelper;
import javafx.collections.ObservableMap;
import java.util.AbstractMap;
import java.util.Map;

class OutcomeBase  implements JsCalcHelper {



    Map.Entry<String, Double> getLast(ObservableMap<String, Double> values) {
        Map.Entry<String, Double> last = null;
        for (Map.Entry<String, Double> entry : values.entrySet()) {
            last = entry;
        }
        return last;
    }

    Map.Entry<String, Double> getFirst(ObservableMap<String, Double> values) {
        return values.entrySet().iterator().next();
    }

    Map.Entry<String, Double> nullEntry(String key){
        return new AbstractMap.SimpleEntry<String, Double>(key, 0.0);
    }
}
