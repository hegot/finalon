package reportGeneration.interpreter.DupontAnalysis;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.interfaces.LabelWrap;
import reportGeneration.interpreter.ReusableComponents.interfaces.OutcomeBase;
import reportGeneration.interpreter.ReusableComponents.interfaces.ParseDouble;
import reportGeneration.interpreter.ReusableComponents.tables.RatiosTable;
import reportGeneration.storage.FormulaStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.SettingsStorage;

import java.util.ArrayList;

public class DupontAnalysis implements LabelWrap, ParseDouble {
    private Formula returnOnAssets;
    private Formula netProfitMargin;
    private Formula totalAssetTurnover;

    public DupontAnalysis() {
        FormulaStorage storage = FormulaStorage.getInstance();
        this.returnOnAssets = storage.getItemByCode("ReturnoNonAssets");
        this.netProfitMargin = storage.getItemByCode("NetProfitMargin");
        this.totalAssetTurnover = storage.getItemByCode("TotalAssetTurnover");

    }

    public VBox get() {
        VBox box = new VBox(8);
        Label tableName = new Label("Table 9. Dupont Analysis");
        tableName.getStyleClass().add("table-name");
        tableName.setWrapText(true);

        ObservableList<Formula> formulas = FXCollections.observableArrayList();
        formulas.addAll(
                returnOnAssets,
                netProfitMargin,
                totalAssetTurnover
        );


        box.getChildren().addAll(
                tableName,
                new RatiosTable(formulas).get(),
                labelWrap(evaluate())
        );
        return box;
    }

    private String valToPercent(Double val) {
        val = val * 100;
        return round(val);
    }

