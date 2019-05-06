package defaultData.Formula.Turnover;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AverageCollectionPeriod {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int AverageCollectionPeriod = counter;
        Formulas.add(new Formula(AverageCollectionPeriod,
                "Average Collection Period (Accounts Receivable Turnover in Days)",
                "AverageCollectionPeriod",
                "360*((TradeAndOtherCurrentReceivables[0]+TradeAndOtherCurrentReceivables[1])/2)/RevenueGeneral",
                "",
                "formula",
                "days",
                parent));
        counter++;
        int AverageCollectionPeriodgood = counter;
        Formulas.add(new Formula(AverageCollectionPeriodgood,
                "good",
                "<",
                "60",
                "",
                "",
                "",
                AverageCollectionPeriod));
        counter++;
        int AverageCollectionPeriodsatisfactory = counter;
        Formulas.add(new Formula(AverageCollectionPeriodsatisfactory,
                "unsatisfactory",
                ">=",
                "60",
                "",
                "",
                "",
                AverageCollectionPeriod));
        return Formulas;
    }
}
