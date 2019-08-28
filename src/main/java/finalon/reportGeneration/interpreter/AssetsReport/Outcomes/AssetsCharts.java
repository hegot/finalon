package finalon.reportGeneration.interpreter.AssetsReport.Outcomes;

import finalon.entities.Item;
import finalon.reportGeneration.interpreter.ReusableComponents.ChartBase;
import finalon.reportGeneration.interpreter.ReusableComponents.helpers.TableName;
import finalon.reportGeneration.storage.ItemsStorage;
import finalon.reportGeneration.storage.Periods;
import finalon.reportGeneration.storage.ResultsStorage;
import finalon.reportGeneration.storage.SettingsStorage;
import javafx.collections.ObservableMap;
import javafx.scene.chart.BarChart;
import javafx.scene.layout.VBox;

public class AssetsCharts extends ChartBase {
    private ObservableMap<String, Double> valuesCurrent;
    private ObservableMap<String, Double> valuesNonCurrent;

    public AssetsCharts() {
        Item current = ItemsStorage.get("GeneralCurrentAssets");
        Item nonCurrent = ItemsStorage.get("NonCurrentAssets");
        this.valuesCurrent = current.getValues();
        this.valuesNonCurrent = nonCurrent.getValues();
    }

    private String chartTitle() {
        return "Chart 1. " + SettingsStorage.get("company") +
                " Non-current and Current Assets between " +
                Periods.getStart() + " - " + Periods.getEnd() +
                " in " + SettingsStorage.get("amount") + " " + SettingsStorage.get("defaultCurrency");
    }

    public VBox get() {
        BarChart<String, Number> bc = getChart();
        String title = chartTitle();
        bc.setAnimated(false);
        bc.getData().addAll(
                getSeries("Current Assets", valuesCurrent),
                getSeries("Non Current Assets", valuesNonCurrent)
        );
        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(TableName.name(title), bc);
        bc.applyCss();
        bc.setAnimated(false);
        bc.layout();
        ResultsStorage.addBarChart(8, bc, title);
        return vBox;
    }

}
