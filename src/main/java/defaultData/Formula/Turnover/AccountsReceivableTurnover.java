package defaultData.Formula.Turnover;

import defaultData.EvaluationTypes;
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
                EvaluationTypes.EVALUATE_END_ONLY.toString(),
                "formula",
                "times",
                parent));
        counter++;
        int AccountsReceivableTurnovergood = counter;
        Formulas.add(new Formula(AccountsReceivableTurnovergood,
                "good",
                ">",
                "6",
                "Accounts Receivable Turnover comes into range 'good' ( > 6) in ENDDATE. " +
                        "Each CURRENCY invested in the accounts receivable generated CURRENCY LASTVALUE in sales in ENDDATE. ",
                "",
                "",
                AccountsReceivableTurnover));
        counter++;
        int AccountsReceivableTurnoversatisfactory = counter;
        Formulas.add(new Formula(AccountsReceivableTurnoversatisfactory,
                "unsatisfactory",
                "<=",
                "6",
                "Accounts Receivable Turnover comes into range 'unsatisfactory' ( <= 6) in ENDDATE. " +
                        "Each CURRENCY invested in the accounts receivable generated CURRENCY LASTVALUE in sales in ENDDATE. ",
                "",
                "",
                AccountsReceivableTurnover));
        counter++;
        int AccountsReceivableTurnoverPrefix = counter;
        Formulas.add(new Formula(AccountsReceivableTurnoverPrefix,
                EvaluationTypes.PREFIX.toString(),
                "",
                "",
                "The AFTERSTART accounts receivable turnover of STARTVALUE indicates COMPANYNAME " +
                        "collected its average receivables STARTVALUE times that period. " +
                        "The higher the turnover is, the faster the collection process. ",
                "",
                "",
                AccountsReceivableTurnover));
        return Formulas;
    }
}
