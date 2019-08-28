package finalon.reportGeneration.interpreter.ReusableComponents.NormValsEvaluator;

import finalon.defaultData.EvaluationTypes;
import finalon.entities.Formula;
import finalon.reportGeneration.interpreter.ReusableComponents.interfaces.CommaFormat;
import javafx.collections.ObservableList;


public class PeriodComparisonEvaluator {
    private ObservableList<Formula> childs;
    private Double startVal;
    private Double endVal;
    private Double changePercent;
    private Double change;
    private Formula formula;

    public PeriodComparisonEvaluator(Formula formula) {
        this.childs = formula.getChilds();
        this.startVal = formula.getFirstVal();
        this.endVal = formula.getLastVal();
        this.formula = formula;
    }


    public String getResult() {
        String outcome = "";
        if (endVal != null && startVal != null) {
            this.change = endVal - startVal;
            this.changePercent = change / startVal * 100;
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
        return InTimeReplacer.getVal(outcome, formula);
    }

    private String getOutcome(EvaluationTypes type) {
        for (Formula formula : childs) {
            if (formula.getName().equals(type.toString())) {
                String descr = formula.getDescription();
                if (startVal != 0) {
                    descr = descr.replace("CHANGEPERCENT", CommaFormat.format(changePercent));
                } else {
                    descr = descr.replace("CHANGEPERCENT", CommaFormat.format(change));
                }
                descr = descr.replace("CHANGE", CommaFormat.format(change));
                return descr;
            }
        }
        return "";
    }

}
