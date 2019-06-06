package defaultData.Formula.Turnover;

import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AverageCollectionPeriod {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int AverageCollectionPeriod = counter;
        Formulas.add(new Formula(AverageCollectionPeriod,
                "Average Collection Period (Accounts Receivable Turnover in Days)",
                "AverageCollectionPeriod",
                "360*((TradeAndOtherCurrentReceivables[0]+TradeAndOtherCurrentReceivables[1])/2)/RevenueGeneral",
                EvaluationTypes.EVALUATE_END_ONLY.toString(),
                "formula",
                "days",
                parent));
        counter++;
        int AverageCollectionPeriodgood = counter;
        Formulas.add(new Formula(AverageCollectionPeriodgood,
                "good",
                "<",
                "60",
                "Average Collection Period index comes into 'good' (< 60) range. ",
                "",
                "",
                AverageCollectionPeriod));
        counter++;
        int AverageCollectionPeriodsatisfactory = counter;
        Formulas.add(new Formula(AverageCollectionPeriodsatisfactory,
                "unsatisfactory",
                ">=",
                "60",
                "Average Collection Period index comes into 'unsatisfactory' (>= 60) range. ",
                "",
                "",
                AverageCollectionPeriod));
        counter++;
        int AverageCollectionPeriodIncrease = counter;
        Formulas.add(new Formula(AverageCollectionPeriodIncrease,
                EvaluationTypes.PERIOD_COMPARISON_INCREASE.toString(),
                "",
                "",
                "As a result, the average collection period grew up from STARTVALUE days to LASTVALUE days. ",
                "",
                "",
                AverageCollectionPeriod));
        counter++;
        int AverageCollectionPeriodDecrease = counter;
        Formulas.add(new Formula(AverageCollectionPeriodDecrease,
                EvaluationTypes.PERIOD_COMPARISON_DECREASE.toString(),
                "",
                "",
                "As a result, the average collection period dropped from STARTVALUE days to LASTVALUE days. ",
                "",
                "",
                AverageCollectionPeriod));
        counter++;
        int TheLongTermDebttoTotalCapitalizationRatioSuffix = counter;
        Formulas.add(new Formula(TheLongTermDebttoTotalCapitalizationRatioSuffix,
                EvaluationTypes.SUFFIX.toString(),
                "",
                "",
                "This means that it took an average of LASTVALUE days to collect a receivable in ENDDATE. ",
                "",
                "",
                AverageCollectionPeriod));

        return Formulas;
    }
}
