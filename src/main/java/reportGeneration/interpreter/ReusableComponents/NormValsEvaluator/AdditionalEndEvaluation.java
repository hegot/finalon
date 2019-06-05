package reportGeneration.interpreter.ReusableComponents.NormValsEvaluator;

import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.ObservableList;
import reportGeneration.interpreter.ReusableComponents.interfaces.JsCalcHelper;

public class AdditionalEndEvaluation extends ValsEvaluator implements JsCalcHelper {
    private Formula formula;
    private ObservableList<Formula> childs;
    private String period;

    public AdditionalEndEvaluation(Formula formula, String period) {
        this.formula = formula;
        this.childs = formula.getChilds();
        this.period = period;
    }

    public String getResult() {
        String outcome = "";
        if (formula.getPeriods().size() > 0) {
            outcome += loopNormatives();
        }
        return outcome;
    }

    private String loopNormatives() {
        for (Formula normative : childs) {
            if (normative.getName().equals(EvaluationTypes.ADDITIONAL_END_EVALUATION.toString())) {
                try {
                    Double valueToCompare = normative.getUnit().length() > 0 ? parseDouble(normative.getUnit()) : null;
                    Boolean match = matches(
                            normative.getShortName(),
                            normative.getCategory(),
                            formula.getVal(period),
                            valueToCompare
                    );
                    if (match) {
                        return normative.getDescription();
                    }
                } catch (NumberFormatException e) {

                }
            }
        }
        return "";
    }

}
