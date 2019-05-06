package defaultData.Formula.Turnover;

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
                "",
                "formula",
                "times",
                parent));
        counter++;
        int WorkingCapitalTurnovergood = counter;
        Formulas.add(new Formula(WorkingCapitalTurnovergood,
                "good",
                ">",
                "5",
                "",
                "",
                "",
                WorkingCapitalTurnover));
        counter++;
        int WorkingCapitalTurnoversatisfactory = counter;
        Formulas.add(new Formula(WorkingCapitalTurnoversatisfactory,
                "unsatisfactory",
                "<=",
                "5",
                "",
                "",
                "",
                WorkingCapitalTurnover));

        return Formulas;
    }
}
