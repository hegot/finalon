package defaultData.Formula.AltmanZScore;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class X1 {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int X1 = counter;
        Formulas.add(new Formula(X1,
                "Altman Z-score X1",
                "X1",
                "(GeneralCurrentAssets-CurrentLiabilities)/AssetsGeneral",
                "",
                "formula",
                "",
                parent));
        return Formulas;
    }
}
