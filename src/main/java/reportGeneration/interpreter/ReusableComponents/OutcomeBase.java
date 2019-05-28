package reportGeneration.interpreter.ReusableComponents;

import javafx.collections.ObservableMap;
import reportGeneration.interpreter.ReusableComponents.interfaces.JsCalcHelper;
import reportGeneration.storage.Periods;

import java.util.ArrayList;

public class OutcomeBase implements JsCalcHelper {


    protected Double getLastVal(ObservableMap<String, Double> values) {
        ArrayList<String> arr = Periods.getInstance().getPeriodArr();
        String key = arr.get(arr.size() - 1);
        if (key != null) {
            return values.get(key);
        }
        return null;
    }

    protected Double getFirstVal(ObservableMap<String, Double> values) {
        ArrayList<String> arr = Periods.getInstance().getPeriodArr();
        if (arr.get(0) != null) {
            return values.get(arr.get(0));
        }
        return null;
    }


}
