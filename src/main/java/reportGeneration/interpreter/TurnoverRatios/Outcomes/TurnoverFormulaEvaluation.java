package reportGeneration.interpreter.TurnoverRatios.Outcomes;

import entities.Formula;
import javafx.collections.ObservableList;
import reportGeneration.interpreter.ReusableComponents.FormulaEvaluateBase;
import reportGeneration.interpreter.ReusableComponents.FormulaEvaluation;

public class TurnoverFormulaEvaluation extends FormulaEvaluation {

    public TurnoverFormulaEvaluation(ObservableList<Formula> formulas) {
        super(formulas);
    }

    protected String evaluateSingle(Formula formula) {
        StringBuilder output = new StringBuilder();
        FormulaEvaluateBase evaluator = new FormulaEvaluateBase(formula);
        output.append(evaluator.prefix());
        output.append(evaluator.startAndEnd());
        output.append(evaluator.periodsComparison());
        String code = formula.getShortName();
        if (code.equals("TotalAssetTurnover")) {
            TotalAssetTurnoverHook totalAssetTurnover = new TotalAssetTurnoverHook(formula);
            output.append(totalAssetTurnover.getResult());
        }
        output.append(evaluator.suffix());
        if (code.equals("AccountsPayableTurnover")) {
            RecivablePayableAccountsComparison recivablePayable = new RecivablePayableAccountsComparison();
            output.append(recivablePayable.getResult());
        }
        return output.toString();
    }
}
