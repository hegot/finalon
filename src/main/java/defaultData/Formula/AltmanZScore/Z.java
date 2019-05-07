package defaultData.Formula.AltmanZScore;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Z {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int Z = counter;
        Formulas.add(new Formula(Z,
                "Altman Z-score Z",
                "Z",
                "0.717*(GeneralCurrentAssets-CurrentLiabilities)/AssetsGeneral+0.847*RetainedEarnings/AssetsGeneral+3.107*(ProfitLossBeforeTax+FinanceCosts)/AssetsGeneral+0.42*EquityGeneral/(NonCurrentAssets+CurrentLiabilities)+0.998*RevenueGeneral/AssetsGeneral",
                "",
                "formula",
                "",
                parent));
        return Formulas;
    }
}
