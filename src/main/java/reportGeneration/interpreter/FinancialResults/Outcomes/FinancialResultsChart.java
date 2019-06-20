package reportGeneration.interpreter.FinancialResults.Outcomes;

import entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.chart.BarChart;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.ChartBase;
import reportGeneration.interpreter.ReusableComponents.interfaces.*;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;

import java.util.ArrayList;

public class FinancialResultsChart extends ChartBase implements GetVal, Round, LabelWrap, JsCalcHelper, ParseDouble {
    private ObservableMap<String, Double> valuesEBIT;
    private ObservableMap<String, Double> valuesRevenueGeneral;
    private ObservableMap<String, Double> valuesGrossProfit;
    private ObservableMap<String, Double> valuesComprehensiveIncome;
    private Item RevenueGeneral;
    private ArrayList<String> periodsArr = Periods.getInstance().getPeriodArr();
    private Periods periods = Periods.getInstance();

    public FinancialResultsChart() {
        ItemsStorage stor = ItemsStorage.getInstance();
        this.RevenueGeneral = stor.get("RevenueGeneral");
        Item EBIT = stor.get("EBIT");

        Item GrossProfit = stor.get("GrossProfit");
        Item ComprehensiveIncome = stor.get("ComprehensiveIncomeGeneral");
        this.valuesEBIT = getUpdatedValues(EBIT.getValues());
        this.valuesRevenueGeneral = getMaxiVals();
        this.valuesGrossProfit = getUpdatedValues(GrossProfit.getValues());
        this.valuesComprehensiveIncome = getUpdatedValues(ComprehensiveIncome.getValues());
    }

    private ObservableMap<String, Double> getMaxiVals() {
        ObservableMap<String, Double> values = FXCollections.observableHashMap();
        for (String period : periodsArr) {
            values.put(period, 100.0);
        }
        return values;
    }

    private ObservableMap<String, Double> getUpdatedValues(ObservableMap<String, Double> values) {
        ObservableMap<String, Double> outputVals = FXCollections.observableHashMap();
        if (values != null && values.size() > 0) {
            for (String period : periodsArr) {
                Double originalVal = values.get(period);
                Double toCompare = RevenueGeneral.getVal(period);
                if (originalVal != null && toCompare != null) {
                    Double part = (originalVal / toCompare) * 100;
                    if (part != null) {
                        outputVals.put(period, parseDouble(round(part)));
                    }
                }
            }
        }
        return outputVals;
    }

    private String chartTitle() {
        return "Chart 5. Share in Financial Results in the Net Sales between" +
                periods.getStart() + " - " + periods.getEnd() + " %";
    }

    public VBox get() {
        BarChart<String, Number> bc = getChart(chartTitle());
        bc.getData().addAll(
                getSeries("Net Sales", valuesRevenueGeneral),
                getSeries("EBIT", valuesEBIT),
                getSeries("Gross Profit", valuesGrossProfit),
                getSeries("Comprehensive Income", valuesComprehensiveIncome)
        );
        VBox vBox = new VBox(20);
        vBox.getChildren().add(bc);
        if (periodsArr.size() > 1) {
            getGrossProfitEvaluation(vBox);
            getEbitEvaluation(vBox);
            getComprehensiveIncomeEvaluation(vBox);
        }
        return vBox;
    }

    private void getGrossProfitEvaluation(VBox vBox) {
        if (valuesGrossProfit.size() > 2) {
            Double fisrt = getFirstVal(valuesGrossProfit);
            Double last = getLastVal(valuesGrossProfit);
            Double change = last - fisrt;
            String chRes = "";
            if (change > 0) {
                chRes = "increased";
            }
            if (change < 0) {
                chRes = "decreased";
            }
            if (change == 0) {
                chRes = "was stable";
            }
            String out = "The chart above shows that the gross profit to net sales ratio " + chRes + " in " + periods.getEnd();
            if (change != 0) {
                out +=  " by "   + round(change) + "% ";
            }
            out +=   " comparing to " + periods.getStart() + ". ";
            if (change != 0) {
                out += " The dynamics of the gross profit to net sales ratio over the period" +
                        " of " + periods.getStart() + "-" + periods.getEnd() + " demonstrates that the company's manufacturing or distribution" +
                        " process management efficiency was changing from period to period, being better" +
                        " during the periods with higher values of the ratio.";
            }
            vBox.getChildren().add(labelWrap(out));
        }
    }

    private void getEbitEvaluation(VBox vBox) {

        if (valuesEBIT.size() > 2) {
            Double fisrt = getFirstVal(valuesEBIT);
            String out = "";
            Double last = getLastVal(valuesEBIT);
            Double change = last - fisrt;
            if (periodsArr.size() > 2) {
                String middelPreiod = periodsArr.get(1);
                Double middle = valuesEBIT.get(middelPreiod);
                if (middle - fisrt != 0) {
                    String chRes = "decreased";
                    if (middle - fisrt > 0) {
                        chRes = "increased";
                    }
                    out = formatDate(middelPreiod) + " also witnessed the " +
                            chRes + " of the company's EBIT to sales ratio comparing to "
                            + formatDate(periodsArr.get(0)) + ". ";
                }
            } else {
                String chRes = "";
                if (change > 0) {
                    chRes = "increase";
                }
                if (change < 0) {
                    chRes = "decrease";
                }
                out += periods.getEnd() + " also witnessed the " + chRes + " of the company's EBIT " +
                        "to sales ratio comparing to " + periods.getStart() + ". ";
            }

            if (change != 0) {
                out += "Changes in dynamics of the company's EBIT to net sales ratio over the period of "
                        + periods.getStart() + "-" + periods.getEnd() +
                        " confirm the variability of its cost management efficiency and earning ability." +
                        " Periods with higher values of this ratio witness better performance " +
                        "of a company in terms of profitability and cost management. ";
            }

            vBox.getChildren().add(labelWrap(out));
        }
    }

    private void getComprehensiveIncomeEvaluation(VBox vBox) {
        if (valuesComprehensiveIncome.size() > 2) {
            Double fisrt = getFirstVal(valuesComprehensiveIncome);
            String out = "";
            Double last = getLastVal(valuesComprehensiveIncome);
            Double change = last - fisrt;
            String chRes = "";
            if (change > 0) {
                chRes = "increased";
            }
            if (change < 0) {
                chRes = "decreased";
            }
            if (change != 0) {
                out += "The share of the comprehensive income in the company's net sales "
                        + chRes + " in " + periods.getEnd() + " by " + round(last - fisrt) + "%. ";
            } else {
                out += "The share of the comprehensive income in the company's net sales " +
                        "did not change during " + periods.getStart() + "-" + periods.getEnd() + ". ";
            }
            vBox.getChildren().add(labelWrap(out));
        }
    }

    private Double getLastVal(ObservableMap<String, Double> values) {
        ArrayList<String> arr = Periods.getInstance().getPeriodArr();
        String key = arr.get(arr.size() - 1);
        if (key != null) {
            return values.get(key);
        }
        return null;
    }

    private Double getFirstVal(ObservableMap<String, Double> values) {
        ArrayList<String> arr = Periods.getInstance().getPeriodArr();
        if (arr.get(0) != null) {
            return values.get(arr.get(0));
        }
        return null;
    }

}
