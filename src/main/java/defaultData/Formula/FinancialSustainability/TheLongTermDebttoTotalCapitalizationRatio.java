package defaultData.Formula.FinancialSustainability;

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
                "excellent",
                ">=",
                "0.6",
                "",
                "",
                "",
                TheLongTermDebttoTotalCapitalizationRatio));
        counter++;
        int TheLongTermDebttoTotalCapitalizationRatiosatisfactory = counter;
        Formulas.add(new Formula(TheLongTermDebttoTotalCapitalizationRatiosatisfactory,
                "satisfactory",
                "<=",
                "0.4",
                "",
                "<",
                "0.6",
                TheLongTermDebttoTotalCapitalizationRatio));
        counter++;
        int TheLongTermDebttoTotalCapitalizationRatiobad = counter;
        Formulas.add(new Formula(TheLongTermDebttoTotalCapitalizationRatiobad,
                "bad",
                "<",
                "0.4",
                "",
                "",
                "",
                TheLongTermDebttoTotalCapitalizationRatio));
        return Formulas;
    }

}
