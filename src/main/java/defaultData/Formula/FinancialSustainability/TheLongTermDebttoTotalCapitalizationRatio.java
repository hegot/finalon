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
               "",
                "formula",
                "",
                parent));
        counter++;
        int TheLongTermDebttoTotalCapitalizationRatioexcellent = counter;
        Formulas.add(new Formula(TheLongTermDebttoTotalCapitalizationRatioexcellent,
                EvaluationTypes.EVALUATE_EACH_PERIOD.toString(),
                ">=",
                "0.6",
                "CURRENTVALUE in CURRENTPERIOD (unsatisfactory - more than 0.6), ",
                "",
                "",
                TheLongTermDebttoTotalCapitalizationRatio));
        counter++;
        int TheLongTermDebttoTotalCapitalizationRatiosatisfactory = counter;
        Formulas.add(new Formula(TheLongTermDebttoTotalCapitalizationRatiosatisfactory,
                EvaluationTypes.EVALUATE_EACH_PERIOD.toString(),
                "<=",
                "0.4",
                "CURRENTVALUE in CURRENTPERIOD (satisfactory - more than 0.4 and less than 0.6), ",
                "<",
                "0.6",
                TheLongTermDebttoTotalCapitalizationRatio));
        counter++;
        int TheLongTermDebttoTotalCapitalizationRatiobad = counter;
        Formulas.add(new Formula(TheLongTermDebttoTotalCapitalizationRatiobad,
                EvaluationTypes.EVALUATE_EACH_PERIOD.toString(),
                "<",
                "0.4",
                "CURRENTVALUE in CURRENTPERIOD (excellent - less than 0.4), ",
                "",
                "",
                TheLongTermDebttoTotalCapitalizationRatio));
        counter++;
        int TheLongTermDebttoTotalCapitalizationRatioPrefix = counter;
        Formulas.add(new Formula(TheLongTermDebttoTotalCapitalizationRatioPrefix,
                EvaluationTypes.PREFIX.toString(),
                "",
                "",
                "The value of the long-term debt to total capitalization ratio was ",
                "",
                "",
                TheLongTermDebttoTotalCapitalizationRatio));
        counter++;
        int TheLongTermDebttoTotalCapitalizationRatioSuffix = counter;
        Formulas.add(new Formula(TheLongTermDebttoTotalCapitalizationRatioSuffix,
                EvaluationTypes.SUFFIX.toString(),
                "",
                "",
                "Total capitalization consists of the long-term debt, preferred stock, and common stockholders' equity. Lower ratio shows lower risk.",
                "",
                "",
                TheLongTermDebttoTotalCapitalizationRatio));
        counter++;
        return Formulas;
    }

}
