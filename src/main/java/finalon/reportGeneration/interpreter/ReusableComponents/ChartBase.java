package finalon.reportGeneration.interpreter.ReusableComponents;

import finalon.reportGeneration.interpreter.ReusableComponents.helpers.Formatter;
import finalon.reportGeneration.storage.Periods;
import javafx.collections.ObservableMap;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class ChartBase {


    protected BarChart<String, Number> getChart() {
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc = new BarChart<String, Number>(
                new CategoryAxis(),
                yAxis
        );
        yAxis.setLabel("Value");
        return bc;
    }

    protected XYChart.Series getSeries(String label, ObservableMap<String, Double> values) {
        XYChart.Series series = new XYChart.Series();
        series.setName(label);
        if (values.size() > 1) {
            for (String period : Periods.getPeriodArr()) {
                String date = Formatter.formatDate(period);
                if (values.get(period) != null) {
                    series.getData().add(new XYChart.Data(date, values.get(period)));
                }

            }
        }
        return series;
    }
}
