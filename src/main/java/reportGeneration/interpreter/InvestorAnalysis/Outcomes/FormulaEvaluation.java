package reportGeneration.interpreter.InvestorAnalysis.Outcomes;

import entities.Formula;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.FormulaEvaluateBase;
import reportGeneration.interpreter.ReusableComponents.NormValsEvaluator.StrReplacer;
import reportGeneration.interpreter.ReusableComponents.interfaces.AttachChilds;
import reportGeneration.interpreter.ReusableComponents.interfaces.LabelWrap;

public class FormulaEvaluation implements LabelWrap, AttachChilds {
    private ObservableList<Formula> formulas;

    public FormulaEvaluation(ObservableList<Formula> formulas) {
        this.formulas = formulas;
    }

    public VBox get() {
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
        return vbox;
    }

    private String evaluateSingle(Formula formula) {
        StringBuilder output = new StringBuilder();
        FormulaEvaluateBase evaluator = new FormulaEvaluateBase(formula);
        output.append(evaluator.prefix());
        output.append(evaluator.multivariate());
        output.append(evaluator.endOnly());
        output.append(evaluator.evaluateEach());
        output.append(evaluator.startAndEnd());
        output.append(evaluator.substituteEvaluator());
        output.append(evaluator.periodsComparison());
        output.append(evaluator.eachPeriodTrue());
        output.append(evaluator.endEvaluation());
        output.append(evaluator.suffix());
        return output.toString();
    }
}

