package finalon.reportGeneration.interpreter.LiabilitiesReport.Outcomes;

import finalon.reportGeneration.interpreter.ReusableComponents.ChartBase;
import finalon.reportGeneration.interpreter.ReusableComponents.interfaces.TableName;
import finalon.reportGeneration.storage.ItemsStorage;
import finalon.reportGeneration.storage.Periods;
import finalon.reportGeneration.storage.ResultsStorage;
import finalon.reportGeneration.storage.SettingsStorage;
import javafx.collections.ObservableMap;
import javafx.scene.chart.BarChart;
import javafx.scene.layout.VBox;

public class LiabilitiesCharts extends ChartBase implements TableName {
    private ObservableMap<String, Double> valuesCurrent;
    private ObservableMap<String, Double> valuesNonCurrent;
    private ObservableMap<String, Double> valuesEquity;

    public LiabilitiesCharts() {
        this.valuesCurrent = ItemsStorage.get("CurrentLiabilities").getValues();
        this.valuesNonCurrent = ItemsStorage.get("NonCurrentLiabilities").getValues();
        this.valuesEquity = ItemsStorage.get("EquityGeneral").getValues();
    }

    private String chartTitle() {
        return "Chart 2. " + SettingsStorage.get("company") +
                " Source of finance between " +
                Periods.getStart() + " - " + Periods.getEnd() +
                " in " + SettingsStorage.get("amount") + " " + SettingsStorage.get("defaultCurrency");
    }

    public VBox get() {
        BarChart<String, Number> bc = getChart();
        if (valuesEquity.size() > 0) bc.getData().add(
                getSeries("Equity", valuesEquity)
        );
        if (valuesCurrent.size() > 0) bc.getData().add(
                getSeries("Non Current Liabilities", valuesNonCurrent)
        );
        if (valuesNonCurrent.size() > 0) bc.getData().add(
                getSeries("Current Liabilities", valuesCurrent)
        );
        VBox vBox = new VBox(20);
        String title = chartTitle();
        vBox.getChildren().addAll(tableName(title), bc);
        ResultsStorage.addBarChart(25, bc, title);

        return vBox;
    }

}
