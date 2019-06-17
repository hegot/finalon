package reportGeneration.interpreter.ReusableComponents.NormValsEvaluator;

import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.ObservableList;
import reportGeneration.interpreter.ReusableComponents.interfaces.ParseDouble;


public class PeriodComparisonEvaluator implements ParseDouble {
    private ObservableList<Formula> childs;
    private Double startVal;
    private Double endVal;
    private Double changePercent;
    private Double change;

    public PeriodComparisonEvaluator(Formula formula) {
        this.childs = formula.getChilds();
        this.startVal = formula.getFirstVal();
        this.endVal = formula.getLastVal();
    }


    public String getResult() {
        String outcome = "";
        if (endVal != null && startVal != null) {
            this.change = endVal - startVal;
            this.changePercent = change * 100;
            if (endVal > startVal) {
                outcome += getOutcome(EvaluationTypes.PERIOD_COMPARISON_INCREASE);
            }
            if (endVal < startVal) {
                outcome += getOutcome(EvaluationTypes.PERIOD_COMPARISON_DECREASE);
            }
            if (endVal.equals(startVal)) {
                outcome += getOutcome(EvaluationTypes.PERIOD_COMPARISON_NOCHANGE);
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
