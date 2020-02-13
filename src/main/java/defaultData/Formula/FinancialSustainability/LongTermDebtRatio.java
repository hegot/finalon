package defaultData.Formula.FinancialSustainability;

import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LongTermDebtRatio {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int LongTermDebtRatio = counter;
        Formulas.add(new Formula(LongTermDebtRatio,
                "Long-Term Debt Ratio",
                "LongTermDebtRatio",
                "NonCurrentLiabilities/AssetsGeneral",
                "",
                "formula",
                "",
                parent));
        counter++;
        int LongTermDebtRatioexcellent = counter;
        Formulas.add(new Formula(LongTermDebtRatioexcellent,
                EvaluationTypes.EVALUATE_END.toString(),
                ">=",
                "0.2",
                "Company had unsatisfactory Long-Term Debt Ratio (>= 0.2). LASTVALUEPERCENT% of the sources of finance were a long-term debt at the end of ENDDATE. ",
                "",
                "",
                LongTermDebtRatio));
        counter++;
        int LongTermDebtRatiosatisfactory = counter;
        Formulas.add(new Formula(LongTermDebtRatiosatisfactory,
                EvaluationTypes.EVALUATE_END.toString(),
                ">=",
                "0.1",
                "Company had satisfactory Long-Term Debt Ratio (between 0.1 and 0.2). LASTVALUEPERCENT% of the sources of finance were a long-term debt at the end of ENDDATE. ",
                "<",
                "0.2",
                LongTermDebtRatio));
        counter++;
        int LongTermDebtRatiobad = counter;
        Formulas.add(new Formula(LongTermDebtRatiobad,
                EvaluationTypes.EVALUATE_END.toString(),
                "<",
                "0.1",
                "Company had excellent Long-Term Debt Ratio (less than 0.1). LASTVALUEPERCENT% of the sources of finance were a long-term debt at the end of ENDDATE. ",
                "",
                "",
                LongTermDebtRatio));
        counter++;
        int LongTermDebtRatioIncrease = counter;
        Formulas.add(new Formula(LongTermDebtRatioIncrease,
                EvaluationTypes.PERIOD_COMPARISON_INCREASE.toString(),
                "",
                "",
                "The share of the long-term debt was unstable in STARTDATE-ENDDATE. " +
                        "An increase in the long-term debt ratio suggests that the company was progressively " +
                        "becoming more dependent on debt to grow a business. ",
                "",
                "",
                LongTermDebtRatio));
        counter++;
        int LongTermDebtRatioDecrease = counter;
        Formulas.add(new Formula(LongTermDebtRatioDecrease,
                EvaluationTypes.PERIOD_COMPARISON_DECREASE.toString(),
                "",
                "",
                "The share of the long-term debt was unstable in STARTDATE-ENDDATE. " +
                        "A decrease in the long-term debt ratio suggests that the company was progressively " +
                        "becoming less dependent on debt to grow a business. ",
                "",
                "",
                LongTermDebtRatio));
        return Formulas;
    }
}
