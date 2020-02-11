package reportGeneration.interpreter.ReusableComponents;

import defaultData.EvaluationTypes;
import entities.Formula;
import reportGeneration.interpreter.ReusableComponents.NormValsEvaluator.GeneralRenderer;
import reportGeneration.interpreter.ReusableComponents.NormValsEvaluator.NormValsEvaluator;
import reportGeneration.interpreter.ReusableComponents.NormValsEvaluator.PeriodComparisonEvaluator;
import reportGeneration.storage.Periods;

import java.util.ArrayList;

public class FormulaEvaluateBase {
    private Formula formula;
    private String end;

    public FormulaEvaluateBase(Formula formula) {
        ArrayList<String> periodsarr = Periods.getPeriodArr();
        this.formula = formula;
        this.end = periodsarr.get(periodsarr.size() - 1);
    }

    public String prefix() {
        GeneralRenderer generalRenderer = new GeneralRenderer(formula);
        return appendWhitespace(generalRenderer.get(EvaluationTypes.PREFIX));
    }

    public String suffix() {
        GeneralRenderer generalRenderer = new GeneralRenderer(formula);
        return appendWhitespace(generalRenderer.get(EvaluationTypes.SUFFIX));
    }

    public String startAndEnd() {

        StringBuilder output = new StringBuilder();

        NormValsEvaluator eval1 = new NormValsEvaluator(
                formula,
                formula.getFormulaStart(),
                EvaluationTypes.EVALUATE_PRE_END
        );
        output.append(eval1.getResult());

        NormValsEvaluator eval2 = new NormValsEvaluator(
                formula,
                end,
                EvaluationTypes.EVALUATE_END
        );
        output.append(eval2.getResult());

        return appendWhitespace(output.toString());

    }

    public String periodsComparison() {
        PeriodComparisonEvaluator periodsComparison = new PeriodComparisonEvaluator(formula);
        return appendWhitespace(periodsComparison.getResult());
    }


    private String appendWhitespace(String str) {
        if (str.length() > 1 && str.endsWith(" ")) {
            str = str + " ";
        }
        return str;
    }
}
