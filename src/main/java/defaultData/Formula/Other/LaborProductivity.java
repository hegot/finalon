package defaultData.Formula.Other;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LaborProductivity {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        Formulas.add(new Formula(counter,
                "Labor productivity",
                "Laborproductivity",
                "RevenueGeneral/NumberOfEmployees",
                "",
                "formula",
                "money per person",
                parent));
        return Formulas;
    }
}
