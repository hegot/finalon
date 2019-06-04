package reportGeneration.interpreter.NormValsEvaluator;

import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import reportGeneration.interpreter.ReusableComponents.interfaces.OutcomeBase;
import reportGeneration.interpreter.ReusableComponents.interfaces.ParseDouble;
import reportGeneration.storage.Periods;

import java.util.ArrayList;


public class PeriodComparisonEvaluator implements ParseDouble, OutcomeBase {
    private ObservableList<Formula> childs;
    private ObservableMap<String, String> vals;
    private Double startVal;
    private Double endVal;
    private Double changePercent;
    private Double change;

    public PeriodComparisonEvaluator(Formula formula) {
        this.childs = formula.getChilds();
        ArrayList<String> periods = Periods.getInstance().getPeriodArr();
        this.vals = formula.getPeriods();
        if (periods.size() > 1 && vals.size() > 1) {
            this.startVal = getFirstValStr(vals);
            this.endVal = getLastValStr(vals);
        }
    }


    public String getResult() {
        String outcome = "";
        if (vals.size() > 1 && endVal != null && startVal != null) {
            this.change = endVal - startVal;
            this.changePercent = change * 100;
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
                String descr = formula.getDescription();
                descr = descr.replace("CHANGEPERCENT", toString(changePercent));
                descr = descr.replace("CHANGE", toString(change));
                return descr;
            }
        }
        return "";
    }

}
