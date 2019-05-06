package defaultData.Formula.Turnover;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InventoryTurnoverinDays {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int InventoryTurnoverinDays = counter;
        Formulas.add(new Formula(InventoryTurnoverinDays,
                "Inventory Turnover in Days",
                "InventoryTurnoverinDays",
                "360*((Inventories[1]+Inventories[0])/2)/CostOfSales",
                "",
                "formula",
                "days",
                parent));
        counter++;
        int InventoryTurnoverinDaysgood = counter;
        Formulas.add(new Formula(InventoryTurnoverinDaysgood,
                "good",
                "<",
                "60",
                "",
                "",
                "",
                InventoryTurnoverinDays));
        counter++;
        int InventoryTurnoverinDayssatisfactory = counter;
        Formulas.add(new Formula(InventoryTurnoverinDayssatisfactory,
                "unsatisfactory",
                ">=",
                "60",
                "",
                "",
                "",
                InventoryTurnoverinDays));
        return Formulas;
    }
}
