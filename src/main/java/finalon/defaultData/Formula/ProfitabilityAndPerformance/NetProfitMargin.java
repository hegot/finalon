package finalon.defaultData.Formula.ProfitabilityAndPerformance;

import finalon.defaultData.EvaluationTypes;
import finalon.entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NetProfitMargin {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int NetProfitMargin = counter;
        Formulas.add(new Formula(NetProfitMargin,
                "Net Profit Margin",
                "NetProfitMargin",
                "((ProfitLossBeforeTax-IncomeTaxExpenseContinuingOperations)/RevenueGeneral)*100",
                "",
                "formula",
                "%",
                parent));
        counter++;
        int NetProfitMarginVariant1 = counter;
        Formulas.add(new Formula(NetProfitMarginVariant1,
                EvaluationTypes.PREFIX.toString(),
                "",
                "",
                "At the end of ENDDATE for every dollar of the net sales collected COMPANYNAME kept CURRENCY LASTVALUE as profit. ",
                "",
                "",
                NetProfitMargin));
        counter++;
        /*int NetProfitMarginWasPositive = counter;
        Formulas.add(new Formula(NetProfitMarginWasPositive,
                EvaluationTypes.EACH_PERIOD_TRUE.toString(),
                ">",
                "0",
                "By looking at Table 8. 'Profitability Ratios' it can be seen that the net profit margin was positive in STARTDATE-ENDDATE. ",
                "",
                "",
                NetProfitMargin));
        counter++;
        int NetProfitMarginWasNegative = counter;
        Formulas.add(new Formula(NetProfitMarginWasNegative,
                EvaluationTypes.EACH_PERIOD_TRUE.toString(),
                "<",
                "0",
                "By looking at Table 8. 'Profitability Ratios' it can be seen that the net profit margin was negative in STARTDATE-ENDDATE. ",
                "",
                "",
                NetProfitMargin));
        counter++;*/
        int NetProfitMarginIncrease = counter;
        Formulas.add(new Formula(NetProfitMarginIncrease,
                EvaluationTypes.PERIOD_COMPARISON_INCREASE.toString(),
                "",
                "",
                "By looking at Table 8. 'Profitability Ratios' it can be seen that the net profit margin increased in ENDDATE comparing to STARTDATE. ",
                "",
                "",
                NetProfitMargin));
        counter++;
        int NetProfitMarginDecrease = counter;
        Formulas.add(new Formula(NetProfitMarginDecrease,
                EvaluationTypes.PERIOD_COMPARISON_DECREASE.toString(),
                "",
                "",
                "By looking at Table 8. 'Profitability Ratios' it can be seen that the net profit margin decreased in ENDDATE comparing to STARTDATE. ",
                "",
                "",
                NetProfitMargin));


        return Formulas;
    }
}