    private String evaluate() {
        Periods periods = new Periods();
        ArrayList<String> periodsArr =  periods.getPeriodArr();
        ObservableMap<String, String> settings = SettingsStorage.getSettings();
        String company = settings.get("company");
        String currency = settings.get("defaultCurrency");
        String start = periods.getStart();
        String end = periods.getEnd();
        ObservableMap<String, String> returnOnAssetsValues = returnOnAssets.getPeriods();
        ObservableMap<String, String> netProfitMarginValues = netProfitMargin.getPeriods();
        ObservableMap<String, String> totalAssetTurnoverValues = totalAssetTurnover.getPeriods();
        if (periodsArr.size() > 2 && returnOnAssetsValues.size() > 0 && netProfitMarginValues.size() > 0 && totalAssetTurnoverValues.size() > 0) {
            Double returnOnAssetsFirst = getFirstValStr(returnOnAssetsValues);
            String returnOnAssetsFirstStr = valToPercent(returnOnAssetsFirst);
            Double returnOnAssetsLast = getLastValStr(returnOnAssetsValues);
            String returnOnAssetsLastStr = valToPercent(returnOnAssetsLast);

            Double netProfitMarginFirst = getFirstValStr(netProfitMarginValues);
            String netProfitMarginFirstStr = valToPercent(netProfitMarginFirst);
            Double netProfitMarginLast = getLastValStr(netProfitMarginValues);
            String netProfitMarginLastStr = valToPercent(netProfitMarginLast);

            Double totalAssetTurnoverFirst = getFirstValStr(totalAssetTurnoverValues);
            String totalAssetTurnoverFirstStr = valToPercent(totalAssetTurnoverFirst);
            Double totalAssetTurnoverLast = getLastValStr(totalAssetTurnoverValues);
            String totalAssetTurnoverLastStr = valToPercent(totalAssetTurnoverLast);

            if (returnOnAssetsFirst != null && returnOnAssetsLast != null &&
                    netProfitMarginFirst != null && netProfitMarginLast != null &&
                    totalAssetTurnoverFirst != null && totalAssetTurnoverLast != null) {
                if (returnOnAssetsLast > returnOnAssetsFirst) {
                    String prefix = "Company's return on assets increased from " + returnOnAssetsFirstStr +
                            "% in " + start + " to " + returnOnAssetsLastStr + "% in " + end + ". ";
                    if (netProfitMarginLast <= netProfitMarginFirst
                            && totalAssetTurnoverLast > totalAssetTurnoverFirst) {
                        return prefix + "The explanation for this trend can be found, as " +
                                "the total asset turnover increased from " + totalAssetTurnoverFirstStr +
                                " in " + start + " to " + totalAssetTurnoverLastStr + " in " +
                                end + ". This indicates that the company has been increasing the " +
                                "efficiency of managing its assets, turning them over more frequently. " +
                                company + "'s effort in improving the asset turnover was enough " +
                                "to cause the positive return on assets trend, even though the company " +
                                "wasn't able to improve its net profit margin over the reported period. ";
                    }

                    if (netProfitMarginLast > netProfitMarginFirst
                            && totalAssetTurnoverLast <= totalAssetTurnoverFirst) {
                        return prefix + "The explanation for this trend can be found, as " +
                                "the net profit margin increased from " + netProfitMarginFirst +
                                " in " + start + " to " + netProfitMarginLast + " in " +
                                end + ". This indicates that the company has been improving its profitability, " +
                                "increasing the amount of the net profit generated by 1 " + currency + " of sales." +
                                company + "'s effort in rising the net profit margin was enough to cause the positive " +
                                "return on assets trend, even though the company wasn't " +
                                "able to improve its asset management efficiency over the reported period. ";
                    }

                    if (netProfitMarginLast > netProfitMarginFirst
                            && totalAssetTurnoverLast > totalAssetTurnoverFirst) {
                        return prefix + "The explanation for this trend can be found, as both " +
                                "net profit margin and total asset turnover grew in " + end + " comparing to  " + start + ". " +
                                "Net profit margin increased from " + netProfitMarginFirstStr + "  in " + start + " to " +
                                netProfitMarginLastStr + " in " + end + ", indicating the amount of the net profit generated by 1 " +
                                currency + " of sales became greater. Total asset turnover grew from " + totalAssetTurnoverFirstStr +
                                " in " + start + " to " + totalAssetTurnoverLastStr + " in" + end +
                                ", meaning the company has been increasing the efficiency of managing its assets, " +
                                "turning them over more frequently. ";
                    }
                }

            }


            if (returnOnAssetsLast < returnOnAssetsFirst) {
                String prefix = "Company's return on assets decreased from " + returnOnAssetsFirstStr +
                        "% in " + start + " to " + returnOnAssetsLastStr + "% in " + end + ". ";
                if (netProfitMarginLast < netProfitMarginFirst
                        && totalAssetTurnoverLast < totalAssetTurnoverFirst) {
                    return prefix + "The explanation for this trend can be found, as both " +
                            "net profit margin and total asset turnover declined in " + end + " comparing to  " + start + ". " +
                            "Net profit margin decreased from " + netProfitMarginFirstStr + "  in " + start + " to " +
                            netProfitMarginLastStr + " in " + end + ", indicating the amount of the net profit generated by 1 " +
                            currency + " of sales became smaller. Total asset turnover declined from " + totalAssetTurnoverFirstStr +
                            " in " + start + " to " + totalAssetTurnoverLastStr + " in " + end +
                            ",meaning the company has been deteriorating the efficiency of managing its assets, " +
                            "turning them over less frequently. ";
                }

                if (netProfitMarginLast < netProfitMarginFirst
                        && totalAssetTurnoverLast >= totalAssetTurnoverFirst) {
                    return prefix + "The explanation for this trend can be found, as " +
                            "the net profit margin decreased from " + netProfitMarginFirst +
                            " in " + start + " to " + netProfitMarginLast + " in " +
                            end + ". This indicates that the company has been deteriorating its profitability, " +
                            "The impact of this decline was so strong that even  " + company + "'s efficient " +
                            "enough assets management policy didn't cause the return on assets increase in " + end;
                }


                if (netProfitMarginLast >= netProfitMarginFirst
                        && totalAssetTurnoverLast < totalAssetTurnoverFirst) {
                    return prefix + "The explanation for this trend can be found, as the total asset turnover decreased from " + totalAssetTurnoverFirstStr +
                            " in " + start + " to " + totalAssetTurnoverLastStr + " in " + end +
                            "This indicates that the company has been deteriorating its assets management efficiency, " +
                            "turning them over less frequently. The impact of this deterioration was so strong " +
                            "that even good trends in " + company + "'s ability to generate the net income from sales " +
                            "didn't cause the return on assets increase in " + end;
                }
            }
        }
        return "";
    }


    private Double getLastValStr(ObservableMap<String, String> values) {
        ArrayList<String> arr = Periods.getInstance().getPeriodArr();
        String key = arr.get(arr.size() - 1);
        if (key != null) {
            return parseDouble(values.get(key));
        }
        return null;
    }

    private Double getFirstValStr(ObservableMap<String, String> values) {
        ArrayList<String> arr = Periods.getInstance().getPeriodArr();
        String key = arr.get(1);
        if (key != null ) {
            return parseDouble(values.get(key));
        }
        return null;
    }
}
