package reportGeneration.interpreter.ProfitabilityRatios.Outcomes;

import database.formula.DbFormulaHandler;
import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.FinancialSustainability.Outcomes.DebtRatioHook;
import reportGeneration.interpreter.NormValsEvaluator.*;
import reportGeneration.interpreter.ReusableComponents.interfaces.LabelWrap;
import reportGeneration.storage.Periods;
import reportGeneration.storage.SettingsStorage;

import java.util.ArrayList;

public class FormulaEvaluation implements LabelWrap {
    private ObservableList<Formula> formulas;
    private Periods periods;
    private ObservableMap<String, String> settings;

    public FormulaEvaluation(ObservableList<Formula> formulas) {
        this.periods = Periods.getInstance();
        this.formulas = formulas;
        this.settings = SettingsStorage.getSettings();
    }


    public VBox get() {
        String outcome = "";
        for (Formula formula : formulas) {
            setFormulaChilds(formula);
            outcome += switchType(formula) + "\n\n";
            if (outcome.length() > 0) {
                StrReplacer replacer = new StrReplacer(outcome, formula);
                outcome = replacer.substitute();
            }
        }
        VBox vbox = new VBox();
        vbox.getChildren().add(labelWrap(outcome));
        return vbox;
    }


    private void setFormulaChilds(Formula formula) {
        DbFormulaHandler dbFormula = new DbFormulaHandler();
        ObservableList<Formula> childs = dbFormula.getFormulas(formula.getId());
        formula.setChilds(childs);
    }


    private String switchType(Formula formula) {
        StringBuilder output = new StringBuilder();
        ArrayList<String> periodsarr = periods.getPeriodArr();
        String end = periodsarr.get(periodsarr.size() - 1);
        String type = formula.getDescription();
        GeneralRenderer generalRenderer = new GeneralRenderer(formula);

        output.append(generalRenderer.get(EvaluationTypes.PREFIX));

        if (type.equals(EvaluationTypes.EACH_PERIOD_MULTIVARIATE.toString())) {
            EachPeriodMultivariate multivariate = new EachPeriodMultivariate(formula);
            output.append(multivariate.getResult());
        }

        output.append(generalRenderer.get(EvaluationTypes.GENERAL));

        if (type.equals(EvaluationTypes.EVALUATE_END_ONLY.toString())) {
            NormValsEvaluator eval = new NormValsEvaluator(formula, end);
            output.append(eval.getResult());
        }

        if (type.equals(EvaluationTypes.EVALUATE_EACH_PERIOD.toString())) {
            for (String period : periodsarr) {
                NormValsEvaluator eval = new NormValsEvaluator(formula, period);
                output.append(eval.getResult());
            }
        }



        PeriodComparisonEvaluator periodsComparison = new PeriodComparisonEvaluator(formula);
        output.append(periodsComparison.getResult());

        EachPeriodTrue eachPeriodTrue = new EachPeriodTrue(formula);
        output.append(eachPeriodTrue.getResult());

        SuffixEvaluator suffixEval = new SuffixEvaluator(formula, end);
        output.append(suffixEval.getResult());

        output.append(generalRenderer.get(EvaluationTypes.SUFFIX));
        return output.toString();
    }
}
