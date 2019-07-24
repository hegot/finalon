package defaultData.Formula.Turnover;

import defaultData.EvaluationTypes;
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
                EvaluationTypes.EVALUATE_END.toString(),
                ">",
                "12",
                "The cash turnover ratio was LASTVALUE in ENDDATE (good - more than 12),",
                "",
                "",
                CashTurnover));
        counter++;
        int CashTurnoversatisfactory = counter;
        Formulas.add(new Formula(CashTurnoversatisfactory,
                EvaluationTypes.EVALUATE_END.toString(),
                "<=",
                "12",
                "The cash turnover ratio was LASTVALUE in ENDDATE (unsatisfactory - less than 12),",
                "",
                "",
                CashTurnover));
        return Formulas;
    }
}
