package finalon.reportGeneration.interpreter.LaborProductivity.Outcomes;

import finalon.entities.Formula;
import javafx.collections.ObservableMap;
import javafx.scene.chart.BarChart;
import javafx.scene.layout.VBox;
import finalon.reportGeneration.interpreter.ReusableComponents.ChartBase;
import finalon.reportGeneration.interpreter.ReusableComponents.interfaces.TableName;
import finalon.reportGeneration.storage.FormulaStorage;
import finalon.reportGeneration.storage.Periods;
import finalon.reportGeneration.storage.ResultsStorage;
import finalon.reportGeneration.storage.SettingsStorage;

public class LaborProductivityChart extends ChartBase implements TableName {
    private Formula laborProductivity;

    public LaborProductivityChart() {
        this.laborProductivity = FormulaStorage.get("LaborProductivity");
    }

    private String chartTitle() {
        ObservableMap<String, String> settings = SettingsStorage.getSettings();
        return "Chart 6. " + settings.get("company") +
                " Labor productivity between " +
                Periods.getStart() + " - " + Periods.getEnd() +
                " in " + settings.get("defaultCurrency") + " per person.";
    }

    public VBox get(int weight) {
        VBox vBox = new VBox(20);
        if (laborProductivity != null && laborProductivity.getPeriods().size() > 0) {
            BarChart<String, Number> bc = getChart();
            bc.getData().addAll(
                    getSeries("Labor Productivity", laborProductivity.getPeriods())
            );
            String title = chartTitle();
            vBox.getChildren().addAll(tableName(title), bc);
            ResultsStorage.addBarChart(weight, bc, title);
        }
        return vBox;
    }

}
