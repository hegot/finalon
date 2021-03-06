package defaultData.Formula.Turnover;

import defaultData.EvaluationTypes;
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
                "360*((CurrentInventories[1]+CurrentInventories[0])/2)/CostOfSales",
                "",
                "formula",
                "days",
                parent));
        counter++;
        int InventoryTurnoverinDaysgood = counter;
        Formulas.add(new Formula(InventoryTurnoverinDaysgood,
                EvaluationTypes.EVALUATE_END.toString(),
                "<",
                "60",
                "",
                "",
                "",
                InventoryTurnoverinDays));
        counter++;
        int InventoryTurnoverinDayssatisfactory = counter;
        Formulas.add(new Formula(InventoryTurnoverinDayssatisfactory,
                EvaluationTypes.EVALUATE_END.toString(),
                ">=",
                "60",
                "",
                "",
                "",
                InventoryTurnoverinDays));
        counter++;
        int InventoryTurnoverinDaysIncrease = counter;
        Formulas.add(new Formula(InventoryTurnoverinDaysIncrease,
                EvaluationTypes.PERIOD_COMPARISON_INCREASE.toString(),
                "",
                "",
                "The company was holding the inventory almost a CHANGE day(s) more in ENDDATE than in AFTERSTART. ",
                "",
                "",
                InventoryTurnoverinDays));
        counter++;
        int InventoryTurnoverinDaysDecrease = counter;
        Formulas.add(new Formula(InventoryTurnoverinDaysDecrease,
                EvaluationTypes.PERIOD_COMPARISON_DECREASE.toString(),
                "",
                "",
                "The company was holding the inventory almost a CHANGE day(s) less in ENDDATE than in AFTERSTART. ",
                "",
                "",
                InventoryTurnoverinDays));
        return Formulas;
    }
}
