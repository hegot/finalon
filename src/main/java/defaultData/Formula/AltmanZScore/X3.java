package defaultData.Formula.AltmanZScore;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class X3 {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int X3 = counter;
        Formulas.add(new Formula(X3,
                "Altman Z-score X3",
                "X3",
                "(ProfitLossBeforeTax+FinanceCosts)/AssetsGeneral",
                "",
                "formula",
                "",
                parent));
        return Formulas;
    }
}
