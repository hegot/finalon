package reportGeneration.interpreter.LiabilitiesReport.Outcomes;

import entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import reportGeneration.SettingsStorage;
import reportGeneration.interpreter.ReusableComponents.interfaces.GetVal;

public class LiabilitiesStructureChart implements GetVal {
    private Item equity;
    private Item current;
    private Item nonCurrent;
    private String period;
    private Double totalVal;
    private Double equityVal;
    private Double currentVal;
    private Double nonCurrentVal;

    public LiabilitiesStructureChart(
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
            chart.setTitle("Chart 4. " + SettingsStorage.getSettings().get("company") + " Source of Finance structure in " + period);
        }
        return chart;
    }
}
