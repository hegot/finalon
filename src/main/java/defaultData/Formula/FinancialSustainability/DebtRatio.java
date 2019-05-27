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
                EvaluationTypes.EVALUATE_END_ONLY.toString(),
                "formula",
                "",
                parent));
        counter++;
        int DebtRatioexcellent = counter;
        Formulas.add(new Formula(DebtRatioexcellent,
                "excellent",
                "<=",
                "0.4",
                "The debt ratio was low at the end of the period, meaning that there was a small financial and credit risk.",
                "",
                "",
                DebtRatio));
        counter++;
        int DebtRatiogood = counter;
        Formulas.add(new Formula(DebtRatiogood,
                "good", "<",
                "0.4",
                "The debt ratio lies in the area of critical values (from 0.4 to 0.6) at the end of the period, meaning that there was an acceptable financial and credit risk. ",
                "<=",
                "0.6",
                DebtRatio));
        counter++;
        int DebtRatiobad = counter;
        Formulas.add(new Formula(DebtRatiobad,
                "bad",
                ">",
                "0.6",
                "The debt ratio was in the 'distress' zone (above 0.6) at the end of the period, meaning that there was a high financial and credit risk.",
                "",
                "",
                DebtRatio));
        return Formulas;
    }
}
