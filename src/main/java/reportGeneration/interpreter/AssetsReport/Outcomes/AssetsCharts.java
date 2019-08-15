package reportGeneration.interpreter.AssetsReport.Outcomes;

import entities.Item;
import javafx.collections.ObservableMap;
import javafx.scene.chart.BarChart;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.ChartBase;
import reportGeneration.interpreter.ReusableComponents.interfaces.TableName;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.SettingsStorage;

public class AssetsCharts extends ChartBase implements TableName {
    private ObservableMap<String, Double> valuesCurrent;
    private ObservableMap<String, Double> valuesNonCurrent;

    public AssetsCharts() {
        Item current = ItemsStorage.get("GeneralCurrentAssets");
        Item nonCurrent = ItemsStorage.get("NonCurrentAssets");
        this.valuesCurrent = current.getValues();
        this.valuesNonCurrent = nonCurrent.getValues();
    }

    private String chartTitle() {
        ObservableMap<String, String> settings = SettingsStorage.getSettings();
        return "Chart 1. " + settings.get("company") +
                " Non-current and Current Assets between " +
                Periods.getStart() + " - " + Periods.getEnd() +
                " in " + settings.get("amount") + " " + settings.get("defaultCurrency");
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
        vBox.getChildren().addAll(tableName(title), bc);
        bc.applyCss();
        bc.setAnimated(false);
        bc.layout();

        ResultsStorage.addStr(8, "h2", title);
        ResultsStorage.addBarChart(8, bc);

        return vBox;
    }

}
