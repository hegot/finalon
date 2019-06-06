package defaultData.Formula.Turnover;

import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DaysPayableOutstanding {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int DaysPayableOutstanding = counter;
        Formulas.add(new Formula(DaysPayableOutstanding,
                "Days Payable Outstanding",
                "DaysPayableOutstanding",
                "(((TradeAndOtherCurrentPayables[0]+TradeAndOtherCurrentPayables[1])/2)*360)/CostOfSales",
                "",
                "formula",
                "times",
                parent));
        counter++;
        int DaysPayableOutstandinggood = counter;
        Formulas.add(new Formula(DaysPayableOutstandinggood,
                "good",
                "<",
                "60",
                "",
                "",
                "",
                DaysPayableOutstanding));
        counter++;
        int DaysPayableOutstandingsatisfactory = counter;
        Formulas.add(new Formula(DaysPayableOutstandingsatisfactory,
                "unsatisfactory",
                ">=",
                "60",
                "",
                "",
                "times",
                DaysPayableOutstanding));
        counter++;
        int DaysPayableOutstandingIncrease = counter;
        Formulas.add(new Formula(DaysPayableOutstandingIncrease,
                EvaluationTypes.PERIOD_COMPARISON_INCREASE.toString(),
                "",
                "",
                "The turnover of the accounts payable was raised from STARTVALUE days to LASTVALUE days. ",
                "",
                "",
                DaysPayableOutstanding));
        counter++;
        int DaysPayableOutstandingDecrease = counter;
        Formulas.add(new Formula(DaysPayableOutstandingDecrease,
                EvaluationTypes.PERIOD_COMPARISON_DECREASE.toString(),
                "",
                "",
                "As a result, the average collection period dropped from STARTVALUE days to LASTVALUE days. ",
                "",
                "",
                DaysPayableOutstanding));
        return Formulas;
    }
}
