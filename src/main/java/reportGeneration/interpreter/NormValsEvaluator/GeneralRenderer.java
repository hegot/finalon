package reportGeneration.interpreter.NormValsEvaluator;

import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class GeneralRenderer {
    private Formula formula;

    public GeneralRenderer(Formula formula) {
        this.formula = formula;
    }

    public String get(EvaluationTypes type) {
        ObservableList<Formula> childs = formula.getChilds();
        ObservableMap<String, String> periods = formula.getPeriods();
        if(periods.size() > 1){
            for (Formula child : childs) {
                if (child.getDescription().equals(type.toString())) {
                    return child.getValue();
                }
            }
        }
        return "";
    }
}
