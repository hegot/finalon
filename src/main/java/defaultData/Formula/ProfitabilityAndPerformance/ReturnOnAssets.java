package defaultData.Formula.ProfitabilityAndPerformance;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReturnOnAssets {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int ReturnoNonAssets = counter;
        Formulas.add(new Formula(ReturnoNonAssets,
                "Return on Assets",
                "ReturnoNonAssets",
                "(ProfitLossBeforeTax-IncomeTaxExpenseContinuingOperations)/(AssetsGeneral[0]/2+AssetsGeneral[1]/2)",
                "",
                "formula",
                "%",
                parent));
        counter++;
        int ReturnoNonAssetsexcellent = counter;
        Formulas.add(new Formula(ReturnoNonAssetsexcellent,
                "excellent",
                ">",
                "5",
                "",
                "",
                "",
                ReturnoNonAssets));
        counter++;
        int ReturnoNonAssetsgood = counter;
        Formulas.add(new Formula(ReturnoNonAssetsgood,
                "good",
                ">",
                "3",
                "",
                "",
                "",
                ReturnoNonAssets));
        counter++;
        int ReturnoNonAssetssatisfactory = counter;
        Formulas.add(new Formula(ReturnoNonAssetssatisfactory,
                "satisfactory",
                ">",
                "0",
                "",
                "",
                "",
                ReturnoNonAssets));
        counter++;
        int ReturnoNonAssetsunsatisfactory = counter;
        Formulas.add(new Formula(ReturnoNonAssetsunsatisfactory,
                "unsatisfactory",
                "<=",
                "0",
                "",
                "",
                "",
                ReturnoNonAssets));
        return Formulas;
    }
}
