package reportGeneration.interpreter.LiabilitiesReport.Outcomes;

import javafx.collections.ObservableMap;
import javafx.scene.chart.BarChart;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.ChartBase;
import reportGeneration.storage.IndexesStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.SettingsStorage;

public class LiabilitiesCharts extends ChartBase {
    private ObservableMap<String, Double> valuesCurrent;
    private ObservableMap<String, Double> valuesNonCurrent;
    private ObservableMap<String, Double> valuesEquity;

    public LiabilitiesCharts() {
        this.valuesCurrent = IndexesStorage.get("CurrentLiabilities").getValues();
        this.valuesNonCurrent = IndexesStorage.get("NonCurrentLiabilities").getValues();
        this.valuesEquity = IndexesStorage.get("EquityGeneral").getValues();
    }

    private String chartTitle() {
        Periods periods = Periods.getInstance();
        ObservableMap<String, String> settings = SettingsStorage.getInstance().getSettings();
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
