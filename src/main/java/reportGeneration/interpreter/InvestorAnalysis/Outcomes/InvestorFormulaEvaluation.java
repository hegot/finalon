package reportGeneration.interpreter.InvestorAnalysis.Outcomes;

import entities.Formula;
import globalReusables.LabelWrap;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.FormulaEvaluateBase;
import reportGeneration.interpreter.ReusableComponents.NormValsEvaluator.StrReplacer;
import reportGeneration.storage.ResultsStorage;

import reportGeneration.interpreter.ReusableComponents.FormulaEvaluation;

public class InvestorFormulaEvaluation  extends FormulaEvaluation {

    public InvestorFormulaEvaluation(ObservableList<Formula> formulas) {
        super(formulas);
    }

    protected String evaluateSingle(Formula formula) {
        StringBuilder output = new StringBuilder();
        FormulaEvaluateBase evaluator = new FormulaEvaluateBase(formula);
        output.append(evaluator.prefix());
        output.append(evaluator.startAndEnd());
        String code = formula.getShortName();
        if (code.equals("DegreeOfFinancialLeverage")) {
            FinancialLeverageHook debtRatio = new FinancialLeverageHook(formula);
            output.append(debtRatio.getResult());
        }
        output.append(evaluator.periodsComparison());
        output.append(evaluator.suffix());
        return output.toString();
    }
}

