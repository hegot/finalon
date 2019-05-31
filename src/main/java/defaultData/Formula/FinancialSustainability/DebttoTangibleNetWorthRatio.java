package defaultData.Formula.FinancialSustainability;

import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DebttoTangibleNetWorthRatio {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int DebttoTangibleNetWorthRatio = counter;
        Formulas.add(new Formula(DebttoTangibleNetWorthRatio,
                "Debt to Tangible Net Worth Ratio",
                "DebttoTangibleNetWorthRatio",
                "(NonCurrentAssets+CurrentLiabilities)/(EquityGeneral-IntangibleAssetsOtherThanGoodwill-GoodwillGeneral)",
                EvaluationTypes.EVALUATE_END_ONLY.toString(),
                "formula",
                "",
                parent));
        counter++;
        int DebttoTangibleNetWorthRatioexcellent = counter;
        Formulas.add(new Formula(DebttoTangibleNetWorthRatioexcellent,
                "excellent",
                ">=",
                "0.6",
                "Company`s Debt to Tangible Net Worth Ratio was excellent in the end period (>= 0.6). ",
                "",
                "",
                DebttoTangibleNetWorthRatio));
        counter++;
        int DebttoTangibleNetWorthRatiosatisfactory = counter;
        Formulas.add(new Formula(DebttoTangibleNetWorthRatiosatisfactory,
                "satisfactory",
                "<=",
                "0.4",
                "Company`s Debt to Tangible Net Worth Ratio was satisfactory in the end period (<0.6 , <=0.4). ",
                "<",
                "0.6",
                DebttoTangibleNetWorthRatio));
        counter++;
        int DebttoTangibleNetWorthRatiobad = counter;
        Formulas.add(new Formula(DebttoTangibleNetWorthRatiobad,
                "bad",
                "<",
                "0.4",
                "Company`s Debt to Tangible Net Worth Ratio was unsatisfactory in the end period (< 0.4). ",
                "",
                "",
                DebttoTangibleNetWorthRatio));

        counter++;
        int DebttoTangibleNetWorthRatioIncrease = counter;
        Formulas.add(new Formula(DebttoTangibleNetWorthRatioIncrease,
                EvaluationTypes.PERIOD_COMPARISON_INCREASE.toString(),
                "",
                "",
                "The table 5 shows that the ratio changed from STARTVALUE in STARTDATE to LASTVALUE in ENDDATE. " +
                        "This shows that the creditors' protection was getting better. ",
                "",
                "",
                DebttoTangibleNetWorthRatio));
        counter++;
        int DebttoTangibleNetWorthRatioDecrease = counter;
        Formulas.add(new Formula(DebttoTangibleNetWorthRatioDecrease,
                EvaluationTypes.PERIOD_COMPARISON_DECREASE.toString(),
                "",
                "",
                "The table 5 shows that the ratio changed from STARTVALUE in STARTDATE to LASTVALUE in ENDDATE. " +
                        "This shows that the creditors' protection was getting worse. ",
                "",
                "",
                DebttoTangibleNetWorthRatio));
        counter++;
        int DebttoTangibleNetWorthRatioPrefix = counter;
        Formulas.add(new Formula(DebttoTangibleNetWorthRatioPrefix,
                EvaluationTypes.PREFIX.toString(),
                "",
                "",
                "The debt to tangible net worth ratio is a more conservative ratio than the debt/equity ratio. " +
                        "It eliminates intangible assets because they do not provide resources to pay creditors. ",
                "",
                "",
                DebttoTangibleNetWorthRatio));
        counter++;
        return Formulas;
    }
}
