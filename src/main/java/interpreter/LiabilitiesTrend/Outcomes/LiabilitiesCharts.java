package interpreter.LiabilitiesTrend.Outcomes;

import entities.Item;
import finalonWindows.reusableComponents.ItemsTable.Periods;
import interpreter.ReusableComponents.ChartBase;
import javafx.collections.ObservableMap;
import javafx.scene.chart.BarChart;
import javafx.scene.layout.VBox;

public class LiabilitiesCharts extends ChartBase {
    private Periods periods;
    private ObservableMap<String, Double> valuesCurrent;
    private ObservableMap<String, Double> valuesNonCurrent;
    private ObservableMap<String, Double> valuesEquity;
    private ObservableMap<String, String> settings;

    public LiabilitiesCharts(
            ObservableMap<String, String> settings,
            Periods periods,
            Item current,
            Item nonCurrent,
            Item equity
    ) {
        super(periods.getPeriodArr());
        this.settings = settings;
        this.periods = periods;
        this.valuesCurrent = current.getValues();
        this.valuesNonCurrent = nonCurrent.getValues();
        this.valuesEquity = equity.getValues();
    }

    private String chartTitle() {
        return "Chart 2. " + settings.get("company") +
                " Source of finance between " +
                periods.getStart() + " - " + periods.getEnd() +
                " in " + settings.get("amount") + " " + settings.get("defaultCurrency");
    }

    public VBox get() {
        BarChart<String, Number> bc = getChart(chartTitle());
        if (valuesEquity != null) bc.getData().add(getSeries("Equity", valuesEquity));
        if (valuesCurrent != null) bc.getData().add(getSeries("Non Current Liabilities", valuesNonCurrent));
        if (valuesNonCurrent != null) bc.getData().add(getSeries("Current Liabilities", valuesCurrent));
        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(bc);
        return vBox;
    }

}
