package finalon.defaultData.Formula.Turnover;

import finalon.defaultData.EvaluationTypes;
import finalon.entities.Formula;
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
                EvaluationTypes.EVALUATE_END.toString(),
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
                EvaluationTypes.EVALUATE_END.toString(),
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
