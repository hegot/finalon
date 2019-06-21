package defaultData.Formula.Turnover;

import defaultData.EvaluationTypes;
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
                EvaluationTypes.EVALUATE_START_AND_END.toString(),
                "formula",
                "days",
                parent));
        counter++;
        int CashConversionCycleStart1 = counter;
        Formulas.add(new Formula(CashConversionCycleStart1,
                EvaluationTypes.EVALUATE_START.toString(),
                ">",
                "0",
                "According to the data it can be seen that the cash conversion cycle was CURRENTVALUE in CURRENTPERIOD. ",
                "",
                "",
                CashConversionCycle));
        counter++;
        int CashConversionCycleStart2 = counter;
        Formulas.add(new Formula(CashConversionCycleStart2,
                EvaluationTypes.EVALUATE_START.toString(),
                "<=",
                "0",
                "According to the data it can be seen that the cash conversion cycle was CURRENTVALUE in CURRENTPERIOD. " +
                        "The negative value of the index (CURRENTVALUE) in CURRENTPERIOD. " +
                        "demonstrates problems in paying off debt to suppliers in CURRENTPERIOD.",
                "",
                "",
                CashConversionCycle));
        counter++;
        int CashConversionCycleEnd1 = counter;
        Formulas.add(new Formula(CashConversionCycleEnd1,
                EvaluationTypes.EVALUATE_END.toString(),
                ">",
                "0",
                "Positive Cash conversion cycle (CURRENTVALUE) " +
                        "in CURRENTPERIOD demonstrates good ability paying off debt to suppliers. ",
                "",
                "",
                CashConversionCycle));
        counter++;
        int CashConversionCycleEnd2 = counter;
        Formulas.add(new Formula(CashConversionCycleEnd2,
                EvaluationTypes.EVALUATE_END.toString(),
                "<=",
                "0",
                "Negative Cash conversion cycle (CURRENTVALUE) " +
                        "in CURRENTPERIOD demonstrates poor ability paying off debt to suppliers.",
                "",
                "",
                CashConversionCycle));
        counter++;
        int OperatingCyclePrefix = counter;
        Formulas.add(new Formula(OperatingCyclePrefix,
                EvaluationTypes.PREFIX.toString(),
                "",
                "",
                "The cash conversion cycle shows how long does it take a company to " +
                        "convert the resource inputs into cash flows. ",
                "",
                "",
                CashConversionCycle));
        return Formulas;
    }
}
