package defaultData.Formula.ProfitabilityAndPerformance;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReturnOnCurrentAssets {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int ReturnoNonCurrentAssets = counter;
        Formulas.add(new Formula(ReturnoNonCurrentAssets,
                "Return on Non-current Assets",
                "ReturnOnNonCurrentAssets",
                "(ProfitLossBeforeTax-IncomeTaxExpenseContinuingOperations)/(AssetsGeneral[1]/2+AssetsGeneral[0]/2)",
                "",
                "formula",
                "%", parent));
        return Formulas;
    }
}
