package reportGeneration.interpreter.TurnoverRatios.Outcomes;

import entities.Formula;
import globalReusables.LabelWrap;
import reportGeneration.interpreter.ReusableComponents.FormulaEvaluateBase;
import reportGeneration.interpreter.ReusableComponents.NormValsEvaluator.StrReplacer;
import reportGeneration.storage.ResultsStorage;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;

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
                    StrReplacer replacer = new StrReplacer(outcome, formula);
                    outcome = replacer.substitute();
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
