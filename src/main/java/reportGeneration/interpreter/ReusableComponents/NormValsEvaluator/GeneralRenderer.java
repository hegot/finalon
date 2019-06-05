package reportGeneration.interpreter.ReusableComponents.NormValsEvaluator;

import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.ObservableList;

public class GeneralRenderer {
    private Formula formula;

    public GeneralRenderer(Formula formula) {
        this.formula = formula;
    }

    public String get(EvaluationTypes type) {
        ObservableList<Formula> childs = formula.getChilds();
        if (formula.getPeriods().size() > 1) {
            for (Formula child : childs) {
                if (child.getName().equals(type.toString())) {
                    return child.getDescription();
                }
            }
        }
        return "";
    }
}
