package reportGeneration.interpreter.AssetsLiabilitiesEquityAnalysis.LiabilitiesReport.Outcomes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.chart.BarChart;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.ChartBase;
import reportGeneration.interpreter.ReusableComponents.helpers.TableName;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.SettingsStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LiabilitiesCharts extends ChartBase {
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
                ", in " + SettingsStorage.get("amount") + " " + SettingsStorage.get("defaultCurrency");
    }

    public VBox get(int weight) {
        List<Double> allVals = new ArrayList<>();
        for(String key : valuesEquity.keySet()) {
            allVals.add(valuesEquity.get(key));
        }
        for(String key : valuesCurrent.keySet()) {
            allVals.add(valuesCurrent.get(key));
        }
        for(String key : valuesNonCurrent.keySet()) {
            allVals.add(valuesNonCurrent.get(key));
        }
        BarChart<String, Number> bc = getChart(allVals);
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
        vBox.getChildren().addAll(TableName.name(title), bc);
        ResultsStorage.addBarChart(weight, bc, title);

        return vBox;
    }

}
