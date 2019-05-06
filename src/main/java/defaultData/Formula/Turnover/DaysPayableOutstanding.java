package defaultData.Formula.Turnover;

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
        return Formulas;
    }
}
