package defaultData.Formula.Other;

import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LaborProductivity {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int LaborProductivity = counter;
        Formulas.add(new Formula(LaborProductivity,
                "Labor productivity",
                "LaborProductivity",
                "RevenueGeneral/NumberOfEmployees",
                "",
                "formula",
                "money per person",
                parent));
        counter++;
        int LaborProductivityIncrease = counter;
        Formulas.add(new Formula(LaborProductivityIncrease,
                EvaluationTypes.PERIOD_COMPARISON_INCREASE.toString(),
                "",
                "",
                "The labor productivity in COMPANYNAME improved from STARTDATE to ENDDATE by CHANGEPERCENT %.  ",
                "",
                "",
                LaborProductivity));
        counter++;
        int LaborProductivityDecrease = counter;
        Formulas.add(new Formula(LaborProductivityDecrease,
                EvaluationTypes.PERIOD_COMPARISON_DECREASE.toString(),
                "",
                "",
                "The labor productivity in COMPANYNAME improved from STARTDATE to ENDDATE by CHANGEPERCENT %. ",
                "",
                "",
                LaborProductivity));
        counter++;
        int LaborProductivityNochange = counter;
        Formulas.add(new Formula(LaborProductivityNochange,
                EvaluationTypes.PERIOD_COMPARISON_NOCHANGE.toString(),
                "",
                "",
                "The labor productivity in COMPANYNAME was equal in STARTDATE and ENDDATE and amounted LASTVALUE. ",
                "",
                "",
                LaborProductivity));
        return Formulas;
    }
}
