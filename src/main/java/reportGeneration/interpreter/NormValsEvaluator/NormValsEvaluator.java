package reportGeneration.interpreter.NormValsEvaluator;

import entities.Formula;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import reportGeneration.interpreter.ReusableComponents.interfaces.JsCalcHelper;

public class NormValsEvaluator extends ValsEvaluator implements JsCalcHelper {
    private Formula parent;
    private ObservableList<Formula> childs;
    private ObservableMap<String, String> vals;
    private String period;

    public NormValsEvaluator(Formula formula, String period) {
        this.parent = formula;
        this.childs = formula.getChilds();
        this.period = period;
        this.vals = parent.getPeriods();
    }

    public String getResult() {
        String outcome = "";
        if (vals.size() > 0) {
            Double val = getVal();
            outcome += strReplace(loopNormatives(val));
        }
        return outcome;
    }

    public String strReplace(String outcome) {
        outcome = outcome.replace("CURRENTPERIOD", formatDate(period));
        if (getVal() != null) {
            outcome = outcome.replace("CURRENTVALUE", Double.toString(getVal()));
        }
        return outcome;
    }


    private Double getVal() {
        String val = vals.get(period);
        if (val != null) {
            return parseDouble(val);
        }
        return null;
    }


    private String loopNormatives(Double value) {
        for (Formula normative : childs) {
            try {
                Double valueToCompare = normative.getValue().length() > 0 ? parseDouble(normative.getValue()) : null;
                Boolean match = matches(
                        normative.getShortName(),
                        normative.getCategory(),
                        value,
                        valueToCompare
                );
                if (match) {
                    return normative.getDescription();
                }
            } catch (NumberFormatException e) {

            }
        }
        return "";
    }


}
