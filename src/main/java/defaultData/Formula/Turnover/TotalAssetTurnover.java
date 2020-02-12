package defaultData.Formula.Turnover;

import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TotalAssetTurnover {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int TotalAssetTurnover = counter;
        Formulas.add(new Formula(TotalAssetTurnover,
                "Total Asset Turnover",
                "TotalAssetTurnover",
                "RevenueGeneral/(AssetsGeneral[0]/2+AssetsGeneral[1]/2)",
                "",
                "formula",
                "times",
                parent));
        counter++;
        int TotalAssetTurnovergood = counter;
        Formulas.add(new Formula(TotalAssetTurnovergood,
                EvaluationTypes.EVALUATE_END.toString(),
                ">",
                "6",
                "At the end of the period index demonstrated good results ( > 6 ). ",
                "",
                "",
                TotalAssetTurnover));
        counter++;
        int TotalAssetTurnoverunsatisfactory = counter;
        Formulas.add(new Formula(TotalAssetTurnoverunsatisfactory,
                EvaluationTypes.EVALUATE_END.toString(),
                "<=",
                "6",
                "At the end of the period index demonstrated unsatisfactory results ( <= 6 ). ",
                "",
                "",
                TotalAssetTurnover));
        counter++;
        int TotalAssetTurnoverPrefix = counter;
        Formulas.add(new Formula(TotalAssetTurnoverPrefix,
                EvaluationTypes.PREFIX.toString(),
                "",
                "",
                "By looking at Table 10 it can be seen that the company produced LASTVALUE CURRENCY of products and services for every CURRENCY of the assets used at the end of the period. ",
                "",
                "",
                TotalAssetTurnover));
        return Formulas;
    }
}
