package defaultData.Formula.ProfitabilityAndPerformance;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReturnonInvestment {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int ReturnonInvestment = counter;
        Formulas.add(new Formula(ReturnonInvestment,
                "Return on Investment",
                "ReturnonInvestment",
                "(ProfitLossBeforeTax-IncomeTaxExpenseContinuingOperations+FinanceCosts)/(EquityGeneral[0]/2+EquityGeneral[1]/2+NonCurrentAssets[0]/2+NonCurrentAssets[1]/2)",
                "",
                "formula",
                "%",
                parent));
        counter++;
        int ReturnonInvestmentexcellent = counter;
        Formulas.add(new Formula(ReturnonInvestmentexcellent,
                "excellent",
                ">",
                "4",
                "",
                "",
                "",
                ReturnonInvestment));
        counter++;
        int ReturnonInvestmentgood = counter;
        Formulas.add(new Formula(ReturnonInvestmentgood,
                "good",
                ">",
                "3,5",
                "",
                "",
                "",
                ReturnonInvestment));
        counter++;
        int ReturnonInvestmentsatisfactory = counter;
        Formulas.add(new Formula(ReturnonInvestmentsatisfactory,
                "satisfactory",
                ">",
                "0",
                "",
                "",
                "%",
                ReturnonInvestment));
        counter++;
        int ReturnonInvestmentunsatisfactory = counter;
        Formulas.add(new Formula(ReturnonInvestmentunsatisfactory,
                "unsatisfactory",
                "<=",
                "0",
                "",
                "",
                "%",
                ReturnonInvestment));
        return Formulas;
    }
}
