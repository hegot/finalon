package defaultData.Formula.Turnover;

import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InventoryTurnover {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();

        int InventoryTurnover = counter;
        Formulas.add(new Formula(InventoryTurnover,
                "Inventory Turnover",
                "InventoryTurnover",
                "CostOfSales/((CurrentInventories[1]+CurrentInventories[0])/2)",
                "",
                "formula",
                "times",
                parent));
        counter++;
        int InventoryTurnovergood = counter;
        Formulas.add(new Formula(InventoryTurnovergood,
                EvaluationTypes.EVALUATE_END.toString(),
                ">",
                "6",
                "Inventory Turnover index comes into 'good' range ( > 6) at the end of the period. ",
                "",
                "",
                InventoryTurnover));
        counter++;
        int InventoryTurnoversatisfactory = counter;
        Formulas.add(new Formula(InventoryTurnoversatisfactory,
                EvaluationTypes.EVALUATE_END.toString(),
                "<=",
                "6",
                "Inventory Turnover index comes into 'unsatisfactory' range ( <= 6) at the end of the period. ",
                "",
                "",
                InventoryTurnover));
        counter++;
        int DaysPayableOutstandingIncrease = counter;
        Formulas.add(new Formula(DaysPayableOutstandingIncrease,
                EvaluationTypes.PERIOD_COMPARISON_INCREASE.toString(),
                "",
                "",
                "COMPANYNAME inventory was more active in ENDDATE than in AFTERSTART. ",
                "",
                "",
                InventoryTurnover));
        counter++;
        int DaysPayableOutstandingDecrease = counter;
        Formulas.add(new Formula(DaysPayableOutstandingDecrease,
                EvaluationTypes.PERIOD_COMPARISON_DECREASE.toString(),
                "",
                "",
                "COMPANYNAME inventory was more active in AFTERSTART than in ENDDATE. ",
                "",
                "",
                InventoryTurnover));
        return Formulas;
    }
}
