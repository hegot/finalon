package defaultData.Formula.ProfitabilityAndPerformance;

import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NetProfitMargin {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int NetProfitMargin = counter;
        Formulas.add(new Formula(NetProfitMargin,
                "Net Profit Margin",
                "NetProfitMargin",
                "(ProfitLossBeforeTax-IncomeTaxExpenseContinuingOperations)/RevenueGeneral",
                EvaluationTypes.EACH_PERIOD_MULTIVARIATE.toString(),
                "formula",
                "%",
                parent));
        counter++;
        int NetProfitMarginVariant1 = counter;
        Formulas.add(new Formula(NetProfitMarginVariant1,
                EvaluationTypes.EACH_PERIOD_MULTIVARIATE.toString(),
                "",
                "",
                "At the end of CURRENTPERIOD for every dollar of the net sales collected COMPANYNAME kept CURRENCY CURRENTVALUE as profit. ",
                "",
                "",
                NetProfitMargin));
        counter++;
        int NetProfitMarginVariant2 = counter;
        Formulas.add(new Formula(NetProfitMarginVariant2,
                EvaluationTypes.EACH_PERIOD_MULTIVARIATE.toString(),
                "",
                "",
                "In CURRENTPERIOD the net profit margin was CURRENTVALUE (CURRENTVALUEPERCENT%). ",
                "",
                "",
                NetProfitMargin));
        counter++;
        int NetProfitMarginVariant3 = counter;
        Formulas.add(new Formula(NetProfitMarginVariant3,
                EvaluationTypes.EACH_PERIOD_MULTIVARIATE.toString(),
                "",
                "",
                "This amount reached CURRENTVALUE (CURRENTVALUEPERCENT%) in CURRENTPERIOD. ",
                "",
                "",
                NetProfitMargin));
        counter++;
        int NetProfitMarginWasPositive = counter;
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
        counter++;
        return Formulas;
    }
}
