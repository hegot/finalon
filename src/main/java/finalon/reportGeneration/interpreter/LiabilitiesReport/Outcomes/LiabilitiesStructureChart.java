package finalon.reportGeneration.interpreter.LiabilitiesReport.Outcomes;

import finalon.entities.Item;
import finalon.reportGeneration.interpreter.ReusableComponents.interfaces.GetVal;
import finalon.reportGeneration.interpreter.ReusableComponents.interfaces.TableName;
import finalon.reportGeneration.storage.ItemsStorage;
import finalon.reportGeneration.storage.ResultsStorage;
import finalon.reportGeneration.storage.SettingsStorage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;

public class LiabilitiesStructureChart implements GetVal, TableName {
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
        Item parent = ItemsStorage.get("EquityAndLiabilities");
        this.equity = ItemsStorage.get("EquityGeneral");
        this.nonCurrent = ItemsStorage.get("NonCurrentLiabilities");
        this.current = ItemsStorage.get("CurrentLiabilities");
        this.period = period;
        this.totalVal = parent.getVal(period);
        this.equityVal = equity.getVal(period);
        this.currentVal = current.getVal(period);
        this.nonCurrentVal = nonCurrent.getVal(period);
    }

    public VBox get() {
        VBox vBox = new VBox();
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
            String title = "Chart 4. " + SettingsStorage.get("company") +
                    " Source of Finance structure in " + period;
            ResultsStorage.addPieChart(31, chart, title);
            vBox.getChildren().addAll(tableName(title), chart);
        }
        return vBox;
    }
}
