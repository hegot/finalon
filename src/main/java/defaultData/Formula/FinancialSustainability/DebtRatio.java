package defaultData.Formula.FinancialSustainability;

import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DebtRatio {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int DebtRatio = counter;
        Formulas.add(new Formula(DebtRatio,
                "Debt Ratio",
                "DebtRatio",
                "(NonCurrentAssets+CurrentLiabilities)/AssetsGeneral",
                "",
                "formula",
                "",
                parent));
        counter++;
        int DebtRatioPrefix = counter;
        Formulas.add(new Formula(DebtRatioPrefix,
                EvaluationTypes.PREFIX.toString(),
                "",
                "",
                "The debt ratio tells us that in STARTDATE STARTVALUE of the sources of finance were liabilities. ",
                "",
                "",
                DebtRatio));
        int DebtRatioexcellent = counter;
        Formulas.add(new Formula(DebtRatioexcellent,
                EvaluationTypes.EVALUATE_PRE_END.toString(),
                ">",
                "0",
                "For every CURRENCY 1.00 of the assets there were PRE_END_VALUE of liabilities in PRE_END_DATE. ",
                "",
                "",
                DebtRatio));
        counter++;
        int DebtRatioexcellentEnd = counter;
        Formulas.add(new Formula(DebtRatioexcellentEnd,
                EvaluationTypes.EVALUATE_END.toString(),
                "<=",
                "0.4",
                "The debt ratio was low at the end of the period, meaning that there was a small financial and credit risk.",
                "",
                "",
                DebtRatio));
        counter++;
        int DebtRatiogoodEnd = counter;
        Formulas.add(new Formula(DebtRatiogoodEnd,
                EvaluationTypes.EVALUATE_END.toString(),
                "<=",
                "0.6",
                "The debt ratio lies in the area of critical values (from 0.4 to 0.6) at the end of the period, meaning that there was an acceptable financial and credit risk. ",
                ">",
                "0.4",
                DebtRatio));
        counter++;
        int DebtRatiobadEnd = counter;
        Formulas.add(new Formula(DebtRatiobadEnd,
                EvaluationTypes.EVALUATE_END.toString(),
                ">",
                "0.6",
                "The debt ratio was in the 'distress' zone (above 0.6) at the end of the period, meaning that there was a high financial and credit risk.",
                "",
                "",
                DebtRatio));
        return Formulas;
    }
}
