package reportGeneration.interpreter.ProfitabilityRatios.Outcomes;

import entities.Formula;
import javafx.collections.ObservableList;
import reportGeneration.interpreter.ReusableComponents.FormulaEvaluateBase;
import reportGeneration.interpreter.ReusableComponents.FormulaEvaluation;
import reportGeneration.interpreter.ReusableComponents.NormValsEvaluator.InTimeReplacer;

public class ProfitabilityFormulaEvaluation extends FormulaEvaluation {

    public ProfitabilityFormulaEvaluation(ObservableList<Formula> formulas) {
        super(formulas);
    }

    protected String evaluateSingle(Formula formula) {
        StringBuilder output = new StringBuilder();
        FormulaEvaluateBase evaluator = new FormulaEvaluateBase(formula);
        output.append(evaluator.prefix());
        String code = formula.getShortName();
        if (code.equals("ReturnonEquityafterTax")) {
            ReturnonEquityafterTaxHook returnonEquityafterTax = new ReturnonEquityafterTaxHook(formula);
            output.append(InTimeReplacer.getVal(returnonEquityafterTax.getResult(), formula));
        }
        output.append(evaluator.startAndEnd());
        output.append(evaluator.periodsComparison());
        output.append(evaluator.suffix());
        return output.toString();
    }
}
