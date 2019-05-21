package interpreter.AssetsReport.Outcomes;

import entities.Item;
import interpreter.ReusableComponents.GetVal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.chart.PieChart;

public class AssetStructureChart extends GetVal {

    private Item current;
    private Item nonCurrent;
    private String period;
    private Double totalVal;
    private Double currentVal;
    private Double nonCurrentVal;
    private ObservableMap<String, String> settings;

    public AssetStructureChart(
            ObservableMap<String, String> settings,
            Item parent,
            Item current,
            Item nonCurrent,
            String period
    ) {
        this.nonCurrent = nonCurrent;
        this.current = current;
        this.period = period;
        this.totalVal = getVal(parent, period);
        this.currentVal = getVal(current, period);
        this.nonCurrentVal = getVal(nonCurrent, period);
        this.settings = settings;
    }

    public PieChart get() {
        ObservableList<PieChart.Data> pieChartData =
        FXCollections.observableArrayList(
                new PieChart.Data(current.getName(), part(currentVal, totalVal)),
                new PieChart.Data(nonCurrent.getName(), part(nonCurrentVal, totalVal))
        );
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Chart 3. " + settings.get("company") + "Assets structure in " + period);
        return chart;
    }
}
