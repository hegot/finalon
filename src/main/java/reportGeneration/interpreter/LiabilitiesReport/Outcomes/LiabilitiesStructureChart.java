package reportGeneration.interpreter.LiabilitiesReport.Outcomes;

import entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import reportGeneration.interpreter.ReusableComponents.interfaces.GetVal;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.ResultsStorage;
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
        ItemsStorage stor = ItemsStorage.getInstance();
        Item parent = stor.get("EquityAndLiabilities");
        this.equity = stor.get("EquityGeneral");
        this.nonCurrent = stor.get("NonCurrentLiabilities");
        this.current = stor.get("CurrentLiabilities");
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
            String title = "Chart 4. " + SettingsStorage.getInstance().getSettings().get("company") +
                    " Source of Finance structure in " + period;
            chart.setTitle(title);
            ResultsStorage.addStr(30, "text", title);
            ResultsStorage.addPieChart(31, chart);
        }
        return chart;
    }
}
