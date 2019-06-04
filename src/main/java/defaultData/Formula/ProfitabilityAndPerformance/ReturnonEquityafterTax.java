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
                EvaluationTypes.EVALUATE_END_ONLY.toString(),
                "formula",
                "%",
                parent));
        counter++;
        int ReturnonEquityafterTaxexcellent = counter;
        Formulas.add(new Formula(ReturnonEquityafterTaxexcellent,
                "excellent",
                ">",
                "7",
                "The ROE value had excellent results (> 7) at the end of the ENDDATE. ",
                "",
                "",
                ReturnonEquityafterTax));
        counter++;
        int ReturnonEquityafterTaxgood = counter;
        Formulas.add(new Formula(ReturnonEquityafterTaxgood,
                "good",
                ">",
                "4",
                "The ROE value had good results (> 4) at the end of the ENDDATE. ",
                "",
                "",
                ReturnonEquityafterTax));
        counter++;
        int ReturnonEquityafterTaxsatisfactory = counter;
        Formulas.add(new Formula(ReturnonEquityafterTaxsatisfactory,
                "satisfactory",
                ">",
                "0",
                "The ROE value had satisfactory results (> 0) at the end of the ENDDATE. ",
                "",
                "",
                ReturnonEquityafterTax));
        counter++;
        int ReturnonEquityafterTaxunsatisfactory = counter;
        Formulas.add(new Formula(ReturnonEquityafterTaxunsatisfactory,
                "unsatisfactory",
                "<=",
                "0",
                "The ROE value was unsatisfactory (<= 0) at the end of the ENDDATE. ",
                "",
                "%",
                ReturnonEquityafterTax));
        counter++;
        int ReturnonEquityafterTaxPositive = counter;
        Formulas.add(new Formula(ReturnonEquityafterTaxPositive,
                EvaluationTypes.EACH_PERIOD_TRUE.toString(),
                ">",
                "0",
                "The ROE represents the increase in the value of the stockholders' wealth during STARTDATE-ENDDATE. ",
                "",
                "",
                ReturnonEquityafterTax));
        counter++;
        int ReturnonEquityafterTaxNegative = counter;
        Formulas.add(new Formula(ReturnonEquityafterTaxNegative,
                EvaluationTypes.EACH_PERIOD_TRUE.toString(),
                "<",
                "0",
                "The ROE represents the decrease in the value of the stockholders' wealth during STARTDATE-ENDDATE. ",
                "",
                "",
                ReturnonEquityafterTax));
        return Formulas;
    }
}
