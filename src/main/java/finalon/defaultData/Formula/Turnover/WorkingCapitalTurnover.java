package finalon.defaultData.Formula.Turnover;

import finalon.defaultData.EvaluationTypes;
import finalon.entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WorkingCapitalTurnover {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int WorkingCapitalTurnover = counter;
        Formulas.add(new Formula(WorkingCapitalTurnover,
                "Working Capital Turnover",
                "WorkingCapitalTurnover",
                "RevenueGeneral/((GeneralCurrentAssets[1]-CurrentLiabilities[1]+GeneralCurrentAssets[0]-CurrentLiabilities[0])/2)",
                "",
                "formula",
                "times",
                parent));
        counter++;
        int WorkingCapitalTurnovergood = counter;
        Formulas.add(new Formula(WorkingCapitalTurnovergood,
                EvaluationTypes.EVALUATE_END.toString(),
                ">",
                "5",
                "It can be seen that the working capital turnover provided LASTVALUE in ENDDATE (good - more than 0.6). ",
                "",
                "",
                WorkingCapitalTurnover));
        counter++;
        int WorkingCapitalTurnoversatisfactory = counter;
        Formulas.add(new Formula(WorkingCapitalTurnoversatisfactory,
                EvaluationTypes.EVALUATE_END.toString(),
                "<=",
                "5",
                "It can be seen that the working capital turnover was LASTVALUE in ENDDATE (unsatisfactory <= 5), ",
                "",
                "",
                WorkingCapitalTurnover));
        return Formulas;
    }
}
