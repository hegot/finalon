package reportGeneration.interpreter.TurnoverRatios.Outcomes;

import entities.Formula;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.FormulaEvaluateBase;
import reportGeneration.interpreter.ReusableComponents.NormValsEvaluator.StrReplacer;
import reportGeneration.interpreter.ReusableComponents.interfaces.AttachChilds;
import globalReusables.LabelWrap;
import reportGeneration.storage.ResultsStorage;

public class FormulaEvaluation implements LabelWrap, AttachChilds {
    private ObservableList<Formula> formulas;

    public FormulaEvaluation(ObservableList<Formula> formulas) {
        this.formulas = formulas;
    }

    public VBox get(int weight) {
        String outcome = "";
        for (Formula formula : formulas) {
            setFormulaChilds(formula);
            outcome += evaluateSingle(formula) + "\n\n";
            if (outcome.length() > 0) {
                StrReplacer replacer = new StrReplacer(outcome, formula);
                outcome = replacer.substitute();
            }
        }
        VBox vbox = new VBox();
        vbox.getChildren().add(labelWrap(outcome));
        ResultsStorage.addStr(weight, "text", outcome);

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
