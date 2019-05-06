package defaultData.Formula.Turnover;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TotalAssetTurnover {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int TotalAssetTurnover = counter;
        Formulas.add(new Formula(TotalAssetTurnover,
                "Total Asset Turnover",
                "TotalAssetTurnover",
                "RevenueGeneral/(AssetsGeneral[0]/2+AssetsGeneral[1]/2)",
                "",
                "formula",
                "times",
                parent));
        counter++;
        int TotalAssetTurnovergood = counter;
        Formulas.add(new Formula(TotalAssetTurnovergood,
                "good",
                ">",
                "6",
                "",
                "",
                "",
                TotalAssetTurnover));
        counter++;
        int TotalAssetTurnoverunsatisfactory = counter;
        Formulas.add(new Formula(TotalAssetTurnoverunsatisfactory,
                "unsatisfactory",
                "<=",
                "6",
                "",
                "",
                "",
                TotalAssetTurnover));

        return Formulas;
    }
}
