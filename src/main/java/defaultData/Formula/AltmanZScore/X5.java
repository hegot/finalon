package defaultData.Formula.AltmanZScore;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class X5 {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int X5 = counter;
        Formulas.add(new Formula(X5,
                "Altman Z-score X5 (Sales/Total Assets)",
                "X5",
                "RevenueGeneral/AssetsGeneral",
                "",
                "formula",
                "",
                parent));
        return Formulas;
    }
}
