package reportGeneration.interpreter.ReusableComponents;

import defaultData.EvaluationTypes;
import entities.Formula;
import reportGeneration.interpreter.ReusableComponents.NormValsEvaluator.GeneralRenderer;
import reportGeneration.interpreter.ReusableComponents.NormValsEvaluator.NormValsEvaluator;
import reportGeneration.interpreter.ReusableComponents.NormValsEvaluator.PeriodComparisonEvaluator;
import reportGeneration.storage.Periods;

import java.util.ArrayList;

public class FormulaEvaluateBase {
    private ArrayList<String> periodsarr = Periods.getInstance().getPeriodArr();
    private Formula formula;
    private String end;
    private String start;
    private String type;

    public FormulaEvaluateBase(Formula formula) {
        this.formula = formula;
        this.end = periodsarr.get(periodsarr.size() - 1);
        this.start = periodsarr.get(0);
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

        return output.toString();

    }

    public String periodsComparison() {
        PeriodComparisonEvaluator periodsComparison = new PeriodComparisonEvaluator(formula);
        return periodsComparison.getResult();
    }


}
