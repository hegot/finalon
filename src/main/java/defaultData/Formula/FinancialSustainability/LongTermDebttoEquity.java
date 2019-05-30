package defaultData.Formula.FinancialSustainability;

import defaultData.EvaluationTypes;
import entities.Formula;
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
                EvaluationTypes.EVALUATE_EACH_PERIOD.toString(),
                "formula",
                "",
                parent));
        counter++;
        int LongTermDebttoEquityexcellent = counter;
        Formulas.add(new Formula(LongTermDebttoEquityexcellent,
                "excellent",
                ">=",
                "1",
                "CURRENTVALUE in CURRENTPERIOD (excellent - more than 1), ",
                "",
                "",
                LongTermDebttoEquity));
        counter++;
        int LongTermDebttoEquitysatisfactory = counter;
        Formulas.add(new Formula(LongTermDebttoEquitysatisfactory,
                "satisfactory",
                "<=",
                "0.5",
                "CURRENTVALUE in CURRENTPERIOD (satisfactory - between 0.5 and 1), ",
                "<",
                "1",
                LongTermDebttoEquity));
        counter++;
        int LongTermDebttoEquitybad = counter;
        Formulas.add(new Formula(LongTermDebttoEquitybad,
                "bad",
                "<",
                "0.5",
                "CURRENTVALUE in CURRENTPERIOD (bad - less than 0.5), ",
                "",
                "",
                LongTermDebttoEquity));
        counter++;
        int LongTermDebttoEquityPrefix = counter;
        Formulas.add(new Formula(LongTermDebttoEquityPrefix,
                EvaluationTypes.PREFIX.toString(),
                "",
                "",
                "The value of the long-term debt to equity ratio was ",
                "",
                "",
                LongTermDebttoEquity));
        counter++;
        return Formulas;
    }
}
