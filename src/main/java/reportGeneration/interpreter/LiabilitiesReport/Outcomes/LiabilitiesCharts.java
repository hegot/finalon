package reportGeneration.interpreter.LiabilitiesReport.Outcomes;

import entities.Item;
import javafx.collections.ObservableMap;
import javafx.scene.chart.BarChart;
import javafx.scene.layout.VBox;
import reportGeneration.Periods;
import reportGeneration.SettingsStorage;
import reportGeneration.interpreter.ReusableComponents.ChartBase;

public class LiabilitiesCharts extends ChartBase {
    private ObservableMap<String, Double> valuesCurrent;
    private ObservableMap<String, Double> valuesNonCurrent;
    private ObservableMap<String, Double> valuesEquity;

    public LiabilitiesCharts(
            Item current,
            Item nonCurrent,
            Item equity
    ) {
        this.valuesCurrent = current.getValues();
        this.valuesNonCurrent = nonCurrent.getValues();
        this.valuesEquity = equity.getValues();
    }

    private String chartTitle() {
        Periods periods = Periods.getInstance();
        ObservableMap<String, String> settings = SettingsStorage.getSettings();
        return "Chart 2. " + settings.get("company") +
                " Source of finance between " +
                periods.getStart() + " - " + periods.getEnd() +
                " in " + settings.get("amount") + " " + settings.get("defaultCurrency");
    }

    public VBox get() {
        BarChart<String, Number> bc = getChart(chartTitle());
        if (valuesEquity != null) bc.getData().add(
                getSeries("Equity", valuesEquity)
        );
        if (valuesCurrent != null) bc.getData().add(
                getSeries("Non Current Liabilities", valuesNonCurrent)
        );
        if (valuesNonCurrent != null) bc.getData().add(
                getSeries("Current Liabilities", valuesCurrent)
        );
        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(bc);
        return vBox;
    }

}
