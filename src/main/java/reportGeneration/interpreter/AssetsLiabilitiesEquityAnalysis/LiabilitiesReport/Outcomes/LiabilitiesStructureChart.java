package reportGeneration.interpreter.AssetsLiabilitiesEquityAnalysis.LiabilitiesReport.Outcomes;

import entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.helpers.Calc;
import reportGeneration.interpreter.ReusableComponents.helpers.Formatter;
import reportGeneration.interpreter.ReusableComponents.helpers.TableName;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.SettingsStorage;

public class LiabilitiesStructureChart {
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
        this.current = ItemsStorage.get("CurrentLiabilitiesGeneral");
        this.period = period;
        this.totalVal = parent.getVal(period);
        this.equityVal = equity.getVal(period);
        this.currentVal = current.getVal(period);
        this.nonCurrentVal = nonCurrent.getVal(period);
    }

    public VBox get(int weight) {
        VBox vBox = new VBox();
        final PieChart chart = new PieChart();
        chart.setStyle("-fx-font-size: 15px;");
        chart.setMinHeight(600);
        if (totalVal != null && totalVal != 0) {
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            if (currentVal != null) {
                Double val = Calc.part(currentVal, totalVal);
                pieChartData.add(new PieChart.Data(
                        current.getName() + " - " + Formatter.finalNumberFormat(val) + "%",
                        val
                ));
            }
            if (nonCurrentVal != null) {
                Double val = Calc.part(nonCurrentVal, totalVal);
                pieChartData.add(new PieChart.Data(
                        nonCurrent.getName() + " - " + Formatter.finalNumberFormat(val) + "%",
                        val
                ));
            }
            if (equityVal != null) {
                Double val = Calc.part(equityVal, totalVal);
                pieChartData.add(new PieChart.Data(
                        equity.getName() + " - " + Formatter.finalNumberFormat(val) + "%",
                        val
                ));
            }
            chart.setData(pieChartData);
            String title = "Chart 4. " + SettingsStorage.get("company") +
                    " Source of Finance structure in " + period;
            ResultsStorage.addPieChart(weight, chart, title);
            vBox.getChildren().addAll(TableName.name(title), chart);
        }
        return vBox;
    }
}
