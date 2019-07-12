package reportGeneration.interpreter.TurnoverRatios.Outcomes;

import entities.Formula;
import entities.Item;
import javafx.collections.ObservableMap;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;

import java.util.ArrayList;

public class TotalAssetTurnoverHook {
    private Double firstVal;
    private Double lastVal;
    private ArrayList<String> periodsArr;
    private Double revenueChange;
    private Double assetsChange;

    TotalAssetTurnoverHook(Formula formula) {
        Periods periods = new Periods();
        this.periodsArr = periods.getPeriodArr();
        if(periodsArr.size() > 1){
            ItemsStorage storage = ItemsStorage.getInstance();

            this.firstVal = formula.getFirstVal();
            this.lastVal = formula.getLastVal();

            Item RevenueGeneral = storage.get("RevenueGeneral");
            Double firstRevenueVal = getSecondFirstVal(RevenueGeneral.getValues());
            Double lastRevenueVal = RevenueGeneral.getLastVal();
            if (firstRevenueVal != null && lastRevenueVal != null) {
                this.revenueChange = lastRevenueVal - firstRevenueVal;
            }

            Item AssetsGeneral = storage.get("AssetsGeneral");
            Double firstAssetsGeneralVal1 = AssetsGeneral.getFirstVal();
            Double firstAssetsGeneralVal2 = getSecondFirstVal(AssetsGeneral.getValues());
            Double lastAssetsGeneralVal1 = AssetsGeneral.getLastVal();
            Double lastAssetsGeneralVal2 = getSecondLastVal(AssetsGeneral.getValues());
            if (firstAssetsGeneralVal1 != null && lastAssetsGeneralVal1 != null) {
                this.assetsChange = (lastAssetsGeneralVal1 + lastAssetsGeneralVal2) / 2
                        - (firstAssetsGeneralVal1 + firstAssetsGeneralVal2) / 2;
            }
        }
    }

    private Double getSecondFirstVal(ObservableMap<String, Double> vals) {
        String nextPer = periodsArr.get(1);
        Double nextVal = 0.0;
        if (nextPer != null) {
            nextVal = vals.get(nextPer);
        }
        return nextVal;
    }


    private Double getSecondLastVal(ObservableMap<String, Double> vals) {
        String prevPer = periodsArr.get(periodsArr.size() - 2);
        Double prevVal = 0.0;
        if (prevPer != null) {
            prevVal = vals.get(prevPer);
        }
        return prevVal;
    }

    public String getResult() {
        StringBuilder output = new StringBuilder();
        if (
                periodsArr.size() > 1
                && firstVal != null
                && lastVal != null
                && revenueChange != null
                && assetsChange != null
                && periodsArr.size() > 2
        ) {
            if (lastVal > firstVal) {
                if (revenueChange > 0 && assetsChange > 0) {
                    output.append("The total asset turnover ratio was higher at the end " +
                            "of the period under review because the net sales " +
                            "of the enterprise increased during STARTDATE-ENDDATE. ");
                }
                if (revenueChange > 0 && assetsChange < 0) {
                    output.append("The total asset turnover ratio was higher at the end " +
                            "of the period under review because the net sales of the enterprise " +
                            "increased and the average total assets decreased during STARTDATE-ENDDATE. ");
                }
                if (revenueChange < 0 && assetsChange < 0) {
                    output.append("The total asset turnover ratio was higher at the end " +
                            "of the period under review because the average " +
                            "total assets decreased during STARTDATE-ENDDATE. ");
                }
            } else {
                if (revenueChange > 0 && assetsChange > 0) {
                    output.append("The total asset turnover ratio was lower at the end " +
                            "of the period under review because the average " +
                            "total assets increased during STARTDATE-ENDDATE. ");
                }
                if (revenueChange < 0 && assetsChange > 0) {
                    output.append("The total asset turnover ratio was lower at the end " +
                            "of the period under review because the average total assets " +
                            "increased and the net sales of the enterprise decreased during STARTDATE-ENDDATE. ");
                }
                if (revenueChange < 0 && assetsChange < 0) {
                    output.append("The total asset turnover ratio was lower at the end " +
                            "of the period under review because the net sales of the enterprise " +
                            "decreased during  STARTDATE-ENDDATE. ");
                }
            }
        }
        return output.toString();
    }
}