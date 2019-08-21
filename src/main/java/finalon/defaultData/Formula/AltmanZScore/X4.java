package finalon.defaultData.Formula.AltmanZScore;

import finalon.entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class X4 {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int X4 = counter;
        Formulas.add(new Formula(X4,
                "Altman Z-score X4 (Market Value of Equity/Book Value of Total Debt)",
                "X4",
                "EquityGeneral/(NonCurrentAssets+CurrentLiabilities)",
                "",
                "formula",
                "",
                parent));
        return Formulas;
    }
}
