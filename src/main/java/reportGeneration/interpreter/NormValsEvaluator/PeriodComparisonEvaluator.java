package reportGeneration.interpreter.NormValsEvaluator;

import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import reportGeneration.interpreter.ReusableComponents.interfaces.ParseDouble;
import reportGeneration.storage.Periods;

import java.util.ArrayList;


public class PeriodComparisonEvaluator implements ParseDouble {
    private ObservableList<Formula> childs;
    private ObservableMap<String, String> vals;
    private Double startVal;
    private Double endVal;

    public PeriodComparisonEvaluator(Formula formula) {
        this.childs = formula.getChilds();
        ArrayList<String> periods = Periods.getInstance().getPeriodArr();
        this.vals = formula.getPeriods();
        if (periods.size() > 1 && vals.size() > 1) {
            String firstKey = periods.get(0);
            this.startVal = getVal(firstKey);
            this.endVal = getVal(periods.get(periods.size() - 1));
        }
    }

    private Double getVal(String period) {
        String val = vals.get(period);
        if (val != null) {
            return parseDouble(val);
        }
        return null;
    }

    public String getResult() {
        String outcome = "";
        if (vals.size() > 1) {
            if (endVal > startVal) {
                outcome += getOutcome(EvaluationTypes.PERIOD_COMPARISON_INCREASE);
            }
            if (endVal < startVal) {
                outcome += getOutcome(EvaluationTypes.PERIOD_COMPARISON_DECREASE);
            }
        }
        return outcome;
    }

    private String getOutcome(EvaluationTypes type) {
        for (Formula formula : childs) {
            if (formula.getName().equals(type.toString())) {
                return formula.getDescription();
            }
        }
        return "";
    }

}
