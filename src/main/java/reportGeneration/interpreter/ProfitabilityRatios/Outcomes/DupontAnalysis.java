package reportGeneration.interpreter.ProfitabilityRatios.Outcomes;

import entities.Formula;
import globalReusables.LabelWrap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.helpers.Formatter;
import reportGeneration.interpreter.ReusableComponents.helpers.TableName;
import reportGeneration.interpreter.ReusableComponents.tables.RatiosTable;
import reportGeneration.storage.*;

import java.util.ArrayList;

public class DupontAnalysis {
    private Formula returnOnAssets;
    private Formula netProfitMargin;
    private Formula totalAssetTurnover;

    public DupontAnalysis() {
        this.returnOnAssets = FormulaStorage.get("ReturnOnAssets");
        this.netProfitMargin = FormulaStorage.get("NetProfitMargin");
        this.totalAssetTurnover = FormulaStorage.get("TotalAssetTurnover");
    }

    public VBox get(int weight) {
        VBox box = new VBox(8);
        String title = "Table 9. Dupont Analysis";
        Label tableName = TableName.name(title);
        weight++;
        ObservableList<Formula> formulas = FXCollections.observableArrayList();
        formulas.addAll(
                returnOnAssets,
                netProfitMargin,
                totalAssetTurnover
        );

        TableView tbl = new RatiosTable(formulas).get();
        tbl.setPrefHeight(170);
        TwoDList items = TableName.getTableViewValues(tbl);
        ResultsStorage.addTable(weight, items, title);
        weight++;
        String evaluate = evaluate();
        ResultsStorage.addStr(weight, "text", evaluate);
        box.getChildren().addAll(
                tableName,
                tbl,
                LabelWrap.wrap(evaluate)
        );
        return box;
    }


