package reportGeneration.interpreter.ReusableComponents;

import database.setting.DbSettingHandler;
import globalReusables.Setting;
import javafx.collections.ObservableMap;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import reportGeneration.interpreter.ReusableComponents.helpers.Formatter;
import reportGeneration.storage.Periods;

import java.util.ArrayList;
import java.util.Collections;

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
        series.getData().addAll(getData(values));
        return series;
    }

    private ArrayList<XYChart.Data> getData(ObservableMap<String, Double> values) {
        ArrayList<XYChart.Data> data = new ArrayList<XYChart.Data>();
        if (values.size() > 1) {
            String date;
            for (String period : Periods.getPeriodArr()) {
                date = Formatter.formatDate(period);
                if (values.get(period) != null) {
                    data.add(new XYChart.Data(date, values.get(period)));
                }
            }
        }
        String order = DbSettingHandler.getSetting(Setting.yearOrder);
        if (order.equals("DESCENDING")) {
            Collections.reverse(data);
        }
        return data;
    }
}
