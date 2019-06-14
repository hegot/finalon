package defaultData.Formula.InvestmentAnalysis;

import defaultData.EvaluationTypes;
import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NetAssets {
    public static ObservableList<Formula> get(int parent, int counter) {
        ObservableList<Formula> Formulas = FXCollections.observableArrayList();
        int NetAssets = counter;
        Formulas.add(new Formula(NetAssets,
                "Net assets (Net worth)",
                "NetAssets",
                "EquityAndLiabilities-LiabilitiesGeneral",
                EvaluationTypes.EVALUATE_END_ONLY.toString(),
                "formula",
                "money",
                parent));
        counter++;
        int NetAssetsNull = counter;
        Formulas.add(new Formula(NetAssetsNull,
                "zero",
                "=",
                "0.0",
                " The net worth equals zero at the end of evaluation period, meaning that the company may go bankrupt. ",
                "",
                "",
                NetAssets));
        counter++;
        int NetAssetslasPreLast1 = counter;
        Formulas.add(new Formula(NetAssetslasPreLast1,
                EvaluationTypes.SUBSTITUTE_COMPARATOR.toString(),
                "LASTVALUE",
                "PRELASTVALUE",
                "The net value of the enterprise's assets was CURRENCY LASTVALUE AMOUNT at the end of ENDDATE, about CURRENCY LASTVALUECHANGE AMOUNT more than declared at the end of PREENDDATE ",
                ">",
                "",
                NetAssets));
        counter++;
        int NetAssetslasPreLast2 = counter;
        Formulas.add(new Formula(NetAssetslasPreLast2,
                EvaluationTypes.SUBSTITUTE_COMPARATOR.toString(),
                "LASTVALUE",
                "PRELASTVALUE",
                "The net value of the enterprise's assets was CURRENCY LASTVALUE AMOUNT at the end of ENDDATE, about CURRENCY LASTVALUECHANGE AMOUNT less than declared at the end of PREENDDATE ",
                "<",
                "",
                NetAssets));
        counter++;
        int NetAssetsIncrease = counter;
        Formulas.add(new Formula(NetAssetsIncrease,
                EvaluationTypes.PERIOD_COMPARISON_INCREASE.toString(),
                "",
                "",
                "Overall, the net worth improved from CURRENCY STARTVALUE AMOUNT to CURRENCY LASTVALUE AMOUNT. This means that COMPANYNAME is expanding. ",
                "",
                "",
                NetAssets));
        counter++;
        int NetAssetsDecrease = counter;
        Formulas.add(new Formula(NetAssetsDecrease,
                EvaluationTypes.PERIOD_COMPARISON_DECREASE.toString(),
                "",
                "",
                "Overall, the net worth dropped from CURRENCY STARTVALUE AMOUNT to CURRENCY LASTVALUE AMOUNT. This means that COMPANYNAME is degrading. ",
                "",
                "",
                NetAssets));
        counter++;
        int NetAssetsNotChanged = counter;
        Formulas.add(new Formula(NetAssetsNotChanged,
                EvaluationTypes.PERIOD_COMPARISON_NOCHANGE.toString(),
                "",
                "",
                "The net value of the enterprise's assets was CURRENCY LASTVALUE AMOUNT at the end of ENDDATE. The net value of COMPANYNAME did not change in ENDDATE comparing to STARTDATE. ",
                "",
                "",
                NetAssets));
        return Formulas;
    }
}
