package reportGeneration.interpreter.FinancialSustainability.Outcomes;

import database.formula.DbFormulaHandler;
import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.NormValsEvaluator.NormValsEvaluator;
import reportGeneration.interpreter.NormValsEvaluator.PeriodComparisonEvaluator;
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
            outcome += switchType(formula);
            PeriodComparisonEvaluator periodsComparison = new PeriodComparisonEvaluator(formula);
            outcome += periodsComparison.getResult();
        }
        outcome = substitute(outcome);
        VBox vbox = new VBox();
        vbox.getChildren().add(labelWrap(outcome));
        return vbox;
    }

    private String substitute(String outcome) {
        outcome = outcome.replace("ENDDATE", periods.getEnd());
        outcome = outcome.replace("STARTDATE", periods.getStart());
        return outcome.replace("COMPANYNAME", settings.get("company"));
    }

    private void setFormulaChilds(Formula formula) {
        DbFormulaHandler dbFormula = new DbFormulaHandler();
        ObservableList<Formula> childs = dbFormula.getFormulas(formula.getId());
        formula.setChilds(childs);
    }


    private String switchType(Formula formula) {
        String output = "";
        String type = formula.getDescription();
        String code = formula.getShortName();
        if (code.equals("DebtRatio")) {
            DebtRatioHook debtRatio = new DebtRatioHook(formula);
            output += "\n" + debtRatio.getResult();
        }
        if (type.equals(EvaluationTypes.EVALUATE_END_ONLY.toString())) {
            ArrayList<String> periodsarr = periods.getPeriodArr();
            String end = periodsarr.get(periodsarr.size() - 1);
            NormValsEvaluator eval = new NormValsEvaluator(formula, end);
            output += "\n" + eval.getResult();
        }
        return output;
    }


}