    private String evaluate() {
        Periods periods = new Periods();
        ArrayList<String> periodsArr = periods.getPeriodArr();
        String company = SettingsStorage.get("company");
        String currency = SettingsStorage.get("defaultCurrency");
        String start = periods.getStart();
        String end = periods.getEnd();
        if (periodsArr.size() > 2 && returnOnAssets != null && netProfitMargin != null && totalAssetTurnover != null) {
            Double returnOnAssetsFirst = returnOnAssets.getFirstVal();
            Double returnOnAssetsLast = returnOnAssets.getLastVal();

            Double netProfitMarginFirst = netProfitMargin.getFirstVal();
            Double netProfitMarginLast = netProfitMargin.getLastVal();

            Double totalAssetTurnoverFirst = totalAssetTurnover.getFirstVal();
            Double totalAssetTurnoverLast = totalAssetTurnover.getLastVal();
            StringBuilder prefix = new StringBuilder();
            if (returnOnAssetsFirst != null && returnOnAssetsLast != null &&
                    netProfitMarginFirst != null && netProfitMarginLast != null &&
                    totalAssetTurnoverFirst != null && totalAssetTurnoverLast != null) {
                if (returnOnAssetsLast > returnOnAssetsFirst) {
                    prefix.append("Company's return on assets increased from " + Formatter.finalNumberFormat(returnOnAssetsFirst) +
                            "% in " + start + " to " + returnOnAssetsLast + "% in " + end + ". ");
                    if (netProfitMarginLast <= netProfitMarginFirst
                            && totalAssetTurnoverLast > totalAssetTurnoverFirst) {
                        prefix.append("The explanation for this trend can be found, as " +
                                "the total asset turnover increased from " + Formatter.finalNumberFormat(totalAssetTurnoverFirst) +
                                " in " + start + " to " + Formatter.finalNumberFormat(totalAssetTurnoverLast) + " in " +
                                end + ". This indicates that the company has been increasing the " +
                                "efficiency of managing its assets, turning them over more frequently. ”" +
                                company + "'s” effort in improving the asset turnover was enough " +
                                "to cause the positive return on assets trend, even though the company " +
                                "wasn't able to improve its net profit margin over the reported period. ");
                    }

                    if (netProfitMarginLast > netProfitMarginFirst
                            && totalAssetTurnoverLast <= totalAssetTurnoverFirst) {
                        prefix.append("The explanation for this trend can be found, as " +
                                "the net profit margin increased from " + Formatter.finalNumberFormat(netProfitMarginFirst) +
                                "% in " + start + " to " + Formatter.finalNumberFormat(netProfitMarginLast) + "% in " +
                                end + ". This indicates that the company has been improving its profitability, " +
                                "increasing the amount of the net profit generated by 1 " + currency + " of sales. ”" +
                                company + "'s” effort in rising the net profit margin was enough to cause the positive " +
                                "return on assets trend, even though the company wasn't " +
                                "able to improve its asset management efficiency over the reported period. ");
                    }

                    if (netProfitMarginLast > netProfitMarginFirst
                            && totalAssetTurnoverLast > totalAssetTurnoverFirst) {
                        prefix.append("The explanation for this trend can be found, as both " +
                                "net profit margin and total asset turnover grew in " + end + " comparing to  " + start + ". " +
                                "Net profit margin increased from " + Formatter.finalNumberFormat(netProfitMarginFirst) + "%  in " + start + " to " +
                                Formatter.finalNumberFormat(netProfitMarginLast) + "% in " + end + ", indicating the amount of the net profit generated by 1 " +
                                currency + " of sales became greater. Total asset turnover grew from " + Formatter.finalNumberFormat(totalAssetTurnoverFirst) +
                                " in " + start + " to " + Formatter.finalNumberFormat(totalAssetTurnoverLast) + " in " + end +
                                ", meaning the company has been increasing the efficiency of managing its assets, " +
                                "turning them over more frequently. ");
                    }
                }


                if (returnOnAssetsLast < returnOnAssetsFirst) {
                    prefix.append("Company's return on assets decreased from " + Formatter.finalNumberFormat(returnOnAssetsFirst) +
                            "% in " + start + " to " + Formatter.finalNumberFormat(returnOnAssetsLast) + "% in " + end + ". ");
                    if (netProfitMarginLast < netProfitMarginFirst
                            && totalAssetTurnoverLast < totalAssetTurnoverFirst) {
                        prefix.append("The explanation for this trend can be found, as both " +
                                "net profit margin and total asset turnover declined in " + end + " comparing to  " + start + ". " +
                                "Net profit margin decreased from " + Formatter.finalNumberFormat(netProfitMarginFirst) + "%  in " + start + " to " +
                                Formatter.finalNumberFormat(netProfitMarginLast) + "% in " + end + ", indicating the amount of the net profit generated by 1 " +
                                currency + " of sales became smaller. Total asset turnover declined from " + Formatter.finalNumberFormat(totalAssetTurnoverFirst) +
                                " in " + start + " to " + Formatter.finalNumberFormat(totalAssetTurnoverLast) + " in " + end +
                                ", meaning the company has been deteriorating the efficiency of managing its assets, " +
                                "turning them over less frequently. ");
                    }

                    if (netProfitMarginLast < netProfitMarginFirst
                            && totalAssetTurnoverLast >= totalAssetTurnoverFirst) {
                        prefix.append("The explanation for this trend can be found, as " +
                                "the net profit margin decreased from " + Formatter.finalNumberFormat(netProfitMarginFirst) +
                                "% in " + start + " to " + Formatter.finalNumberFormat(netProfitMarginLast) + "% in " +
                                end + ". This indicates that the company has been deteriorating its profitability, " +
                                "The impact of this decline was so strong that even  ”" + company + "'s” efficient " +
                                "enough assets management policy didn't cause the return on assets increase in " + end);
                    }


                    if (netProfitMarginLast >= netProfitMarginFirst
                            && totalAssetTurnoverLast < totalAssetTurnoverFirst) {
                        prefix.append("The explanation for this trend can be found, as the total asset turnover decreased from "
                                + Formatter.finalNumberFormat(totalAssetTurnoverFirst) +
                                " in " + start + " to " + Formatter.finalNumberFormat(totalAssetTurnoverLast) + " in " + end +
                                "This indicates that the company has been deteriorating its assets management efficiency, " +
                                "turning them over less frequently. The impact of this deterioration was so strong " +
                                "that even good trends in ”" + company + "'s” ability to generate the net income from sales " +
                                "didn't cause the return on assets increase in " + end);
                    }

                }
            }
            return prefix.toString();
        }
        return "";
    }
}
