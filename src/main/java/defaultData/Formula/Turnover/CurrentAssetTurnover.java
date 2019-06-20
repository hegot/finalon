package defaultData.Formula.Turnover;

import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CurrentAssetTurnover {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int CurrentAssetTurnover = counter;
        Formulas.add(new Formula(CurrentAssetTurnover,
                "Current Asset Turnover",
                "CurrentAssetTurnover",
                "RevenueGeneral/((GeneralCurrentAssets[0]+GeneralCurrentAssets[1])/2)",
                "",
                "formula",
                "times",
                parent));
        counter++;
        int CurrentAssetTurnovergood = counter;
        Formulas.add(new Formula(CurrentAssetTurnovergood,
                EvaluationTypes.EVALUATE_END.toString(),
                ">",
                "10",
                "",
                "",
                "",
                CurrentAssetTurnover));
        counter++;
        int CurrentAssetTurnoverunsatisfactory = counter;
        Formulas.add(new Formula(CurrentAssetTurnoverunsatisfactory,
                EvaluationTypes.EVALUATE_END.toString(),
                "<=",
                "10",
                "",
                "",
                "times",
                CurrentAssetTurnover));

        return Formulas;
    }
}
