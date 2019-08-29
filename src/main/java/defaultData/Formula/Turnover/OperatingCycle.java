package defaultData.Formula.Turnover;

import defaultData.EvaluationTypes;
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
                EvaluationTypes.EVALUATE_END.toString(),
                "<",
                "90",
                "The operating cycle was LASTVALUE (good < 90) in ENDDATE. " +
                        "Shorter operating cycle indicates better performance. ",
                "",
                "",
                OperatingCycle));
        counter++;
        int OperatingCyclesatisfactory = counter;
        Formulas.add(new Formula(OperatingCyclesatisfactory,
                EvaluationTypes.EVALUATE_END.toString(),
                ">=",
                "90",
                "The operating cycle was LASTVALUE (unsatisfactory >= 90) in ENDDATE. " +
                        "Shorter operating cycle indicates better performance. ",
                "",
                "",
                OperatingCycle));
        counter++;
        int OperatingCyclePrefix = counter;
        Formulas.add(new Formula(OperatingCyclePrefix,
                EvaluationTypes.PREFIX.toString(),
                "",
                "",
                "It took the company STARTVALUE days from a moment of buying " +
                        "inventories until getting cash for selling goods and services in AFTERSTART. ",
                "",
                "",
                OperatingCycle));
        return Formulas;
    }
}
