package reportGeneration.interpreter.LiabilitiesReport.Outcomes;

import javafx.collections.ObservableMap;
import javafx.scene.chart.BarChart;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.ChartBase;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.SettingsStorage;

public class LiabilitiesCharts extends ChartBase {
    private ObservableMap<String, Double> valuesCurrent;
    private ObservableMap<String, Double> valuesNonCurrent;
    private ObservableMap<String, Double> valuesEquity;

    public LiabilitiesCharts() {
        ItemsStorage stor = ItemsStorage.getInstance();
        this.valuesCurrent = stor.get("CurrentLiabilities").getValues();
        this.valuesNonCurrent = stor.get("NonCurrentLiabilities").getValues();
        this.valuesEquity = stor.get("EquityGeneral").getValues();
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
        if (valuesEquity.size() > 0) bc.getData().add(
                getSeries("Equity", valuesEquity)
        );
        if (valuesCurrent.size() > 0) bc.getData().add(
                getSeries("Non Current Liabilities", valuesNonCurrent)
        );
        if (valuesNonCurrent.size() > 0) bc.getData().add(
                getSeries("Current Liabilities", valuesCurrent)
        );
        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(bc);
        return vBox;
    }

}
