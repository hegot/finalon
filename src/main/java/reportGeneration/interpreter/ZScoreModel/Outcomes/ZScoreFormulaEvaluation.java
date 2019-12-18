package reportGeneration.interpreter.ZScoreModel.Outcomes;

import entities.Formula;
import javafx.collections.ObservableList;
import reportGeneration.interpreter.ReusableComponents.FormulaEvaluateBase;
import reportGeneration.interpreter.ReusableComponents.FormulaEvaluation;

public class ZScoreFormulaEvaluation extends FormulaEvaluation {

    public ZScoreFormulaEvaluation(ObservableList<Formula> formulas) {
        super(formulas);
    }

    protected String evaluateSingle(Formula formula) {
        StringBuilder output = new StringBuilder();
        FormulaEvaluateBase evaluator = new FormulaEvaluateBase(formula);
        output.append(evaluator.prefix());
        String code = formula.getShortName();
        if (code.equals("TotalAssetTurnover")) {
            ZIndexHook ZIndexHook = new ZIndexHook(formula);
            output.append(ZIndexHook.getResult());
        }
        output.append(evaluator.startAndEnd());
        output.append(evaluator.periodsComparison());
        output.append(evaluator.suffix());

        return output.toString();
    }
}
