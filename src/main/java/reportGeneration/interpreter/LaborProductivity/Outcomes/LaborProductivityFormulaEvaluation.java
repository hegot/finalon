package reportGeneration.interpreter.LaborProductivity.Outcomes;

import entities.Formula;
import globalReusables.LabelWrap;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.FormulaEvaluateBase;
import reportGeneration.interpreter.ReusableComponents.FormulaEvaluation;
import reportGeneration.interpreter.ReusableComponents.NormValsEvaluator.StrReplacer;
import reportGeneration.storage.ResultsStorage;

public class LaborProductivityFormulaEvaluation extends FormulaEvaluation {

    public LaborProductivityFormulaEvaluation(ObservableList<Formula> formulas) {
        super(formulas);
    }

    protected String evaluateSingle(Formula formula) {
        StringBuilder output = new StringBuilder();
        FormulaEvaluateBase evaluator = new FormulaEvaluateBase(formula);
        output.append(evaluator.prefix());
        output.append(evaluator.periodsComparison());
        return output.toString();
    }
}
