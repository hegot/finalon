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
                "GeneralCurrentAssets/CurrentLiabilitiesGeneral",
                "",
                "formula",
                "",
                parent));
        counter++;
        int CurrentRatiosatisfactory = counter;
        Formulas.add(new Formula(CurrentRatiosatisfactory,
                EvaluationTypes.EVALUATE_PRE_END.toString(),
                ">",
                "3",
                "For the PRE_END_DATE period the value of the ratio is higher than 3 that means excellent results.",
                "",
                "",
                CurrentRatio));
        counter++;
        int CurrentRatioexcellent = counter;
        Formulas.add(new Formula(CurrentRatioexcellent,
                EvaluationTypes.EVALUATE_PRE_END.toString(),
                ">",
                "2",
                "Current Ratio in PRE_END_DATE equals to PRE_END_VALUE that fits good results range (from 2 to 3). ",
                "<=",
                "3",
                CurrentRatio));
        counter++;
        int CurrentRatiogood = counter;
        Formulas.add(new Formula(CurrentRatiogood,
                EvaluationTypes.EVALUATE_PRE_END.toString(),
                ">=",
                "1.5",
                "In PRE_END_DATE the value (PRE_END_VALUE) of the ratio lies in the area of critical values (from 1.5 to 2). ",
                "<=",
                "2",
                CurrentRatio));
        counter++;
        int CurrentRatiobad = counter;
        Formulas.add(new Formula(CurrentRatiobad,
                EvaluationTypes.EVALUATE_PRE_END.toString(),
                "<",
                "1,5",
                "In PRE_END_DATE the value (PRE_END_VALUE) of the ratio lies above the area of critical values. ",
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
        counter++;
        int CurrentRatioSuffix1 = counter;
        Formulas.add(new Formula(CurrentRatioSuffix1,
                EvaluationTypes.EVALUATE_END.toString(),
                "",
                "",
                "The value of the ratio was acceptable at the end of the period under review. This means that COMPANYNAME was able to pay off debt in due time. ",
                ">=",
                "1.5",
                CurrentRatio));
        counter++;
        int CurrentRatioSuffix2 = counter;
        Formulas.add(new Formula(CurrentRatioSuffix2,
                EvaluationTypes.EVALUATE_END.toString(),
                "",
                "",
                "The value of the ratio was unacceptable at the end of the period under review. This means that COMPANYNAME had problems with paying its suppliers and creditors in due time. ",
                "<",
                "1.5",
                CurrentRatio));
        counter++;

        return Formulas;
    }
}
