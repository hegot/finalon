package finalon.reportGeneration.interpreter.AssetsReport.Outcomes;

import finalon.entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import finalon.reportGeneration.interpreter.ReusableComponents.interfaces.GetVal;
import finalon.reportGeneration.interpreter.ReusableComponents.interfaces.TableName;
import finalon.reportGeneration.storage.ItemsStorage;
import finalon.reportGeneration.storage.ResultsStorage;
import finalon.reportGeneration.storage.SettingsStorage;

public class AssetStructureChart implements GetVal, TableName {

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

    public VBox get() {
        VBox vBox = new VBox();
        ObservableMap<String, String> settings = SettingsStorage.getSettings();
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
            chart.setData(pieChartData);
            String title = "Chart 3. " + settings.get("company") + " Assets structure in " + period;
            ResultsStorage.addPieChart(16, chart, title);
            vBox.getChildren().addAll(tableName(title), chart);
        }
        return vBox;
    }
}