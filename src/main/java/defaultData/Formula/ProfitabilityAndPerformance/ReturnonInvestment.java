package defaultData.Formula.ProfitabilityAndPerformance;

import defaultData.EvaluationTypes;
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
                EvaluationTypes.EVALUATE_END.toString(),
                ">",
                "4",
                "Higher ROIs suggest better performance. At the end of evaluation period index provided excellent results ( > 4 ). ",
                "",
                "",
                ReturnonInvestment));
        counter++;
        int ReturnonInvestmentgood = counter;
        Formulas.add(new Formula(ReturnonInvestmentgood,
                EvaluationTypes.EVALUATE_END.toString(),
                ">",
                "3,5",
                "Higher ROIs suggest better performance.  At the end of evaluation period index provided good results ( > 3.5 ). ",
                "",
                "",
                ReturnonInvestment));
        counter++;
        int ReturnonInvestmentsatisfactory = counter;
        Formulas.add(new Formula(ReturnonInvestmentsatisfactory,
                EvaluationTypes.EVALUATE_END.toString(),
                ">",
                "0",
                "Higher ROIs suggest better performance.  At the end of evaluation period index provided satisfactory results ( > 0 ). ",
                "",
                "%",
                ReturnonInvestment));
        counter++;
        int ReturnonInvestmentunsatisfactory = counter;
        Formulas.add(new Formula(ReturnonInvestmentunsatisfactory,
                EvaluationTypes.EVALUATE_END.toString(),
                "<=",
                "0",
                "Higher ROIs suggest better performance.  At the end of evaluation period index was unsatisfactory ( <= 0 ). ",
                "",
                "%",
                ReturnonInvestment));
        counter++;
        int ReturnonInvestmentIncrease = counter;
        Formulas.add(new Formula(ReturnonInvestmentIncrease,
                EvaluationTypes.PERIOD_COMPARISON_INCREASE.toString(),
                "",
                "",
                "Overall, ROI improved from STARTVALUEPERCENT% in STARTDATE to LASTVALUEPERCENT% in ENDDATE. ",
                "",
                "",
                ReturnonInvestment));
        counter++;
        int ReturnonInvestmentDecrease = counter;
        Formulas.add(new Formula(ReturnonInvestmentDecrease,
                EvaluationTypes.PERIOD_COMPARISON_DECREASE.toString(),
                "",
                "",
                "Overall, ROI declined from STARTVALUEPERCENT% in STARTDATE to LASTVALUEPERCENT% in ENDDATE. ",
                "",
                "",
                ReturnonInvestment));
        return Formulas;
    }
}
