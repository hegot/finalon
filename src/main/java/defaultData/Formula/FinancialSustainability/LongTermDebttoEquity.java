package defaultData.Formula.FinancialSustainability;

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
                "",
                "formula",
                "",
                parent));
        counter++;
        int LongTermDebttoEquityexcellent = counter;
        Formulas.add(new Formula(LongTermDebttoEquityexcellent,
                "excellent",
                ">=",
                "1",
                "",
                "",
                "",
                LongTermDebttoEquity));
        counter++;
        int LongTermDebttoEquitysatisfactory = counter;
        Formulas.add(new Formula(LongTermDebttoEquitysatisfactory,
                "satisfactory",
                "<=",
                "0.5",
                "",
                "<",
                "1",
                LongTermDebttoEquity));
        counter++;
        int LongTermDebttoEquitybad = counter;
        Formulas.add(new Formula(LongTermDebttoEquitybad,
                "bad",
                "<",
                "0.5",
                "",
                "",
                "",
                LongTermDebttoEquity));
        return Formulas;
    }
}
