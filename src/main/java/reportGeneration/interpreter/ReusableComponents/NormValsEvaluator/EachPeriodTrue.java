package reportGeneration.interpreter.ReusableComponents.NormValsEvaluator;

import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.ObservableList;
import reportGeneration.interpreter.ReusableComponents.interfaces.JsCalcHelper;
import reportGeneration.storage.Periods;

import java.util.ArrayList;

public class EachPeriodTrue extends ValsEvaluator implements JsCalcHelper {
    private ObservableList<Formula> childs;
    private Formula formula;
    private ArrayList<String> periods;

    public EachPeriodTrue(Formula formula) {
        this.childs = formula.getChilds();
        this.periods = Periods.getInstance().getPeriodArr();
        this.formula = formula;
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
            if (normative.getName().equals(EvaluationTypes.EACH_PERIOD_TRUE.toString())) {
                try {
                    Double valueToCompare = normative.getValue().length() > 0 ? parseDouble(normative.getValue()) : null;
                    Boolean fits = true;
                    for (String period : periods) {
                        Boolean match = matches(
                                normative.getShortName(),
                                "",
                                formula.getVal(period),
                                valueToCompare
                        );
                        if (!match) {
                            fits = false;
                        }
                    }
                    if (fits) {
                        return normative.getDescription();
                    }
                } catch (NumberFormatException e) {
                    System.out.println(e.toString());
                }
            }
        }
        return "";
    }
}
