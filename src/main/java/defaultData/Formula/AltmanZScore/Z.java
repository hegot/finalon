package defaultData.Formula.AltmanZScore;

import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Z {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int ZScore = counter;
        Formulas.add(new Formula(ZScore,
                "Altman Z-score Z",
                "Z",
                "0.717*(GeneralCurrentAssets-CurrentLiabilities)/AssetsGeneral+0.847*RetainedEarnings/AssetsGeneral+3.107*(ProfitLossBeforeTax+FinanceCosts)/AssetsGeneral+0.42*EquityGeneral/(NonCurrentLiabilities+CurrentLiabilities)+0.998*RevenueGeneral/AssetsGeneral",
                "",
                "formula",
                "",
                parent));

        counter++;
        int ZScoreexcellent = counter;
        Formulas.add(new Formula(ZScoreexcellent,
                EvaluationTypes.EVALUATE_PRE_END.toString(),
                ">=",
                "2.9",
                "COMPANYS Z-score was above the gray area of 1.20 to " +
                        "2.90 indicating a low bankruptcy risk in PRE_END_DATE. ",
                "",
                "",
                ZScore));
        counter++;
        int ZScoregood = counter;
        Formulas.add(new Formula(ZScoregood,
                EvaluationTypes.EVALUATE_PRE_END.toString(),
                ">=",
                "1.2",
                "COMPANYS Z-score was in " +
                        "the gray area between 1.20 and 2.90 in PRE_END_DATE. ",
                "<",
                "2.9",
                ZScore));
        counter++;
        int ZScorebad = counter;
        Formulas.add(new Formula(ZScorebad,
                EvaluationTypes.EVALUATE_PRE_END.toString(),
                "<",
                "1.2",
                "COMPANYS Z-score was under 1.2 " +
                        "indicating a high bankruptcy risk in PRE_END_DATE. ",
                "",
                "",
                ZScore));


        counter++;
        int ZScoreIncrease = counter;
        Formulas.add(new Formula(ZScoreIncrease,
                EvaluationTypes.PERIOD_COMPARISON_INCREASE.toString(),
                "",
                "",
                "The increase of the company's Z-Score value over the period of STARTDATE-ENDDATE " +
                        "showed that COMPANYNAME has been improving its financial condition and " +
                        "declining the risk of bankruptcy. ",
                "",
                "",
                ZScore));
        counter++;
        int ZScoreDecline = counter;
        Formulas.add(new Formula(ZScoreDecline,
                EvaluationTypes.PERIOD_COMPARISON_DECREASE.toString(),
                "",
                "",
                "The decrease of the company's Z-Score value over the period of STARTDATE-ENDDATE " +
                        "showed that COMPANYNAME has been deteriorating its financial condition and " +
                        "increasing the risk of bankruptcy. ",
                "",
                "",
                ZScore));
        counter++;


        int ZScoreEndexcellent = counter;
        Formulas.add(new Formula(ZScoreEndexcellent,
                EvaluationTypes.EVALUATE_END.toString(),
                ">=",
                "2.9",
                "At the end of ENDDATE the Altman Z-score was LASTVALUE " +
                        "that indicates the company had a relatively " +
                        "high degree of protection against bankruptcy. ",
                "",
                "",
                ZScore));
        counter++;
        int ZScoreEndgood = counter;
        Formulas.add(new Formula(ZScoreEndgood,
                EvaluationTypes.EVALUATE_END.toString(),
                ">=",
                "1.2",
                "At the end of ENDDATE the Altman Z-score " +
                        "was LASTVALUE (within the gray area). ",
                "<",
                "2.9",
                ZScore));
        counter++;
        int ZScoreEndbad = counter;
        Formulas.add(new Formula(ZScoreEndbad,
                EvaluationTypes.EVALUATE_END.toString(),
                "<",
                "1.2",
                "At the end of ENDDATE the Altman Z-score " +
                        "was LASTVALUE that indicates the bankruptcy may follow. ",
                "",
                "",
                ZScore));
        return Formulas;
    }
}
