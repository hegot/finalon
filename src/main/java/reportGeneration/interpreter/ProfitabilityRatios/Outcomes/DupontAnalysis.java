package reportGeneration.interpreter.ProfitabilityRatios.Outcomes;

import entities.Formula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import globalReusables.LabelWrap;
import reportGeneration.interpreter.ReusableComponents.interfaces.ParseDouble;
import reportGeneration.interpreter.ReusableComponents.interfaces.TableName;
import reportGeneration.interpreter.ReusableComponents.tables.RatiosTable;
import reportGeneration.storage.*;

import java.util.ArrayList;

public class DupontAnalysis implements LabelWrap, ParseDouble, TableName {
    private Formula returnOnAssets;
    private Formula netProfitMargin;
    private Formula totalAssetTurnover;

    public DupontAnalysis() {
        FormulaStorage storage = FormulaStorage.getInstance();
        this.returnOnAssets = storage.get("ReturnoNonAssets");
        this.netProfitMargin = storage.get("NetProfitMargin");
        this.totalAssetTurnover = storage.get("TotalAssetTurnover");

    }

    public VBox get() {
        VBox box = new VBox(8);
        String title = "Table 9. Dupont Analysis";
        Label tableName = tableName(title);
        ResultsStorage.addStr(74, "tableName", title);
        ObservableList<Formula> formulas = FXCollections.observableArrayList();
        formulas.addAll(
                returnOnAssets,
                netProfitMargin,
                totalAssetTurnover
        );

        TableView tbl = new RatiosTable(formulas).get();
        TwoDList items = getTableViewValues(tbl);
        ResultsStorage.addTable(75, items);
        String evaluate = evaluate();
        ResultsStorage.addStr(76, "text", evaluate);
        box.getChildren().addAll(
                tableName,
                tbl,
                labelWrap(evaluate)
        );
        return box;
    }

    private String valToPercent(Double val) {
        if (val != null) {
            val = val * 100;
            return round(val);
        }
        return "";
    }

    private String evaluate() {
        Periods periods = new Periods();
        ArrayList<String> periodsArr = periods.getPeriodArr();
        ObservableMap<String, String> settings = SettingsStorage.getInstance().getSettings();
        String company = settings.get("company");
        String currency = settings.get("defaultCurrency");
        String start = periods.getStart();
        String end = periods.getEnd();
        if (periodsArr.size() > 2 && returnOnAssets != null && netProfitMargin != null && totalAssetTurnover != null) {
            Double returnOnAssetsFirst = returnOnAssets.getFirstVal();
            String returnOnAssetsFirstStr = valToPercent(returnOnAssetsFirst);
            Double returnOnAssetsLast = returnOnAssets.getLastVal();
            String returnOnAssetsLastStr = valToPercent(returnOnAssetsLast);

            Double netProfitMarginFirst = netProfitMargin.getFirstVal();
            String netProfitMarginFirstStr = valToPercent(netProfitMarginFirst);
            Double netProfitMarginLast = netProfitMargin.getLastVal();
            String netProfitMarginLastStr = valToPercent(netProfitMarginLast);

            Double totalAssetTurnoverFirst = totalAssetTurnover.getFirstVal();
            String totalAssetTurnoverFirstStr = valToPercent(totalAssetTurnoverFirst);
            Double totalAssetTurnoverLast = totalAssetTurnover.getLastVal();
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
}
