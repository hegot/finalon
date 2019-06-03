package defaultData.Formula.ProfitabilityAndPerformance;

import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OperatingIncomeMargin {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int OperatingIncomeMargin = counter;
        Formulas.add(new Formula(OperatingIncomeMargin,
                "Operating Income Margin",
                "OperatingIncomeMargin",
                "ProfitLossFromOperatingActivities/RevenueGeneral",
                EvaluationTypes.EVALUATE_END_ONLY.toString(),
                "formula",
                "%",
                parent));
        counter++;
        int OperatingIncomeMarginGood = counter;
        Formulas.add(new Formula(OperatingIncomeMarginGood,
                "good",
                ">",
                "0",
                "The COMPANYNAME's operating performance was robust in ENDDATE. For every CURRENCY of the net sales the company earned LASTVALUE in the operating income.",
                "",
                "",
                OperatingIncomeMargin));
        counter++;
        int OperatingIncomeMarginBad = counter;
        Formulas.add(new Formula(OperatingIncomeMarginBad,
                "bad",
                "<=",
                "0",
                "The COMPANYNAME's operating performance was weak in ENDDATE. For every CURRENCY of the net sales the company had LASTVALUE in the operating loss.",
                "",
                "",
                OperatingIncomeMargin));
        counter++;
        return Formulas;
    }
}
