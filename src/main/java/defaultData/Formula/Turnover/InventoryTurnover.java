package defaultData.Formula.Turnover;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InventoryTurnover {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();

        int InventoryTurnover = counter;
        Formulas.add(new Formula(InventoryTurnover,
                "Inventory Turnover  (Days Inventory Outstanding)",
                "InventoryTurnover",
                "CostOfSales/((Inventories[1]+Inventories[0])/2)",
                "",
                "formula",
                "days",
                parent));
        counter++;
        int InventoryTurnovergood = counter;
        Formulas.add(new Formula(InventoryTurnovergood,
                "good",
                ">",
                "6",
                "",
                "",
                "",
                InventoryTurnover));
        counter++;
        int InventoryTurnoversatisfactory = counter;
        Formulas.add(new Formula(InventoryTurnoversatisfactory,
                "unsatisfactory",
                "<=",
                "6",
                "",
                "",
                "",
                InventoryTurnover));
        return Formulas;
    }
}
