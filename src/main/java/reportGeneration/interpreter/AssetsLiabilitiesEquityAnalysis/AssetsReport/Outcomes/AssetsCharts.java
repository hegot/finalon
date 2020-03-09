package reportGeneration.interpreter.AssetsLiabilitiesEquityAnalysis.AssetsReport.Outcomes;

import entities.Item;
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
                ", in " + SettingsStorage.get("amount") + " " + SettingsStorage.get("defaultCurrency");
    }

    public VBox get(int weight) {
        List<Double> allVals = new ArrayList<>();
        for(String key : valuesCurrent.keySet()) {
            allVals.add(valuesCurrent.get(key));
        }
        for(String key : valuesNonCurrent.keySet()) {
            allVals.add(valuesNonCurrent.get(key));
        }
        BarChart<String, Number> bc = getChart(allVals);
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
        ResultsStorage.addBarChart(weight, bc, title);
        return vBox;
    }

}
