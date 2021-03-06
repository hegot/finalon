package defaultData.Formula.Liquidity;

import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class QuickRatio {

    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int QuickRatio = counter;
        Formulas.add(new Formula(QuickRatio,
                "Quick Ratio (Acid Test Ratio)",
                "QuickRatio",
                "(CashAndCashEquivalents+TradeAndOtherCurrentReceivables)/CurrentLiabilitiesGeneral",
                "",
                "formula",
                "",
                parent));
        counter++;
        int CurrentRatiosatisfactory = counter;
        Formulas.add(new Formula(CurrentRatiosatisfactory,
                EvaluationTypes.EVALUATE_END.toString(),
                ">=",
                "1",
                "The company's quick liquidity was satisfactory at the end of the period. ",
                "",
                "",
                QuickRatio));
        counter++;
        int CurrentRatioexcellent = counter;
        Formulas.add(new Formula(CurrentRatioexcellent,
                EvaluationTypes.EVALUATE_END.toString(),
                "<",
                "1",
                "The company's quick liquidity was unsatisfactory at the end of the period. ",
                ">=",
                "0",
                QuickRatio));
        counter++;
        int QuickRatioPrefix = counter;
        Formulas.add(new Formula(QuickRatioPrefix,
                EvaluationTypes.PREFIX.toString(),
                "",
                "",
                "The quick ratio for STARTDATE was STARTVALUE, showing there were STARTVALUE of the quick assets for every USD 1.00 of the current liabilities. ",
                "",
                "",
                QuickRatio));
        counter++;
        int QuickRatioSuffix = counter;
        Formulas.add(new Formula(QuickRatioSuffix,
                EvaluationTypes.SUFFIX.toString(),
                "",
                "",
                "The ratio for ENDDATE from Table 6 shows LASTVALUE of the quick assets were available for every USD 1.00 of the current liabilities. ",
                "",
                "",
                QuickRatio));
        counter++;
        int QuickRatioIncrease = counter;
        Formulas.add(new Formula(QuickRatioIncrease,
                EvaluationTypes.PERIOD_COMPARISON_INCREASE.toString(),
                "",
                "",
                "The increase in the quick ratio from STARTDATE-ENDDATE indicates that the company was improving its ability to pay its short-term debt. ",
                "",
                "",
                QuickRatio));
        counter++;
        int QuickRatioDecrease = counter;
        Formulas.add(new Formula(QuickRatioDecrease,
                EvaluationTypes.PERIOD_COMPARISON_DECREASE.toString(),
                "",
                "",
                "The decrease in the quick ratio from STARTDATE-ENDDATE indicates that the company was losing its ability to pay its short-term debt. ",
                "",
                "",
                QuickRatio));
        return Formulas;
    }
}
