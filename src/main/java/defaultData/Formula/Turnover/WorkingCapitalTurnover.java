package defaultData.Formula.Turnover;

import defaultData.EvaluationTypes;
import entities.Formula;
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
                 EvaluationTypes.EVALUATE_EACH_PERIOD.toString(),
                "formula",
                "times",
                parent));
        counter++;
        int WorkingCapitalTurnovergood = counter;
        Formulas.add(new Formula(WorkingCapitalTurnovergood,
                "good",
                ">",
                "5",
                "good (more than 0.6) CURRENTVALUE in CURRENTPERIOD, ",
                "",
                "",
                WorkingCapitalTurnover));
        counter++;
        int WorkingCapitalTurnoversatisfactory = counter;
        Formulas.add(new Formula(WorkingCapitalTurnoversatisfactory,
                "unsatisfactory",
                "<=",
                "5",
                "CURRENTVALUE in CURRENTPERIOD (unsatisfactory <= 5), ",
                "",
                "",
                WorkingCapitalTurnover));
        counter++;
        int WorkingCapitalTurnoverPrefix = counter;
        Formulas.add(new Formula(WorkingCapitalTurnoverPrefix,
                EvaluationTypes.PREFIX.toString(),
                "",
                "",
                "It can be seen that the working capital turnover was ",
                "",
                "",
                WorkingCapitalTurnover));
        counter++;
        return Formulas;
    }
}
