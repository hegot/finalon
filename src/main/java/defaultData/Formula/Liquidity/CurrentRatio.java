package defaultData.Formula.Liquidity;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CurrentRatio {

    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int CurrentRatio = counter;
        Formulas.add(new Formula(CurrentRatio,
                "Current Ratio",
                "CurrentRatio",
                "GeneralCurrentAssets/CurrentLiabilities",
                "",
                "formula",
                "",
                parent));
        counter++;
        int CurrentRatiosatisfactory = counter;
        Formulas.add(new Formula(CurrentRatiosatisfactory,
                "excellent",
                ">",
                "3",
                "The value of the ratio lies high above the area of critical values (from 1.5 to 2).",
                "",
                "",
                CurrentRatio));
        counter++;
        int CurrentRatioexcellent = counter;
        Formulas.add(new Formula(CurrentRatioexcellent,
                "good",
                "<",
                "2",
                "The value of the ratio lies above the area of critical values (from 1.5 to 2).",
                "<=",
                "3",
                CurrentRatio));
        counter++;
        int CurrentRatiogood = counter;
        Formulas.add(new Formula(CurrentRatiogood,
                "normal",
                "<=",
                "1.5",
                "The value of the ratio lies in the area of critical values (from 1.5 to 2).",
                "<=",
                "2",
                CurrentRatio));
        counter++;
        int CurrentRatiobad = counter;
        Formulas.add(new Formula(CurrentRatiobad,
                "bad",
                "<",
                "1,5",
                "The value of the ratio lies below the area of critical values.",
                "",
                "",
                CurrentRatio));
        return Formulas;
    }
}
