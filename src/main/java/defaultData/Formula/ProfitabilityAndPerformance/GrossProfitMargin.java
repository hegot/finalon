package defaultData.Formula.ProfitabilityAndPerformance;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GrossProfitMargin {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int GrossProfitMargin = counter;
        Formulas.add(new Formula(GrossProfitMargin,
                "Gross Profit Margin",
                "GrossProfitMargin",
                "GrossProfit/RevenueGeneral",
                "",
                "formula",
                "%",
                parent));
        counter++;
        int GrossProfitMargingood = counter;
        Formulas.add(new Formula(GrossProfitMargingood,
                "good",
                ">",
                "0",
                "",
                "",
                "",
                GrossProfitMargin));
        counter++;
        int GrossProfitMarginunsatisfactory = counter;
        Formulas.add(new Formula(GrossProfitMarginunsatisfactory,
                "unsatisfactory",
                "<=",
                "0",
                "",
                "",
                "",
                GrossProfitMargin));
        counter++;
        return Formulas;
    }
}
