package defaultData.Formula.Turnover;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AccountsReceivableTurnover {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int AccountsReceivableTurnover = counter;
        Formulas.add(new Formula(AccountsReceivableTurnover,
                "Accounts Receivable Turnover (Times)",
                "AccountsReceivableTurnover",
                "RevenueGeneral/((TradeAndOtherCurrentReceivables[0]+TradeAndOtherCurrentReceivables[1])/2)",
                "",
                "formula",
                "times",
                parent));
        counter++;
        int AccountsReceivableTurnovergood = counter;
        Formulas.add(new Formula(AccountsReceivableTurnovergood,
                "good",
                ">",
                "6",
                "",
                "",
                "",
                AccountsReceivableTurnover));
        counter++;
        int AccountsReceivableTurnoversatisfactory = counter;
        Formulas.add(new Formula(AccountsReceivableTurnoversatisfactory,
                "unsatisfactory",
                "<=",
                "6",
                "",
                "",
                "",
                AccountsReceivableTurnover));
        return Formulas;
    }
}
