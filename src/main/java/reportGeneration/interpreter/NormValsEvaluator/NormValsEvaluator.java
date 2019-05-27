package reportGeneration.interpreter.NormValsEvaluator;

import entities.Formula;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class NormValsEvaluator {
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
            outcome += loopNormatives(val);
        }
        return outcome;
    }


    private Double getVal() {
        String val = vals.get(period);
        if (val != null) {
            return Double.parseDouble(val);
        }
        return null;
    }


    private String loopNormatives(Double value) {
        for (Formula normative : childs) {
            Double valueToCompare = normative.getValue().length() > 0 ? Double.parseDouble(normative.getValue()) : null;
            Boolean match = matches(
                    normative.getShortName(),
                    normative.getCategory(),
                    value,
                    valueToCompare
            );
            if (match) {
                return normative.getDescription();
            }
        }
        return "";
    }

    private Boolean matches(String firstComparator, String secondComparator, Double value, Double valueToCompare) {
        Boolean result = false;
        if (value != null && valueToCompare != null) {
            if (firstComparator.length() > 0) {
                result = evaluateOperator(firstComparator, value, valueToCompare);
                if (!result) return false;
            }
            if (secondComparator.length() > 0) {
                result = evaluateOperator(secondComparator, value, valueToCompare);
            }
        }
        return result;
    }


    private Boolean evaluateOperator(String operator, Double a, Double b) {
        Boolean result = false;
        switch (operator) {
            case (">"):
                result = a > b;
                break;
            case ("<"):
                result = a < b;
                break;
            case ("="):
                result = a.equals(b);
                break;
            case (">="):
                result = a >= b;
                break;
            case ("<="):
                result = a <= b;
                break;
        }
        return result;
    }

}
