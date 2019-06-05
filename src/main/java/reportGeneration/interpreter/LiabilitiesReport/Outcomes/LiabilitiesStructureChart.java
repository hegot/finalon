package reportGeneration.interpreter.LiabilitiesReport.Outcomes;

import entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import reportGeneration.interpreter.ReusableComponents.interfaces.GetVal;
import reportGeneration.storage.IndexesStorage;
import reportGeneration.storage.SettingsStorage;

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
            String period
    ) {
        Item parent = IndexesStorage.get("EquityAndLiabilities");
        this.equity = IndexesStorage.get("EquityGeneral");
        this.nonCurrent = IndexesStorage.get("NonCurrentLiabilities");
        this.current = IndexesStorage.get("CurrentLiabilities");
        this.period = period;
        this.totalVal = parent.getVal(period);
        this.equityVal = equity.getVal(period);
        this.currentVal = current.getVal(period);
        this.nonCurrentVal = nonCurrent.getVal(period);
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
            chart.setTitle("Chart 4. " + SettingsStorage.getSettings().get("company") +
                    " Source of Finance structure in " + period);
        }
        return chart;
    }
}
