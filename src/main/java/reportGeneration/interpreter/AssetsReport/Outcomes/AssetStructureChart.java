package reportGeneration.interpreter.AssetsReport.Outcomes;

import entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.chart.PieChart;
import reportGeneration.interpreter.ReusableComponents.interfaces.GetVal;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.SettingsStorage;

public class AssetStructureChart implements GetVal {

    private Item current;
    private Item nonCurrent;
    private String period;
    private Double totalVal;
    private Double currentVal;
    private Double nonCurrentVal;

    public AssetStructureChart(
            String period
    ) {
        ItemsStorage stor = ItemsStorage.getInstance();
        this.nonCurrent = stor.get("NonCurrentAssets");
        this.current = stor.get("GeneralCurrentAssets");
        this.period = period;
        this.totalVal = stor.get("AssetsGeneral").getVal(period);
        this.currentVal = current.getVal(period);
        this.nonCurrentVal = nonCurrent.getVal(period);
    }

    public PieChart get() {
        ObservableMap<String, String> settings = SettingsStorage.getInstance().getSettings();
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
            chart.setTitle("Chart 3. " + settings.get("company") + " Assets structure in " + period);
        }
        return chart;
    }
}
