package defaultData.Formula.Turnover;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CashTurnover {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int CashTurnover = counter;
        Formulas.add(new Formula(CashTurnover,
                "Cash Turnover",
                "CashTurnover",
                "RevenueGeneral/((CashAndCashEquivalents[0]+CashAndCashEquivalents[1])/2)",
                "",
                "formula",
                "times",
                parent));
        counter++;
        int CashTurnovergood = counter;
        Formulas.add(new Formula(CashTurnovergood,
                "good",
                ">",
                "12",
                "",
                "",
                "",
                CashTurnover));
        counter++;
        int CashTurnoversatisfactory = counter;
        Formulas.add(new Formula(CashTurnoversatisfactory,
                "unsatisfactory",
                "<=",
                "12",
                "",
                "",
                "",
                CashTurnover));
        return Formulas;
    }
}
