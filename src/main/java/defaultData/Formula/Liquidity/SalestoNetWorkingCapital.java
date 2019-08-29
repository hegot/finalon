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
                "",
                "formula",
                "",
                parent));
        counter++;
        int SalestoNetWorkingCapitalexcellent = counter;
        Formulas.add(new Formula(SalestoNetWorkingCapitalexcellent,
                EvaluationTypes.EVALUATE_END.toString(),
                ">",
                "3",
                "Sales to Net Working Capital presented excellent results in ENDDATE. ",
                "",
                "",
                SalestoNetWorkingCapital));
        counter++;
        int SalestoNetWorkingCapitalsatisfactory = counter;
        Formulas.add(new Formula(SalestoNetWorkingCapitalsatisfactory,
                EvaluationTypes.EVALUATE_END.toString(),
                ">",
                "0",
                "Sales to Net Working Capital was satisfactory in ENDDATE. ",
                "<=",
                "3",
                SalestoNetWorkingCapital));
        counter++;
        int SalestoNetWorkingCapitalunsatisfactory = counter;
        Formulas.add(new Formula(SalestoNetWorkingCapitalunsatisfactory,
                EvaluationTypes.EVALUATE_END.toString(),
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
                "This index increased by CHANGE from STARTDATE to ENDDATE. " +
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
                "This index decreased by CHANGE from STARTDATE to ENDDATE. " +
                        "This indicates a less profitable use of the working capital in ENDDATE in relation to STARTDATE. ",
                "",
                "",
                SalestoNetWorkingCapital));
        counter++;
        return Formulas;
    }
}
