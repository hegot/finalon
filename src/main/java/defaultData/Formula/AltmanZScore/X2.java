package defaultData.Formula.AltmanZScore;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class X2 {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int X2 = counter;
        Formulas.add(new Formula(X2,
                "Altman Z-score X2 (Retained Earnings /Total Assets)",
                "X2",
                "RetainedEarnings/AssetsGeneral",
                "",
                "formula",
                "",
                parent));
        return Formulas;
    }
}
