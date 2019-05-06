package defaultData.Formula.ProfitabilityAndPerformance;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OperatingIncomeMargin {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int OperatingIncomeMargin = counter;
        Formulas.add(new Formula(OperatingIncomeMargin,
                "Operating Income Margin",
                "OperatingIncomeMargin",
                "ProfitLossFromOperatingActivities/RevenueGeneral",
                "",
                "formula",
                "%",
                parent));
        return Formulas;
    }
}
