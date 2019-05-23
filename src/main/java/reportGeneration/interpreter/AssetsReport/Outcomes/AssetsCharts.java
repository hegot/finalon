package reportGeneration.interpreter.AssetsReport.Outcomes;

import entities.Item;
import javafx.collections.ObservableMap;
import javafx.scene.chart.BarChart;
import javafx.scene.layout.VBox;
import reportGeneration.Periods;
import reportGeneration.SettingsStorage;
import reportGeneration.interpreter.ReusableComponents.ChartBase;

public class AssetsCharts extends ChartBase {
    private Periods periods;
    private ObservableMap<String, Double> valuesCurrent;
    private ObservableMap<String, Double> valuesNonCurrent;

    public AssetsCharts(
            Periods periods,
            Item current,
            Item nonCurrent
    ) {
        super(periods.getPeriodArr());
        this.periods = periods;
        this.valuesCurrent = current.getValues();
        this.valuesNonCurrent = nonCurrent.getValues();
    }

    private String chartTitle() {
        ObservableMap<String, String> settings = SettingsStorage.getSettings();
        return "Chart 1. " + settings.get("company") +
                " Non-current and Current Assets between " +
                periods.getStart() + " - " + periods.getEnd() +
                " in " + settings.get("amount") + " " + settings.get("defaultCurrency");
    }

    public VBox get() {
        BarChart<String, Number> bc = getChart(chartTitle());
        bc.getData().addAll(
                getSeries("Current Assets", valuesCurrent),
                getSeries("Non Current Assets", valuesNonCurrent)
        );
        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(bc);
        return vBox;
    }

}
