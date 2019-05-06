package defaultData.Formula.ProfitabilityAndPerformance;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReturnonOperatingAssets {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int ReturnonOperatingAssets = counter;
        Formulas.add(new Formula(ReturnonOperatingAssets,
                "Return on Operating Assets",
                "ReturnonOperatingAssets",
                "ProfitLossFromOperatingActivities/(AssetsGeneral[0]/2+AssetsGeneral[1]/2-IntangibleAssetsOtherThanGoodwill[0]/2-IntangibleAssetsOtherThanGoodwill[1]/2-GoodwillGeneral[0]/2-GoodwillGeneral[1]/2-OtherNoncurrentNonfinancialAssets[0]/2-OtherNoncurrentNonfinancialAssets[1]/2-DeferredTaxAssets[0]/2-DeferredTaxAssets[1]/2-OtherNoncurrentFinancialAssets[0]/2-OtherNoncurrentFinancialAssets[1]/2-CurrentTaxAssetsNoncurrent[0]/2-CurrentTaxAssetsNoncurrent[1]/2)",
                "",
                "formula",
                "%",
                parent));
        return Formulas;
    }
}
