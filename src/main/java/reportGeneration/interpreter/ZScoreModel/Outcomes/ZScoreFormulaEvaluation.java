package reportGeneration.interpreter.ZScoreModel.Outcomes;

import entities.Formula;
import globalReusables.LabelWrap;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.FormulaEvaluateBase;
import reportGeneration.interpreter.ReusableComponents.FormulaEvaluation;
import reportGeneration.interpreter.ReusableComponents.NormValsEvaluator.StrReplacer;
import reportGeneration.storage.ResultsStorage;

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
