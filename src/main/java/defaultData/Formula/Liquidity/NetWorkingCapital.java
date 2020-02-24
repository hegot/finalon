package defaultData.Formula.Liquidity;

import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NetWorkingCapital {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int NetWorkingCapital = counter;
        Formulas.add(new Formula(NetWorkingCapital,
                "Net Working Capital",
                "NetWorkingCapital",
                "GeneralCurrentAssets-CurrentLiabilities",
                "",
                "formula",
                "money",
                parent));
        counter++;
        int NetWorkingCapitalExcellent = counter;
        Formulas.add(new Formula(NetWorkingCapitalExcellent,
                EvaluationTypes.EVALUATE_END.toString(),
                ">",
                "0",
                "There were CURRENCY LASTVALUE AMOUNT in the working capital in ENDDATE. " +
                        "The working capital value was positive at the end of the period under review, " +
                        "meaning greater flexibility, since current assets may be modified easily as the sales volume changes. ",
                "",
                "",
                NetWorkingCapital));
        counter++;
        int NetWorkingCapitalSatisfactory = counter;
        Formulas.add(new Formula(NetWorkingCapitalSatisfactory,
                EvaluationTypes.EVALUATE_END.toString(),
                "=",
                "0",
                "There was no working capital at the end of the period under review, " +
                        "meaning low flexibility, since non-current assets may not be modified easily as the sales volume changes. ",
                "",
                "",
                NetWorkingCapital));
        counter++;
        int NetWorkingCapitalBad = counter;
        Formulas.add(new Formula(NetWorkingCapitalBad,
                EvaluationTypes.EVALUATE_END.toString(),
                "<",
                "0",
                "There were CURRENCY LASTVALUE AMOUNT in the working capital in ENDDATE. " +
                        "The working capital value was negative at the end of the period under review, " +
                        "meaning low flexibility, since non-current assets may not be modified easily as the sales volume changes. ",
                "",
                "",
                NetWorkingCapital));
        counter++;
        int NetWorkingCapitalPrefix = counter;
        Formulas.add(new Formula(NetWorkingCapitalPrefix,
                EvaluationTypes.PREFIX.toString(),
                "",
                "",
                "COMPANYNAME had CURRENCY STARTVALUE AMOUNT in the working capital in STARTDATE. ",
                "",
                "",
                NetWorkingCapital));
        counter++;
        int NetWorkingCapitalIncrease = counter;
        Formulas.add(new Formula(NetWorkingCapitalIncrease,
                EvaluationTypes.PERIOD_COMPARISON_INCREASE.toString(),
                "",
                "",
                "Overall, the value of the working capital had increased over STARTDATE-ENDDATE. ",
                "",
                "",
                NetWorkingCapital));
        counter++;
        int NetWorkingCapitalDecrease = counter;
        Formulas.add(new Formula(NetWorkingCapitalDecrease,
                EvaluationTypes.PERIOD_COMPARISON_DECREASE.toString(),
                "",
                "",
                "Overall, the value of the working capital had dropped over STARTDATE-ENDDATE. ",
                "",
                "",
                NetWorkingCapital));
        return Formulas;
    }
}
