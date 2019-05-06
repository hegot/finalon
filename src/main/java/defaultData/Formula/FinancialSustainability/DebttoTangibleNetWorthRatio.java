package defaultData.Formula.FinancialSustainability;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DebttoTangibleNetWorthRatio {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int DebttoTangibleNetWorthRatio = counter;
        Formulas.add(new Formula(DebttoTangibleNetWorthRatio,
                "Debt to Tangible Net Worth Ratio",
                "DebttoTangibleNetWorthRatio",
                "(NonCurrentAssets+CurrentLiabilities)/(EquityGeneral-IntangibleAssetsOtherThanGoodwill-GoodwillGeneral)",
                "",
                "formula",
                "",
                parent));
        counter++;
        int DebttoTangibleNetWorthRatioexcellent = counter;
        Formulas.add(new Formula(DebttoTangibleNetWorthRatioexcellent,
                "excellent",
                ">=",
                "0.6",
                "",
                "",
                "",
                DebttoTangibleNetWorthRatio));
        counter++;
        int DebttoTangibleNetWorthRatiosatisfactory = counter;
        Formulas.add(new Formula(DebttoTangibleNetWorthRatiosatisfactory,
                "satisfactory",
                "<=",
                "0.4",
                "",
                "<",
                "0.6",
                DebttoTangibleNetWorthRatio));
        counter++;
        int DebttoTangibleNetWorthRatiobad = counter;
        Formulas.add(new Formula(DebttoTangibleNetWorthRatiobad,
                "bad",
                "<",
                "0.4",
                "",
                "",
                "",
                DebttoTangibleNetWorthRatio));
        return Formulas;
    }
}
