package reportGeneration.interpreter.ReusableComponents;

import defaultData.EvaluationTypes;
import entities.Formula;
import reportGeneration.interpreter.ReusableComponents.NormValsEvaluator.*;
import reportGeneration.storage.Periods;

import java.util.ArrayList;

public class FormulaEvaluateBase {
    private ArrayList<String> periodsarr = Periods.getInstance().getPeriodArr();
    private Formula formula;
    private String end;
    private String type;

    public FormulaEvaluateBase(Formula formula) {
        this.formula = formula;
        this.end = periodsarr.get(periodsarr.size() - 1);
        this.type = formula.getDescription();
    }

    public String prefix() {
        GeneralRenderer generalRenderer = new GeneralRenderer(formula);
        return generalRenderer.get(EvaluationTypes.PREFIX);
    }

    public String suffix() {
        GeneralRenderer generalRenderer = new GeneralRenderer(formula);
        return generalRenderer.get(EvaluationTypes.SUFFIX);
    }

    public String multivariate() {
        if (type.equals(EvaluationTypes.EACH_PERIOD_MULTIVARIATE.toString())) {
            EachPeriodMultivariate multivariate = new EachPeriodMultivariate(formula);
            return multivariate.getResult();
        }
        return "";
    }


    public String endOnly() {
        if (type.equals(EvaluationTypes.EVALUATE_END_ONLY.toString())) {
            NormValsEvaluator eval = new NormValsEvaluator(formula, end);
            return eval.getResult();
        }
        return "";
    }

    public String evaluateEach() {
        if (type.equals(EvaluationTypes.EVALUATE_EACH_PERIOD.toString())) {
            StringBuilder output = new StringBuilder();
            for (String period : periodsarr) {
                NormValsEvaluator eval = new NormValsEvaluator(formula, period);
                output.append(eval.getResult());
            }
            return output.toString();
        }
        return "";
    }

    public String periodsComparison() {
        PeriodComparisonEvaluator periodsComparison = new PeriodComparisonEvaluator(formula);
        return periodsComparison.getResult();
    }

    public String eachPeriodTrue() {
        EachPeriodTrue eachPeriodTrue = new EachPeriodTrue(formula);
        return eachPeriodTrue.getResult();
    }

    public String endEvaluation() {
        AdditionalEndEvaluation additionalEndEvaluation = new AdditionalEndEvaluation(formula, end);
        return additionalEndEvaluation.getResult();
    }

}
