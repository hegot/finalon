package finalon.reportGeneration.interpreter.ZScoreModel.Outcomes;

import finalon.entities.Formula;
import finalon.globalReusables.LabelWrap;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import finalon.reportGeneration.interpreter.ReusableComponents.FormulaEvaluateBase;
import finalon.reportGeneration.interpreter.ReusableComponents.NormValsEvaluator.StrReplacer;
import finalon.reportGeneration.interpreter.ReusableComponents.interfaces.AttachChilds;
import finalon.reportGeneration.storage.ResultsStorage;

public class FormulaEvaluation implements LabelWrap, AttachChilds {
    private ObservableList<Formula> formulas;

    public FormulaEvaluation(ObservableList<Formula> formulas) {
        this.formulas = formulas;
    }

    public VBox get(int weight) {
        VBox vbox = new VBox();
        if (formulas != null) {
            String outcome = "";
            for (Formula formula : formulas) {
                setFormulaChilds(formula);
                outcome += evaluateSingle(formula) + "\n\n";
                if (outcome.length() > 0) {
                    StrReplacer replacer = new StrReplacer(outcome, formula);
                    outcome = replacer.substitute();
                }
            }
            vbox.getChildren().add(labelWrap(outcome));
            ResultsStorage.addStr(weight, "text", outcome);
        }
        return vbox;
    }

    private String evaluateSingle(Formula formula) {
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
