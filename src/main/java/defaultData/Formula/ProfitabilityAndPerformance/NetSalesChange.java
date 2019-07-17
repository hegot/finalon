package defaultData.Formula.ProfitabilityAndPerformance;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NetSalesChange {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int NetSalesChange = counter;
        Formulas.add(new Formula(NetSalesChange,
                "Net Sales Change",
                "NetSalesChange",
                "(RevenueGeneral[1]-(RevenueGeneral[0]))/RevenueGeneral[0]",
                "",
                "formula",
                "",
                parent));

        return Formulas;
    }
}
