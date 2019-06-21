package reportGeneration.interpreter.ReusableComponents.NormValsEvaluator;

import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.ObservableList;
import reportGeneration.interpreter.ReusableComponents.interfaces.JsCalcHelper;
import reportGeneration.interpreter.ReusableComponents.interfaces.ParseDouble;

public class NormValsEvaluator extends ValsEvaluator implements JsCalcHelper, ParseDouble {
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
        outcome = outcome.replace("CURRENTPERIOD", formatDate(period));
        if (formula.getVal(period) != null) {
            outcome = outcome.replace("CURRENTVALUE", toString(formula.getVal(period)));
        }
        return outcome;
    }

    private String loopNormatives() {
        for (Formula normative : childs) {
            try {
                EvaluationTypes formulaType = EvaluationTypes.valueOf(normative.getName());
                if (formulaType.equals(type)) {
                    Double valueToCompare = normative.getValue().length() > 0 ? parseDouble(normative.getValue()) : null;
                    Boolean match = matches(
                            normative.getShortName(),
                            normative.getCategory(),
                            formula.getVal(period),
                            valueToCompare
                    );
                    if (match) {
                        return normative.getDescription();
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        return "";
    }


}
