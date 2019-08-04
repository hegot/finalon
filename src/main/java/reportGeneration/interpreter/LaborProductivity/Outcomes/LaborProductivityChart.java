package reportGeneration.interpreter.LaborProductivity.Outcomes;

import entities.Formula;
import javafx.collections.ObservableMap;
import javafx.scene.chart.BarChart;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.ChartBase;
import reportGeneration.interpreter.ReusableComponents.interfaces.TableName;
import reportGeneration.storage.FormulaStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.SettingsStorage;

public class LaborProductivityChart extends ChartBase implements TableName {
    private Formula laborProductivity;

    public LaborProductivityChart() {
        this.laborProductivity = FormulaStorage.getInstance().get("LaborProductivity");
    }

    private String chartTitle() {
        ObservableMap<String, String> settings = SettingsStorage.getInstance().getSettings();
        Periods periods = Periods.getInstance();
        return "Chart 6. " + settings.get("company") +
                " Labor productivity between " +
                periods.getStart() + " - " + periods.getEnd() +
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
            ResultsStorage.addBarChart(weight, bc);
            weight++;
            ResultsStorage.addStr(weight, "h2",  title);

        }
        return vBox;
    }

}
