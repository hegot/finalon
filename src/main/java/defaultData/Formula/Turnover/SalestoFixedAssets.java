package defaultData.Formula.Turnover;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SalestoFixedAssets {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int SalestoFixedAssets = counter;
        Formulas.add(new Formula(SalestoFixedAssets,
                "Sales to Fixed Assets",
                "SalestoFixedAssets",
                "RevenueGeneral/(PropertyPlantAndEquipment[0]/2+PropertyPlantAndEquipment[1]/2)",
                "",
                "formula",
                "times",
                parent));
        counter++;
        int SalestoFixedAssetsgood = counter;
        Formulas.add(new Formula(SalestoFixedAssetsgood,
                "good",
                ">",
                "8",
                "",
                "",
                "",
                SalestoFixedAssets));
        counter++;
        int SalestoFixedAssetsunsatisfactory = counter;
        Formulas.add(new Formula(SalestoFixedAssetsunsatisfactory,
                "unsatisfactory",
                "<=",
                "8",
                "",
                "",
                "",
                SalestoFixedAssets));
        return Formulas;
    }
}
