package defaultData.Formula.ProfitabilityAndPerformance;

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
                "",
                "formula",
                "%",
                parent));
        counter++;
        int ReturnonEquityafterTaxexcellent = counter;
        Formulas.add(new Formula(ReturnonEquityafterTaxexcellent,
                "excellent",
                ">",
                "7",
                "",
                "",
                "",
                ReturnonEquityafterTax));
        counter++;
        int ReturnonEquityafterTaxgood = counter;
        Formulas.add(new Formula(ReturnonEquityafterTaxgood,
                "good",
                ">",
                "4",
                "",
                "",
                "",
                ReturnonEquityafterTax));
        counter++;
        int ReturnonEquityafterTaxsatisfactory = counter;
        Formulas.add(new Formula(ReturnonEquityafterTaxsatisfactory,
                "satisfactory",
                ">",
                "0",
                "",
                "",
                "",
                ReturnonEquityafterTax));
        counter++;
        int ReturnonEquityafterTaxunsatisfactory = counter;
        Formulas.add(new Formula(ReturnonEquityafterTaxunsatisfactory,
                "unsatisfactory",
                "<=",
                "0",
                "",
                "",
                "%",
                ReturnonEquityafterTax));
        return Formulas;
    }
}
