package defaultData.Formula.InvestmentAnalysis;

import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DegreeOfFinancialLeverage {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int DegreeOfFinancialLeverage = counter;
        Formulas.add(new Formula(DegreeOfFinancialLeverage,
                "Degree of Financial Leverage",
                "DegreeOfFinancialLeverage",
                "(ProfitLossBeforeTax-FinanceCosts)/ProfitLossBeforeTax",
                "",
                "formula",
                "money",
                parent));
        counter++;
        int NetAssetsNull = counter;
        Formulas.add(new Formula(NetAssetsNull,
                EvaluationTypes.EVALUATE_END.toString(),
                "=",
                "1",
                "The degree of financial leverage equal to 1 in ENDDATE " +
                        "means that any change in the EBIT will be accompanied by the same change in the net income. ",
                "",
                "",
                DegreeOfFinancialLeverage));
        return Formulas;
    }
}
