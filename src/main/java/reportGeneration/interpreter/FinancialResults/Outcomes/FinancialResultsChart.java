package reportGeneration.interpreter.FinancialResults.Outcomes;

import entities.Item;
import globalReusables.LabelWrap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.chart.BarChart;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.ChartBase;
import reportGeneration.interpreter.ReusableComponents.helpers.Calc;
import reportGeneration.interpreter.ReusableComponents.helpers.Formatter;
import reportGeneration.interpreter.ReusableComponents.helpers.TableName;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.ResultsStorage;

import java.util.ArrayList;

public class FinancialResultsChart extends ChartBase {
    private ObservableMap<String, Double> valuesEBIT;
    private ObservableMap<String, Double> valuesRevenueGeneral;
    private ObservableMap<String, Double> valuesGrossProfit;
    private ObservableMap<String, Double> valuesComprehensiveIncome;
    private Item RevenueGeneral;
    private ArrayList<String> periodsArr = Periods.getPeriodArr();

    public FinancialResultsChart() {
        this.RevenueGeneral = ItemsStorage.get("RevenueGeneral");
        Item EBIT = ItemsStorage.get("EBIT");
        Item GrossProfit = ItemsStorage.get("GrossProfit");
        Item ComprehensiveIncome = ItemsStorage.get("ComprehensiveIncomeGeneral");
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
                    Double part = Calc.part(originalVal, toCompare);
                    if (part != null) {
                        outputVals.put(period, part);
                    }
                }
            }
        }
        return outputVals;
    }

    private String chartTitle() {
        return "Chart 5. Share in Financial Results in the Net Sales between " +
                Periods.getStart() + " - " + Periods.getEnd() + " %";
    }

    public VBox get(int weight) {
        BarChart<String, Number> bc = getChart();
        bc.getData().addAll(
                getSeries("Net Sales", valuesRevenueGeneral),
                getSeries("EBIT", valuesEBIT),
                getSeries("Gross Profit", valuesGrossProfit),
                getSeries("Comprehensive Income", valuesComprehensiveIncome)
        );
        String title = chartTitle();
        ResultsStorage.addBarChart(weight, bc, title);
        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(TableName.name(title), bc);
        if (periodsArr.size() > 1) {
            getGrossProfitEvaluation(vBox, weight);
            weight++;
            getEbitEvaluation(vBox, weight);
            weight++;
            getComprehensiveIncomeEvaluation(vBox, weight);
        }
        return vBox;
    }

    private void getGrossProfitEvaluation(VBox vBox, int weight) {
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
            String out = "The chart above shows that the gross profit to net sales ratio " + chRes + " in " + Periods.getEnd();
            if (change != 0) {
                out += " by " + Formatter.format(change) + "% ";
            }
            out += " comparing to " + Periods.getStart() + ". ";
            if (change != 0) {
                out += " The dynamics of the gross profit to net sales ratio over the period" +
                        " of " + Periods.getStart() + "-" + Periods.getEnd() + " demonstrates that the company's manufacturing or distribution" +
                        " process management efficiency was changing from period to period, being better" +
                        " during the periods with higher values of the ratio.";
            }
            ResultsStorage.addStr(weight, "text", out);
            vBox.getChildren().add(LabelWrap.wrap(out));
        }
    }

    private void getEbitEvaluation(VBox vBox, int weight) {

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
                    out = Formatter.formatDate(middelPreiod) + " also witnessed the " +
                            chRes + " of the company's EBIT to sales ratio comparing to "
                            + Formatter.formatDate(periodsArr.get(0)) + ". ";
                }
            } else {
                String chRes = "";
                if (change > 0) {
                    chRes = "increase";
                }
                if (change < 0) {
                    chRes = "decrease";
                }
                out += Periods.getEnd() + " also witnessed the " + chRes + " of the company's EBIT " +
                        "to sales ratio comparing to " + Periods.getStart() + ". ";
            }

            if (change != 0) {
                out += "Changes in dynamics of the company's EBIT to net sales ratio over the period of "
                        + Periods.getStart() + "-" + Periods.getEnd() +
                        " confirm the variability of its cost management efficiency and earning ability." +
                        " Periods with higher values of this ratio witness better performance " +
                        "of a company in terms of profitability and cost management. ";
            }
            ResultsStorage.addStr(weight, "text", out);
            vBox.getChildren().add(LabelWrap.wrap(out));
        }
    }

    private void getComprehensiveIncomeEvaluation(VBox vBox, int weight) {
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
                        + chRes + " in " + Periods.getEnd() + " by " + Formatter.format(last - fisrt) + "%. ";
            } else {
                out += "The share of the comprehensive income in the company's net sales " +
                        "did not change during " + Periods.getStart() + "-" + Periods.getEnd() + ". ";
            }
            ResultsStorage.addStr(weight, "text", out);
            vBox.getChildren().add(LabelWrap.wrap(out));
        }
    }

    private Double getLastVal(ObservableMap<String, Double> values) {
        String key = periodsArr.get(periodsArr.size() - 1);
        if (key != null) {
            return values.get(key);
        }
        return null;
    }

    private Double getFirstVal(ObservableMap<String, Double> values) {
        if (periodsArr.get(0) != null) {
            return values.get(periodsArr.get(0));
        }
        return null;
    }

}
