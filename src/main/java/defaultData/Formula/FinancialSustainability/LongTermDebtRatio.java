package defaultData.Formula.FinancialSustainability;

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
                "NonCurrentAssets/AssetsGeneral",
                "",
                "formula",
                "",
                parent));
        counter++;
        int LongTermDebtRatioexcellent = counter;
        Formulas.add(new Formula(LongTermDebtRatioexcellent,
                "excellent",
                ">=",
                "0.2",
                "",
                "",
                "",
                LongTermDebtRatio));
        counter++;
        int LongTermDebtRatiosatisfactory = counter;
        Formulas.add(new Formula(LongTermDebtRatiosatisfactory,
                "satisfactory",
                "<=",
                "0.1",
                "",
                "<",
                "0.2",
                LongTermDebtRatio));
        counter++;
        int LongTermDebtRatiobad = counter;
        Formulas.add(new Formula(LongTermDebtRatiobad,
                "bad",
                "<",
                "0.1",
                "",
                "",
                "",
                LongTermDebtRatio));
        return Formulas;
    }
}
