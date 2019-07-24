package defaultData.Formula.ProfitabilityAndPerformance;

import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReturnonEquityafterTax {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int ReturnonEquityafterTax = counter;
        Formulas.add(new Formula(ReturnonEquityafterTax,
                "Return on Equity after Tax",
                "ReturnonEquityafterTax",
                "(ProfitLossBeforeTax-IncomeTaxExpenseContinuingOperations)/(EquityGeneral[0]/2+EquityGeneral[1]/2)",
                EvaluationTypes.EVALUATE_END.toString(),
                "formula",
                "%",
                parent));
        counter++;
        int ReturnonEquityafterTaxexcellent = counter;
        Formulas.add(new Formula(ReturnonEquityafterTaxexcellent,
                EvaluationTypes.EVALUATE_END.toString(),
                ">",
                "7",
                "The ROE value had excellent results (> 7) at the end of the ENDDATE. " +
                        "It shows that the company  was earning a profit of about LASTVALUE cents " +
                        "per CURRENCY of investment by stockholders. ",
                "",
                "",
                ReturnonEquityafterTax));
        counter++;
        int ReturnonEquityafterTaxgood = counter;
        Formulas.add(new Formula(ReturnonEquityafterTaxgood,
                EvaluationTypes.EVALUATE_END.toString(),
                ">",
                "4",
                "The ROE value had good results (> 4) at the end of the ENDDATE. " +
                        "It shows that the company  was earning a profit of about LASTVALUE cents " +
                        "per CURRENCY of investment by stockholders. ",
                "<=",
                "7",
                ReturnonEquityafterTax));
        counter++;
        int ReturnonEquityafterTaxsatisfactory = counter;
        Formulas.add(new Formula(ReturnonEquityafterTaxsatisfactory,
                EvaluationTypes.EVALUATE_END.toString(),
                ">",
                "0",
                "The ROE value had satisfactory results (> 0) at the end of the ENDDATE. " +
                        "It shows that the company  was earning a profit of about LASTVALUE cents " +
                        "per CURRENCY of investment by stockholders",
                "<=",
                "4",
                ReturnonEquityafterTax));
        counter++;
        int ReturnonEquityafterTaxNull = counter;
        Formulas.add(new Formula(ReturnonEquityafterTaxNull,
                EvaluationTypes.EVALUATE_END.toString(),
                "=",
                "0",
                "The ROE value was unsatisfactory (= 0) at the end of the ENDDATE. " +
                        "It shows that the company was having no loss and no profit " +
                        "per CURRENCY of investment by stockholders",
                "",
                "",
                ReturnonEquityafterTax));
        counter++;
        int ReturnonEquityafterTaxunsatisfactory = counter;
        Formulas.add(new Formula(ReturnonEquityafterTaxunsatisfactory,
                EvaluationTypes.EVALUATE_END.toString(),
                "<",
                "0",
                "The ROE value was unsatisfactory (< 0) at the end of the ENDDATE. " +
                        "It shows that the company was having loss of about LASTVALUE cents " +
                        "per CURRENCY of investment by stockholders",
                "",
                "",
                ReturnonEquityafterTax));
        counter++;
        int ReturnonEquityafterTaxPositive = counter;
        Formulas.add(new Formula(ReturnonEquityafterTaxPositive,
                EvaluationTypes.PERIOD_COMPARISON_INCREASE.toString(),
                "",
                "",
                "The ROE represents the increase in the value of the stockholders' wealth during STARTDATE-ENDDATE. ",
                "",
                "",
                ReturnonEquityafterTax));
        counter++;
        int ReturnonEquityafterTaxNegative = counter;
        Formulas.add(new Formula(ReturnonEquityafterTaxNegative,
                EvaluationTypes.PERIOD_COMPARISON_DECREASE.toString(),
                "",
                "",
                "The ROE represents the decrease in the value of the stockholders' wealth during STARTDATE-ENDDATE. ",
                "",
                "",
                ReturnonEquityafterTax));
        return Formulas;
    }
}
