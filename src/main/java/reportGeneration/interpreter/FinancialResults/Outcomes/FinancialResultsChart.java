package reportGeneration.interpreter.FinancialResults.Outcomes;

import entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.ChartBase;
import reportGeneration.interpreter.ReusableComponents.interfaces.GetVal;
import reportGeneration.interpreter.ReusableComponents.interfaces.LabelWrap;
import reportGeneration.interpreter.ReusableComponents.interfaces.OutcomeBase;
import reportGeneration.interpreter.ReusableComponents.interfaces.Round;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class FinancialResultsChart extends ChartBase implements GetVal, OutcomeBase, Round, LabelWrap {
    private ObservableMap<String, Double> valuesEBIT;
    private ObservableMap<String, Double> valuesRevenueGeneral;
    private ObservableMap<String, Double> valuesGrossProfit;
    private ObservableMap<String, Double> valuesComprehensiveIncome;
    private Item RevenueGeneral;
    private ArrayList<String> periodsArr = Periods.getInstance().getPeriodArr();
    private Periods periods = Periods.getInstance();

    public FinancialResultsChart() {
        ItemsStorage stor = ItemsStorage.getInstance();
        this.RevenueGeneral = stor.getItemByCode("RevenueGeneral");
        Item EBIT = stor.getItemByCode("EBIT");

        Item GrossProfit = stor.getItemByCode("GrossProfit");
        Item ComprehensiveIncome = stor.getItemByCode("ComprehensiveIncomeGeneral");
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

        for (String period : periodsArr) {
            Double originalVal = values.get(period);
            Double toCompare = getVal(RevenueGeneral, period);
            DecimalFormat df = new DecimalFormat("#.##");
            Double part = Double.valueOf(df.format(originalVal / toCompare * 100));
            outputVals.put(period, part);
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
        if (periodsArr.size() > 1) {
            vBox.getChildren().addAll(bc,
                    getGrossProfitEvaluation(),
                    getEbitEvaluation(),
                    getComprehensiveIncomeEvaluation()
            );
        }
        return vBox;
    }

    private Label getGrossProfitEvaluation() {

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
        String out = "The chart above shows that the gross profit to net sales ratio " +
                chRes + " in " + periods.getEnd() + " by "
                + round(last - fisrt) + "% comparing to " + periods.getStart();
        if (change != 0) {
            out += " The dynamics of the gross profit to net sales ratio over the period" +
                    " of " + periods.getStart() + "-" + periods.getEnd() + " demonstrates that the company's manufacturing or distribution" +
                    " process management efficiency was changing from period to period, being better" +
                    " during the periods with higher values of the ratio.";
        }
        return labelWrap(out);
    }

    private Label getEbitEvaluation() {

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
                out = formatDate(middelPreiod) + "also witnessed the " +
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

        return labelWrap(out);
    }

    private Label getComprehensiveIncomeEvaluation() {
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
        return labelWrap(out);
    }


}
