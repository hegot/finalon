package defaultData.Formula.FinancialSustainability;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DebtEquityRatio {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int DebtEquityRatio = counter;
        Formulas.add(new Formula(DebtEquityRatio,
                "Debt/Equity Ratio",
                "DebtEquityRatio",
                "(NonCurrentAssets+CurrentLiabilities)/EquityGeneral",
                "",
                "formula",
                "",
                parent));
        return Formulas;
    }
}
