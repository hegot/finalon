package defaultData.Formula.ProfitabilityAndPerformance;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NetProfitMargin {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int NetProfitMargin = counter;
        Formulas.add(new Formula(NetProfitMargin,
                "Net Profit Margin",
                "NetProfitMargin",
                "(ProfitLossBeforeTax-IncomeTaxExpenseContinuingOperations)/RevenueGeneral",
                "",
                "formula",
                "%",
                parent));
        return Formulas;
    }
}
