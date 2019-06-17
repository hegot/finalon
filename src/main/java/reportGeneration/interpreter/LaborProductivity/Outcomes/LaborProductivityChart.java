package reportGeneration.interpreter.LaborProductivity.Outcomes;

import entities.Formula;
import javafx.collections.ObservableMap;
import javafx.scene.chart.BarChart;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.ChartBase;
import reportGeneration.storage.FormulaStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.SettingsStorage;

public class LaborProductivityChart extends ChartBase {
    private Formula laborProductivity;

    public LaborProductivityChart() {
        this.laborProductivity = FormulaStorage.getInstance().getItemByCode("LaborProductivity");
    }

    private String chartTitle() {
        ObservableMap<String, String> settings = SettingsStorage.getSettings();
        Periods periods = Periods.getInstance();
        return "Chart 6. " + settings.get("company") +
                " Labor productivity between " +
                periods.getStart() + " - " + periods.getEnd() +
                " in " + settings.get("defaultCurrency") + " per person. ";
    }

    public VBox get() {
        VBox vBox = new VBox(20);
        if (laborProductivity.getPeriods().size() > 0) {
            BarChart<String, Number> bc = getChart(chartTitle());
            bc.getData().addAll(
                    getSeries("Current Assets", laborProductivity.getPeriods())
            );
            vBox.getChildren().addAll(bc);
        }
        return vBox;
    }

}
