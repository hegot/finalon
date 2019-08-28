package finalon.reportGeneration.interpreter.LaborProductivity.Outcomes;

import finalon.entities.Formula;
import finalon.reportGeneration.interpreter.ReusableComponents.ChartBase;
import finalon.reportGeneration.interpreter.ReusableComponents.interfaces.TableName;
import finalon.reportGeneration.storage.FormulaStorage;
import finalon.reportGeneration.storage.Periods;
import finalon.reportGeneration.storage.ResultsStorage;
import finalon.reportGeneration.storage.SettingsStorage;
import javafx.scene.chart.BarChart;
import javafx.scene.layout.VBox;

public class LaborProductivityChart extends ChartBase implements TableName {
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
            vBox.getChildren().addAll(tableName(title), bc);
            ResultsStorage.addBarChart(weight, bc, title);
        }
        return vBox;
    }

}
