package defaultData.Formula.FinancialSustainability;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DebtRatio {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int DebtRatio = counter;
        Formulas.add(new Formula(DebtRatio,
                "Debt Ratio",
                "DebtRatio",
                "(NonCurrentAssets+CurrentLiabilities)/AssetsGeneral",
                "",
                "formula",
                "",
                parent));
        counter++;
        int DebtRatioexcellent = counter;
        Formulas.add(new Formula(DebtRatioexcellent,
                "excellent",
                "<=",
                "0.4",
                "",
                "",
                "",
                DebtRatio));
        counter++;
        int DebtRatiogood = counter;
        Formulas.add(new Formula(DebtRatiogood,
                "good", "<",
                "0.4",
                "",
                "<=",
                "0.6",
                DebtRatio));
        counter++;
        int DebtRatiobad = counter;
        Formulas.add(new Formula(DebtRatiobad,
                "bad",
                ">",
                "0.6",
                "",
                "",
                "",
                DebtRatio));
        counter++;
        return Formulas;
    }
}
