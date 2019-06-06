package defaultData.Formula.Turnover;

import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AccountsPayableTurnover {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();

        int AccountsPayableTurnover = counter;
        Formulas.add(new Formula(AccountsPayableTurnover,
                "Accounts Payable Turnover",
                "AccountsPayableTurnover",
                "CostOfSales/(((TradeAndOtherCurrentPayables[0]+TradeAndOtherCurrentPayables[1])/2))",
                EvaluationTypes.EVALUATE_END_ONLY.toString(),
                "formula",
                "times",
                parent));
        counter++;
        int AccountsPayableTurnovergood = counter;
        Formulas.add(new Formula(AccountsPayableTurnovergood,
                "good",
                ">",
                "6",
                "(good > 6).",
                "",
                "",
                AccountsPayableTurnover));
        counter++;
        int AccountsPayableTurnoversatisfactory = counter;
        Formulas.add(new Formula(AccountsPayableTurnoversatisfactory,
                "unsatisfactory",
                "<=",
                "6",
                "(unsatisfactory <= 6).",
                "",
                "",
                AccountsPayableTurnover));

        counter++;
        int AccountsPayableTurnoverPrefix = counter;
        Formulas.add(new Formula(AccountsPayableTurnoverPrefix,
                EvaluationTypes.PREFIX.toString(),
                "",
                "",
                "The accounts payable turned over STARTVALUE " +
                        "times in AFTERSTART and LASTVALUE times in ENDDATE ",
                "",
                "",
                AccountsPayableTurnover));
        return Formulas;
    }
}
