package reportGeneration.interpreter.FinancialSustainability.Outcomes;

import entities.Formula;
import globalReusables.LabelWrap;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.FormulaEvaluateBase;
import reportGeneration.interpreter.ReusableComponents.NormValsEvaluator.StrReplacer;
import reportGeneration.interpreter.ReusableComponents.interfaces.AttachChilds;
import reportGeneration.storage.Periods;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.SettingsStorage;

public class FormulaEvaluation implements LabelWrap, AttachChilds {
    private ObservableList<Formula> formulas;
    private Periods periods;
    private ObservableMap<String, String> settings;

    public FormulaEvaluation(ObservableList<Formula> formulas) {
        this.periods = Periods.getInstance();
        this.formulas = formulas;
        this.settings = SettingsStorage.getInstance().getSettings();
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
            ResultsStorage.addStr(weight, "test", outcome);
        }
        return vbox;
    }

    private String evaluateSingle(Formula formula) {
        StringBuilder output = new StringBuilder();
        FormulaEvaluateBase evaluator = new FormulaEvaluateBase(formula);
        output.append(evaluator.prefix());
        String code = formula.getShortName();
        if (code.equals("DebtRatio")) {
            DebtRatioHook debtRatio = new DebtRatioHook(formula);
            output.append(debtRatio.getResult());
        }
        output.append(evaluator.startAndEnd());
        output.append(evaluator.periodsComparison());
        output.append(evaluator.suffix());
        return output.toString();
    }


}
