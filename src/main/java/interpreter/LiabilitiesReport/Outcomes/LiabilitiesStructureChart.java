package interpreter.LiabilitiesReport.Outcomes;

import entities.Item;
import interpreter.ReusableComponents.GetVal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.chart.PieChart;

public class LiabilitiesStructureChart implements GetVal {
    private Item equity;
    private Item current;
    private Item nonCurrent;
    private String period;
    private Double totalVal;
    private Double equityVal;
    private Double currentVal;
    private Double nonCurrentVal;
    private ObservableMap<String, String> settings;

    public LiabilitiesStructureChart(
            ObservableMap<String, String> settings,
            Item parent,
            Item equity,
            Item current,
            Item nonCurrent,
            String period
    ) {
        this.equity = equity;
        this.nonCurrent = nonCurrent;
        this.current = current;
        this.period = period;
        this.totalVal = getVal(parent, period);
        this.equityVal = getVal(equity, period);
        this.currentVal = getVal(current, period);
        this.nonCurrentVal = getVal(nonCurrent, period);
        this.settings = settings;
    }

    public PieChart get() {
        final PieChart chart = new PieChart();
        if (totalVal != null && totalVal != 0) {
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            if (currentVal != null) {
                pieChartData.add(new PieChart.Data(
                        current.getName(),
                        part(currentVal, totalVal)
                ));
            }
            if (nonCurrentVal != null) {
                pieChartData.add(new PieChart.Data(
                        nonCurrent.getName(),
                        part(nonCurrentVal, totalVal)
                ));
            }
            if (equityVal != null) {
                pieChartData.add(new PieChart.Data(
                        equity.getName(),
                        part(equityVal, totalVal)
                ));
            }
            chart.setData(pieChartData);
            chart.setTitle("Chart 4. " + settings.get("company") + " Source of Finance structure in " + period);
        }
        return chart;
    }
}
