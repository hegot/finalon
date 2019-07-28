package reportGeneration.interpreter.LaborProductivity.Outcomes;

import entities.Formula;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.FormulaEvaluateBase;
import reportGeneration.interpreter.ReusableComponents.NormValsEvaluator.StrReplacer;
import reportGeneration.interpreter.ReusableComponents.interfaces.AttachChilds;
import reportGeneration.interpreter.ReusableComponents.interfaces.JsCalcHelper;
import reportGeneration.interpreter.ReusableComponents.interfaces.LabelWrap;
import reportGeneration.storage.FormulaStorage;
import reportGeneration.storage.Periods;

import java.util.ArrayList;

public class FormulaEvaluation implements LabelWrap, AttachChilds, JsCalcHelper {
    private Formula formula;

    public FormulaEvaluation() {
        FormulaStorage storage = FormulaStorage.getInstance();
        this.formula = storage.get("LaborProductivity");
    }

    public VBox get() {
        String outcome = "";
        setFormulaChilds(formula);
        outcome += evalMultiple();
        outcome += evaluateSingle(formula) + "\n";
        if (outcome.length() > 0) {
            StrReplacer replacer = new StrReplacer(outcome, formula);
            outcome = replacer.substitute();
        }
        VBox vbox = new VBox();
        vbox.getChildren().add(labelWrap(outcome));
        return vbox;
    }

    private String evalMultiple() {
        if (formula.getPeriods().size() > 2) {
            String outcome = "";
            ArrayList<String> arr = Periods.getInstance().getPeriodArr();
            Double first = formula.getFirstVal();
            Double second = formula.getVal(arr.get(1));
            Double third = formula.getVal(arr.get(2));

            String firstRange = formatDate(arr.get(0)) + " to " + formatDate(arr.get(1));

            if (second > first) {
                outcome += "Chart shows COMPANYNAME productivity improved from "
                        + firstRange;
            } else {
                outcome += "Chart shows COMPANYNAME productivity decreased from "
                        + firstRange;
            }
            if (third > second) {
                outcome += ", and increased in " + formatDate(arr.get(2)) + ". ";
            } else {
                outcome += ", and decreased in " + formatDate(arr.get(2)) + ". ";
            }
            return outcome;
        }
        return "";
    }

    private String evaluateSingle(Formula formula) {
        StringBuilder output = new StringBuilder();
        FormulaEvaluateBase evaluator = new FormulaEvaluateBase(formula);
        output.append(evaluator.prefix());
        output.append(evaluator.periodsComparison());
        return output.toString();
    }
}
