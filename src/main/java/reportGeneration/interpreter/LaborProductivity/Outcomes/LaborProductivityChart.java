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

import java.util.ArrayList;
import java.util.List;

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
            List<Double> allVals = new ArrayList<>();
            for (String key : laborProductivity.getPeriods().keySet()) {
                allVals.add(laborProductivity.getPeriods().get(key));
            }
            BarChart<String, Number> bc = getChart(allVals);
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
