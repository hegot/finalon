package defaultData.Formula.ProfitabilityAndPerformance;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EquityChange {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        Formulas.add(new Formula(counter,
                "Equity Change",
                "EquityChange",
                "(EquityGeneral[1]-(EquityGeneral[0]))/EquityGeneral[0]",
                "",
                "formula",
                "",
                parent));

        return Formulas;
    }
}
