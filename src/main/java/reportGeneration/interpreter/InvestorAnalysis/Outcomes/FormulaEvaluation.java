package reportGeneration.interpreter.InvestorAnalysis.Outcomes;

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
            String res = "";
            for (Formula formula : formulas) {
                formula.attachChilds();
                res = evaluateSingle(formula);
                if (res.length() > 2) {
                    outcome += res + "\n\n";
                    if (outcome.length() > 0) {
                        outcome = StrReplacer.substitute(outcome, formula);
                    }
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

