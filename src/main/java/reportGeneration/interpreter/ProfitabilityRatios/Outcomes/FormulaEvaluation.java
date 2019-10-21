package reportGeneration.interpreter.ProfitabilityRatios.Outcomes;

import entities.Formula;
import globalReusables.LabelWrap;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.FormulaEvaluateBase;
import reportGeneration.interpreter.ReusableComponents.NormValsEvaluator.StrReplacer;
import reportGeneration.storage.ResultsStorage;

public class FormulaEvaluation {
    private ObservableList<Formula> formulas;

    public FormulaEvaluation(ObservableList<Formula> formulas) {
        this.formulas = formulas;
    }

    public VBox get(int weight) {
        VBox vbox = new VBox();
        if (formulas != null) {
            String outcome = "";
            for (Formula formula : formulas) {
                formula.attachChilds();
                outcome += evaluateSingle(formula) + "\n\n";
                if (outcome.length() > 0) {
                    outcome = StrReplacer.substitute(outcome, formula);
                }
            }
            vbox.getChildren().add(LabelWrap.wrap(outcome));
            ResultsStorage.addStr(weight, "text", outcome);
        }
        return vbox;
    }

    private String evaluateSingle(Formula formula) {
        StringBuilder output = new StringBuilder();
        FormulaEvaluateBase evaluator = new FormulaEvaluateBase(formula);
        output.append(evaluator.prefix());
        String code = formula.getShortName();
        if (code.equals("ReturnonEquityafterTax")) {
            ReturnonEquityafterTaxHook returnonEquityafterTax = new ReturnonEquityafterTaxHook(formula);
            output.append(returnonEquityafterTax.getResult());
        }
        output.append(evaluator.startAndEnd());
        output.append(evaluator.periodsComparison());
        output.append(evaluator.suffix());
        return output.toString();
    }


}
