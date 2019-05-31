package defaultData.Formula.Liquidity;

import defaultData.EvaluationTypes;
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
                EvaluationTypes.EVALUATE_END_ONLY.toString(),
                "formula",
                "",
                parent));
        counter++;
        int SalestoNetWorkingCapitalexcellent = counter;
        Formulas.add(new Formula(SalestoNetWorkingCapitalexcellent,
                "excellent",
                "<",
                "3",
                "Sales to Net Working Capital presented excellent results in ENDDATE. ",
                "<",
                "3",
                SalestoNetWorkingCapital));
        counter++;
        int SalestoNetWorkingCapitalsatisfactory = counter;
        Formulas.add(new Formula(SalestoNetWorkingCapitalsatisfactory,
                "satisfactory",
                "<",
                "3",
                "Sales to Net Working Capital was satisfactory in ENDDATE. ",
                "",
                "",
                SalestoNetWorkingCapital));
        counter++;
        int SalestoNetWorkingCapitalunsatisfactory = counter;
        Formulas.add(new Formula(SalestoNetWorkingCapitalunsatisfactory,
                "unsatisfactory",
                "<=",
                "0",
                "Sales to Net Working Capital was unsatisfactory (less than 0) in ENDDATE. ",
                "",
                "",
                SalestoNetWorkingCapital));
        counter++;
        int NetWorkingCapitalIncrease = counter;
        Formulas.add(new Formula(NetWorkingCapitalIncrease,
                EvaluationTypes.PERIOD_COMPARISON_INCREASE.toString(),
                "",
                "",
                "This index increased from STARTDATE to ENDDATE. " +
                        "This indicates a more profitable use of the working capital in ENDDATE in relation to STARTDATE. ",
                "",
                "",
                SalestoNetWorkingCapital));
        counter++;
        int NetWorkingCapitalDecrease = counter;
        Formulas.add(new Formula(NetWorkingCapitalDecrease,
                EvaluationTypes.PERIOD_COMPARISON_DECREASE.toString(),
                "",
                "",
                "This index decreased from STARTDATE to ENDDATE. " +
                        "This indicates a less profitable use of the working capital in ENDDATE in relation to STARTDATE. ",
                "",
                "",
                SalestoNetWorkingCapital));
        counter++;
        return Formulas;
    }
}
