package interpreter.AssetsReport.Outcomes;

import entities.Item;
import finalonWindows.reusableComponents.ItemsTable.Periods;
import javafx.collections.ObservableMap;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class AssetsCharts extends OutcomeBase {
    private Periods periods;
    private ObservableMap<String, Double> valuesCurrent;
    private ObservableMap<String, Double> valuesNonCurrent;
    private ObservableMap<String, String> settings;

    public AssetsCharts(
            ObservableMap<String, String> settings,
            Periods periods,
            Item current,
            Item nonCurrent
    ) {
        this.settings = settings;
        this.periods = periods;
        valuesCurrent = current.getValues();
        valuesNonCurrent = nonCurrent.getValues();
    }

    public VBox get() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc =
                new BarChart<String, Number>(xAxis, yAxis);
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Current Assets");

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Non-current Assets");

        bc.setTitle("Chart 1. " + settings.get("company") +
                " Non-current and Current Assets between " +
                periods.getStart() + " - " + periods.getEnd() +
                " in " + settings.get("amount") + " " + settings.get("defaultCurrency")
        );
        yAxis.setLabel("Value");

        ArrayList<String> arr = periods.getPeriodArr();
        if (valuesCurrent.size() > 1) {
            for (String period : arr) {
                String date = formatDate(period);
                series1.getData().add(new XYChart.Data(date, valuesCurrent.get(period)));
            }
        }

        if (valuesNonCurrent.size() > 1) {
            for (String period : arr) {
                String date = formatDate(period);
                series2.getData().add(new XYChart.Data(date, valuesNonCurrent.get(period)));
            }
        }


        bc.getData().addAll(series1, series2);

        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(bc);
        return vBox;
    }

}
