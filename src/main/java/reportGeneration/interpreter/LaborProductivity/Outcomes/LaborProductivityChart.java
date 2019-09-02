package reportGeneration.interpreter.LaborProductivity.Outcomes;

import entities.Formula;
import javafx.scene.chart.BarChart;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.ChartBase;
import reportGeneration.interpreter.ReusableComponents.helpers.TableName;
import reportGeneration.storage.FormulaStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.SettingsStorage;

public class LaborProductivityChart extends ChartBase {
    private Formula laborProductivity;

    public LaborProductivityChart() {
        this.laborProductivity = FormulaStorage.get("LaborProductivity");
    }

    private String chartTitle() {
        return "Chart 6. " + SettingsStorage.get("company") +
                " Labor productivity between " +
                Periods.getStart() + " - " + Periods.getEnd() +
                " in " + SettingsStorage.get("defaultCurrency") + " per person.";
    }

    public VBox get(int weight) {
        VBox vBox = new VBox(20);
        if (laborProductivity != null && laborProductivity.getPeriods().size() > 0) {
            BarChart<String, Number> bc = getChart();
            bc.getData().addAll(
                    getSeries("Labor Productivity", laborProductivity.getPeriods())
            );
            String title = chartTitle();
            vBox.getChildren().addAll(TableName.name(title), bc);
            ResultsStorage.addBarChart(weight, bc, title);
        }
        return vBox;
    }

}
