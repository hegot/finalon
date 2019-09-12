package reportGeneration.interpreter.AssetsLiabilitiesEquityAnalysis.AssetsReport.Outcomes;

import entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.helpers.Calc;
import reportGeneration.interpreter.ReusableComponents.helpers.TableName;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.SettingsStorage;

public class AssetStructureChart {

    private Item current;
    private Item nonCurrent;
    private String period;
    private Double totalVal;
    private Double currentVal;
    private Double nonCurrentVal;

    public AssetStructureChart(
            String period
    ) {
        this.nonCurrent = ItemsStorage.get("NonCurrentAssets");
        this.current = ItemsStorage.get("GeneralCurrentAssets");
        this.period = period;
        this.totalVal = ItemsStorage.get("AssetsGeneral").getVal(period);
        this.currentVal = current.getVal(period);
        this.nonCurrentVal = nonCurrent.getVal(period);
    }

    public VBox get(int weight) {
        VBox vBox = new VBox();
        final PieChart chart = new PieChart();
        if (totalVal != null && totalVal != 0) {
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            if (currentVal != null) {
                pieChartData.add(new PieChart.Data(
                        current.getName(),
                        Calc.part(currentVal, totalVal)
                ));
            }
            if (nonCurrentVal != null) {
                pieChartData.add(new PieChart.Data(
                        nonCurrent.getName(),
                        Calc.part(nonCurrentVal, totalVal)
                ));
            }
            chart.setData(pieChartData);
            String title = "Chart 3. " + SettingsStorage.get("company") + " Assets structure in " + period;
            ResultsStorage.addPieChart(weight, chart, title);
            vBox.getChildren().addAll(TableName.name(title), chart);
        }
        return vBox;
    }
}
