package defaultData.Formula.FinancialSustainability;

import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TheLongTermDebttoTotalCapitalizationRatio {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int TheLongTermDebttoTotalCapitalizationRatio = counter;
        Formulas.add(new Formula(TheLongTermDebttoTotalCapitalizationRatio,
                "The Long-Term Debt to Total Capitalization Ratio",
                "TheLongTermDebttoTotalCapitalizationRatio",
                "(NonCurrentAssets)/(NonCurrentAssets+IssuedCapital+SharePremium)",
                EvaluationTypes.EVALUATE_EACH_PERIOD.toString(),
                "formula",
                "",
                parent));
        counter++;
        int TheLongTermDebttoTotalCapitalizationRatioexcellent = counter;
        Formulas.add(new Formula(TheLongTermDebttoTotalCapitalizationRatioexcellent,
                "excellent",
                ">=",
                "0.6",
                "CURRENTVALUE in CURRENTPERIOD (excellent - more than 0.6), ",
                "",
                "",
                TheLongTermDebttoTotalCapitalizationRatio));
        counter++;
        int TheLongTermDebttoTotalCapitalizationRatiosatisfactory = counter;
        Formulas.add(new Formula(TheLongTermDebttoTotalCapitalizationRatiosatisfactory,
                "satisfactory",
                "<=",
                "0.4",
                "CURRENTVALUE in CURRENTPERIOD (satisfactory - more than 0.4 and less than 0.6), ",
                "<",
                "0.6",
                TheLongTermDebttoTotalCapitalizationRatio));
        counter++;
        int TheLongTermDebttoTotalCapitalizationRatiobad = counter;
        Formulas.add(new Formula(TheLongTermDebttoTotalCapitalizationRatiobad,
                "bad",
                "<",
                "0.4",
                "CURRENTVALUE in CURRENTPERIOD (unsatisfactory - less than 0.4), ",
                "",
                "",
                TheLongTermDebttoTotalCapitalizationRatio));
        counter++;
        int TheLongTermDebttoTotalCapitalizationRatioPrefix = counter;
        Formulas.add(new Formula(TheLongTermDebttoTotalCapitalizationRatioPrefix,
                "general",
                "",
                "The value of the long-term debt to total capitalization ratio was ",
                EvaluationTypes.PREFIX.toString(),
                "",
                "",
                TheLongTermDebttoTotalCapitalizationRatio));
        counter++;
        int TheLongTermDebttoTotalCapitalizationRatioSuffix = counter;
        Formulas.add(new Formula(TheLongTermDebttoTotalCapitalizationRatioSuffix,
                "general",
                "",
                "Total capitalization consists of the long-term debt, preferred stock, and common stockholders' equity. Lower ratio shows lower risk.",
                EvaluationTypes.SUFFIX.toString(),
                "",
                "",
                TheLongTermDebttoTotalCapitalizationRatio));
        counter++;
        return Formulas;
    }

}
