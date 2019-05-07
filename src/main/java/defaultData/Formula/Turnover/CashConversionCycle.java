package defaultData.Formula.Turnover;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CashConversionCycle {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int CashConversionCycle = counter;
        Formulas.add(new Formula(CashConversionCycle,
                "Cash Conversion Cycle",
                "CashConversionCycle",
                "360*((Inventories[1]+Inventories[0])/2)/CostOfSales+360*((TradeAndOtherCurrentReceivables[0]+TradeAndOtherCurrentReceivables[1])/2)/RevenueGeneral-(((TradeAndOtherCurrentPayables[0]+TradeAndOtherCurrentPayables[1])/2)*360)/CostOfSales",
                "",
                "formula",
                "days",
                parent));
        counter++;
        int CashConversionCyclegood = counter;
        Formulas.add(new Formula(CashConversionCyclegood,
                "good",
                "<",
                "60",
                "",
                "",
                "",
                CashConversionCycle));
        counter++;
        int CashConversionCyclesatisfactory = counter;
        Formulas.add(new Formula(CashConversionCyclesatisfactory,
                "unsatisfactory",
                ">=",
                "60",
                "",
                "",
                "",
                CashConversionCycle));
        return Formulas;
    }
}
