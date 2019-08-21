package finalon.defaultData.Formula.FinancialSustainability;

import finalon.defaultData.EvaluationTypes;
import finalon.entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LongTermDebttoEquity {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int LongTermDebttoEquity = counter;
        Formulas.add(new Formula(LongTermDebttoEquity,
                "Long-Term Debt to Equity",
                "LongTermDebttoEquity",
                "NonCurrentAssets/(IssuedCapital+SharePremium)",
                "",
                "formula",
                "",
                parent));
        counter++;
        int LongTermDebttoEquityexcellent = counter;
        Formulas.add(new Formula(LongTermDebttoEquityexcellent,
                EvaluationTypes.EVALUATE_END.toString(),
                ">=",
                "1",
                "The value of the long-term debt to equity ratio was LASTVALUE in ENDDATE (excellent - more than 1), ",
                "",
                "",
                LongTermDebttoEquity));
        counter++;
        int LongTermDebttoEquitysatisfactory = counter;
        Formulas.add(new Formula(LongTermDebttoEquitysatisfactory,
                EvaluationTypes.EVALUATE_END.toString(),
                ">=",
                "0.5",
                "The value of the long-term debt to equity ratio was LASTVALUE in ENDDATE (satisfactory - between 0.5 and 1), ",
                "<",
                "1",
                LongTermDebttoEquity));
        counter++;
        int LongTermDebttoEquitybad = counter;
        Formulas.add(new Formula(LongTermDebttoEquitybad,
                EvaluationTypes.EVALUATE_END.toString(),
                "<",
                "0.5",
                "The value of the long-term debt to equity ratio was LASTVALUE in ENDDATE (bad - less than 0.5), ",
                "",
                "",
                LongTermDebttoEquity));
        return Formulas;
    }
}
