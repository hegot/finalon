package reportGeneration.interpreter.ReusableComponents.interfaces;

import javafx.collections.ObservableMap;
import reportGeneration.storage.Periods;

import java.util.ArrayList;

public interface OutcomeBase extends ParseDouble {

    default Double getLastVal(ObservableMap<String, Double> values) {
        ArrayList<String> arr = Periods.getInstance().getPeriodArr();
        String key = arr.get(arr.size() - 1);
        if (key != null) {
            return values.get(key);
        }
        return null;
    }

    default Double getFirstVal(ObservableMap<String, Double> values) {
        ArrayList<String> arr = Periods.getInstance().getPeriodArr();
        if (arr.get(0) != null) {
            return values.get(arr.get(0));
        }
        return null;
    }

    default Double getLastValStr(ObservableMap<String, String> values) {
        ArrayList<String> arr = Periods.getInstance().getPeriodArr();
        String key = arr.get(arr.size() - 1);
        if (key != null) {
            return parseDouble(values.get(key));
        }
        return null;
    }

    default Double getFirstValStr(ObservableMap<String, String> values) {
        ArrayList<String> arr = Periods.getInstance().getPeriodArr();
        String key = arr.get(0);
        if (key != null ) {
            if(values.get(key) == null){
                key = arr.get(1);
                if (key != null) {
                    return parseDouble(values.get(key));
                }
            }else{
                return parseDouble(values.get(key));
            }
        }
        return null;
    }
}
