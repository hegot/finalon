package defaultData.Formula.Liquidity;

import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CashRatio {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int CashRatio = counter;
        Formulas.add(new Formula(CashRatio,
                "Cash Ratio",
                "CashRatio",
                "CashAndCashEquivalents/CurrentLiabilities",
                "",
                "formula",
                "",
                parent));
        counter++;
        int CashRatioexcellent = counter;
        Formulas.add(new Formula(CashRatioexcellent,
                EvaluationTypes.EVALUATE_END.toString(),
                ">=",
                "0.2",
                "The value of the ratio lies above the area of critical values.",
                "",
                "",
                CashRatio));
        counter++;
        int CashRatiogood = counter;
        Formulas.add(new Formula(CashRatiogood,
                EvaluationTypes.EVALUATE_END.toString(),
                ">=",
                "0.1",
                "The value of the ratio lies in the area of critical values.",
                "<",
                "0.2",
                CashRatio));
        counter++;
        int CashRatiobad = counter;
        Formulas.add(new Formula(CashRatiobad,
                EvaluationTypes.EVALUATE_END.toString(),
                "<",
                "0.1",
                "It indicates an immediate problem with paying bills.",
                "",
                "",
                CashRatio));
        counter++;
        int CashRatioPrefix = counter;
        Formulas.add(new Formula(CashRatioPrefix,
                EvaluationTypes.PREFIX.toString(),
                "",
                "",
                "The cash ratio shows that the company was able to pay off LASTVALUEPERCENT% of its debt immediately as for the end of ENDDATE. ",
                "",
                "",
                CashRatio));
        counter++;
        return Formulas;
    }
}
