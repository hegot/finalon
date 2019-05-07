package defaultData.Formula.InvestmentAnalysis;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NetAssets {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        Formulas.add(new Formula(counter,
                "Net assets (Net worth)",
                "NetAssets",
                "AssetsGeneral-NonCurrentAssets-GeneralCurrentAssets",
                "",
                "formula",
                "money",
                parent));
        return Formulas;
    }
}
