package defaultData.Formula.ProfitabilityAndPerformance;

import defaultData.EvaluationTypes;
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
                "(ProfitLossFromOperatingActivities/(AssetsGeneral[0]/2+AssetsGeneral[1]/2-IntangibleAssetsOtherThanGoodwill[0]/2-IntangibleAssetsOtherThanGoodwill[1]/2-GoodwillGeneral[0]/2-GoodwillGeneral[1]/2-OtherNoncurrentNonfinancialAssets[0]/2-OtherNoncurrentNonfinancialAssets[1]/2-DeferredTaxAssets[0]/2-DeferredTaxAssets[1]/2-OtherNoncurrentFinancialAssets[0]/2-OtherNoncurrentFinancialAssets[1]/2-CurrentTaxAssetsNoncurrent[0]/2-CurrentTaxAssetsNoncurrent[1]/2))*100",
                "",
                "formula",
                "%",
                parent));
        counter++;
        int ReturnonOperatingAssetsPrefix = counter;
        Formulas.add(new Formula(ReturnonOperatingAssetsPrefix,
                EvaluationTypes.PREFIX.toString(),
                "",
                "",
                "The operating assets were yielding LASTVALUE% return in ENDDATE. ",
                "",
                "",
                ReturnonOperatingAssets));
        counter++;
        int ReturnonOperatingAssetsIncrease = counter;
        Formulas.add(new Formula(ReturnonOperatingAssetsIncrease,
                EvaluationTypes.PERIOD_COMPARISON_INCREASE.toString(),
                "",
                "",
                "This ratio was CHANGE higher at the end of ENDDATE comparing to STARTDATE. ",
                "",
                "",
                ReturnonOperatingAssets));
        counter++;
        int ReturnonOperatingAssetsDecrease = counter;
        Formulas.add(new Formula(ReturnonOperatingAssetsDecrease,
                EvaluationTypes.PERIOD_COMPARISON_DECREASE.toString(),
                "",
                "",
                "This ratio was CHANGE higher at the end of ENDDATE comparing to STARTDATE. ",
                "",
                "",
                ReturnonOperatingAssets));
        counter++;
        return Formulas;
    }
}
