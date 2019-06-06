package reportGeneration.interpreter.TurnoverRatios.Outcomes;

import entities.Formula;
import reportGeneration.storage.FormulaStorage;

public class RecivablePayableAccountsComparison {

    private Formula accountPayable;
    private Formula accountRecivable;

    public RecivablePayableAccountsComparison() {
        FormulaStorage storage = FormulaStorage.getInstance();
        this.accountPayable = storage.getItemByCode("AccountsPayableTurnover");
        this.accountRecivable = storage.getItemByCode("AccountsReceivableTurnover");
    }

    public String getResult() {
        Double accountPayableLast = accountPayable.getLastVal();
        Double accountRecivableLast = accountRecivable.getLastVal();
        if(accountPayableLast != null && accountRecivableLast != null){
            if (accountPayableLast > accountRecivableLast) {
                return "By comparing the turnover data of the accounts receivable and accounts payable during " +
                        "ENDDATE it can be seen the accounts receivable were turned over faster than the accounts payable.";
            }
            if (accountPayableLast < accountRecivableLast) {
                return "By comparing the turnover data of the accounts receivable and accounts payable during " +
                        "ENDDATE it can be seen the accounts receivable were turned over slower than the accounts payable.";
            }
            return "By comparing the turnover data of the accounts receivable and accounts payable during " +
                    "ENDDATE it can be seen the accounts receivable turned over as fast as the accounts payable.";
        }
       return "";
    }

}
