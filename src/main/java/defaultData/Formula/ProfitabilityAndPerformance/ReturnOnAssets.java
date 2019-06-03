package defaultData.Formula.ProfitabilityAndPerformance;

import defaultData.EvaluationTypes;
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
                EvaluationTypes.EVALUATE_END_ONLY.toString(),
                "formula",
                "%",
                parent));
        counter++;
        int ReturnoNonAssetsexcellent = counter;
        Formulas.add(new Formula(ReturnoNonAssetsexcellent,
                "excellent",
                ">",
                "0",
                "For COMPANYNAME the ROA shows that the company was earning a profit of about LASTVALUE cents per CURRENCY of the asset value in ENDDATE",
                "",
                "",
                ReturnoNonAssets));
        counter++;
        int ReturnoNonAssetssatisfactory = counter;
        Formulas.add(new Formula(ReturnoNonAssetssatisfactory,
                "satisfactory",
                "==",
                "0",
                "For COMPANYNAME the ROA shows that the company's profitability was 0%. After taking into account inflation, this ratio indicates actual decrease in company value in ENDDATE.",
                "",
                "",
                ReturnoNonAssets));
        counter++;
        int ReturnoNonAssetsunsatisfactory = counter;
        Formulas.add(new Formula(ReturnoNonAssetsunsatisfactory,
                "unsatisfactory",
                "<",
                "0",
                "For COMPANYNAME the ROA shows that the company was losing about LASTVALUE cents per CURRENCY of the asset value in ENDDATE.",
                "",
                "",
                ReturnoNonAssets));
        return Formulas;
    }
}
