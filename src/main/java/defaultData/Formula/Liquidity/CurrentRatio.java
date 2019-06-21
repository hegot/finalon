package defaultData.Formula.Liquidity;

import defaultData.EvaluationTypes;
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
                EvaluationTypes.EVALUATE_START.toString(),
                ">",
                "3",
                "Current Ratio in STARTDATE equals to STARTVALUE (>3) that means excellent results.",
                "",
                "",
                CurrentRatio));
        counter++;
        int CurrentRatioexcellent = counter;
        Formulas.add(new Formula(CurrentRatioexcellent,
                EvaluationTypes.EVALUATE_START.toString(),
                ">",
                "2",
                "Current Ratio in CURRENTPERIOD  fits good results range (from 2 to 3). ",
                "<=",
                "3",
                CurrentRatio));
        counter++;
        int CurrentRatiogood = counter;
        Formulas.add(new Formula(CurrentRatiogood,
                EvaluationTypes.EVALUATE_START.toString(),
                ">=",
                "1.5",
            "Current Ratio in STARTDATE lies in the area of critical values (from 1.5 to 2). ",
                "<=",
                "2",
                CurrentRatio));
        counter++;
        int CurrentRatiobad = counter;
        Formulas.add(new Formula(CurrentRatiobad,
                EvaluationTypes.EVALUATE_START.toString(),
                "<",
                "1,5",
                "Current Ratio in STARTDATE lies in the area of critical values. ",
                "",
                "",
                CurrentRatio));

        counter++;
        int CurrentRatioEndSatisfactory = counter;
        Formulas.add(new Formula(CurrentRatioEndSatisfactory,
                EvaluationTypes.EVALUATE_END.toString(),
                ">",
                "3",
                "In the end of evaluation period the value (LASTVALUE) of the ratio is more " +
                        "than 3 that means excellent results. This means that COMPANYNAME was " +
                        "able to pay off debt in due time.",
                "",
                "",
                CurrentRatio));
        counter++;
        int CurrentRatioEndExcellent = counter;
        Formulas.add(new Formula(CurrentRatioEndExcellent,
                EvaluationTypes.EVALUATE_END.toString(),
                ">",
                "2",
                "In the end of evaluation period the value (LASTVALUE) of the ratio lies " +
                        "in good results range (from 2 to 3). This means that COMPANYNAME was able to pay off debt in due time.",
                "<=",
                "3",
                CurrentRatio));
        counter++;
        int CurrentRatioEndGood = counter;
        Formulas.add(new Formula(CurrentRatioEndGood,
                EvaluationTypes.EVALUATE_END.toString(),
                ">=",
                "1.5",
                "In the end of evaluation period the value (LASTVALUE) of the ratio lies in the area " +
                        "of critical values (from 1.5 to 2). This means that COMPANYNAME was able to pay off debt in due time.",
                "<=",
                "2",
                CurrentRatio));
        counter++;
        int CurrentRatioEndBad = counter;
        Formulas.add(new Formula(CurrentRatioEndBad,
                EvaluationTypes.EVALUATE_END.toString(),
                "<",
                "1,5",
                "The value of the ratio was unacceptable at the end of the period under review. " +
                        "This means that COMPANYNAME had problems with paying its suppliers and creditors in due time.  ",
                "",
                "",
                CurrentRatio));


        counter++;
        int CurrentRatioPrefix = counter;
        Formulas.add(new Formula(CurrentRatioPrefix,
                EvaluationTypes.PREFIX.toString(),
                "",
                "",
                "The current ratio was STARTVALUE in STARTDATE, meaning that COMPANYNAME had STARTVALUE times as many current assets as current liabilities. ",
                "",
                "",
                CurrentRatio));

        return Formulas;
    }
}
