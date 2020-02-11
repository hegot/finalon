package reportGeneration.interpreter.ReusableComponents.NormValsEvaluator;

import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.ObservableList;
import reportGeneration.interpreter.ReusableComponents.helpers.Formatter;

public class NormValsEvaluator extends ValsEvaluator {
    private Formula formula;
    private ObservableList<Formula> childs;
    private String period;
    private EvaluationTypes type;

    public NormValsEvaluator(Formula formula, String period, EvaluationTypes type) {
        this.formula = formula;
        this.childs = formula.getChilds();
        this.period = period;
        this.type = type;
    }

    public String getResult() {
        String outcome = "";
        if (formula.getPeriods().size() > 0) {
            outcome += strReplace(loopNormatives());
        }
        return outcome;
    }

    public String strReplace(String outcome) {
        outcome = outcome.replace("CURRENTPERIOD", Formatter.formatDate(period));
        if (formula.getVal(period) != null) {
            outcome = outcome.replace("CURRENTVALUE", Formatter.finalNumberFormat(formula.getVal(period)));
        }
        return outcome;
    }

    private String loopNormatives() {
        EvaluationTypes formulaType;
        Double valueToCompare;
        Boolean match;
        for (Formula normative : childs) {
            try {
                formulaType = EvaluationTypes.valueOf(normative.getName());
                if (formulaType.equals(type)) {
                    valueToCompare = normative.getValue().length() > 0 ? Formatter.parseDouble(normative.getValue()) : null;
                    match = matches(
                            normative.getShortName(),
                            normative.getCategory(),
                            formula.getVal(period),
                            valueToCompare
                    );
                    if (match) {
                        return InTimeReplacer.getVal(normative.getDescription(), formula);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        return "";
    }


}
