package finalon.defaultData.Formula.ProfitabilityAndPerformance;

import finalon.defaultData.EvaluationTypes;
import finalon.entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GrossProfitMargin {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int GrossProfitMargin = counter;
        Formulas.add(new Formula(GrossProfitMargin,
                "Gross Profit Margin",
                "GrossProfitMargin",
                "(GrossProfit/RevenueGeneral)*100",
                "",
                "formula",
                "%",
                parent));
        counter++;
        int GrossProfitMargingood = counter;
        Formulas.add(new Formula(GrossProfitMargingood,
                EvaluationTypes.EVALUATE_END.toString(),
                ">",
                "0",
                "Gross profit margin shows that the company earned CURRENCY LASTVALUE of the gross profit per CURRENCY of sales in ENDDATE. ",
                "",
                "",
                GrossProfitMargin));
        counter++;
        int GrossProfitMarginBad = counter;
        Formulas.add(new Formula(GrossProfitMarginBad,
                EvaluationTypes.EVALUATE_END.toString(),
                "=",
                "0",
                "Gross profit margin was negatively affected by the high cost of goods sold. COMPANYNAME earned $0 of gross profit in ENDDATE.",
                "",
                "",
                GrossProfitMargin));
        counter++;
        int GrossProfitMarginunsatisfactory = counter;
        Formulas.add(new Formula(GrossProfitMarginunsatisfactory,
                EvaluationTypes.EVALUATE_END.toString(),
                "<",
                "0",
                "Gross profit margin was negatively affected by the high cost of goods sold. COMPANYNAME lost CURRENCY LASTVALUE of sales in ENDDATE. ",
                "",
                "",
                GrossProfitMargin));
        counter++;
        int GrossProfitMarginIncrease = counter;
        Formulas.add(new Formula(GrossProfitMarginIncrease,
                EvaluationTypes.PERIOD_COMPARISON_INCREASE.toString(),
                "",
                "",
                "Sales increased much more than the cost of goods sold, resulting in a higher gross margin in ENDDATE comparing to STARTDATE. ",
                "",
                "",
                GrossProfitMargin));
        counter++;
        int GrossProfitMarginDecrease = counter;
        Formulas.add(new Formula(GrossProfitMarginDecrease,
                EvaluationTypes.PERIOD_COMPARISON_DECREASE.toString(),
                "",
                "",
                "Cost of goods sold increased much more than sales, resulting in a lower gross margin in ENDDATE comparing to STARTDATE. ",
                "",
                "",
                GrossProfitMargin));
        return Formulas;
    }
}
