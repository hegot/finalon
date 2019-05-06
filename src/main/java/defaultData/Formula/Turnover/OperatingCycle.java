package defaultData.Formula.Turnover;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OperatingCycle {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int OperatingCycle = counter;
        Formulas.add(new Formula(OperatingCycle,
                "Operating Cycle",
                "OperatingCycle",
                "360*((Inventories[1]+Inventories[0])/2)/CostOfSales+360*((TradeAndOtherCurrentReceivables[0]+TradeAndOtherCurrentReceivables[1])/2)/RevenueGeneral",
                "",
                "formula",
                "days",
                parent));
        counter++;
        int OperatingCyclegood = counter;
        Formulas.add(new Formula(OperatingCyclegood,
                "good",
                "<",
                "90",
                "",
                "",
                "",
                OperatingCycle));
        counter++;
        int OperatingCyclesatisfactory = counter;
        Formulas.add(new Formula(OperatingCyclesatisfactory,
                "unsatisfactory",
                ">=",
                "90",
                "",
                "",
                "",
                OperatingCycle));
        return Formulas;
    }
}
