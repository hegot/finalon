package defaultData.Formula.Liquidity;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SalestoNetWorkingCapital {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int SalestoNetWorkingCapital = counter;
        Formulas.add(new Formula(SalestoNetWorkingCapital,
                "Sales to Net Working Capital",
                "SalestoNetWorkingCapital",
                "RevenueGeneral/((GeneralCurrentAssets[1]-CurrentLiabilities[1]+GeneralCurrentAssets[0]-CurrentLiabilities[0])/2)",
                "",
                "formula",
                "",
                parent));
        counter++;
        int SalestoNetWorkingCapitalexcellent = counter;
        Formulas.add(new Formula(SalestoNetWorkingCapitalexcellent,
                "excellent",
                "<",
                "3",
                "",
                "<",
                "3",
                SalestoNetWorkingCapital));
        counter++;
        int SalestoNetWorkingCapitalsatisfactory = counter;
        Formulas.add(new Formula(SalestoNetWorkingCapitalsatisfactory,
                "satisfactory",
                "<",
                "3",
                "",
                "",
                "",
                SalestoNetWorkingCapital));
        counter++;
        int SalestoNetWorkingCapitalunsatisfactory = counter;
        Formulas.add(new Formula(SalestoNetWorkingCapitalunsatisfactory,
                "unsatisfactory",
                "<=",
                "0",
                "",
                "",
                "",
                SalestoNetWorkingCapital));
        counter++;
        return Formulas;
    }
}
