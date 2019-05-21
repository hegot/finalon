package interpreter.AssetsReport.Outcomes;

import entities.Item;
import finalonWindows.reusableComponents.ItemsTable.Periods;
import interpreter.ReusableComponents.ChartBase;
import javafx.collections.ObservableMap;
import javafx.scene.chart.BarChart;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class AssetsCharts extends ChartBase {
    private Periods periods;
    private ObservableMap<String, Double> valuesCurrent;
    private ObservableMap<String, Double> valuesNonCurrent;
    private ObservableMap<String, String> settings;
    private ArrayList<String> periodsArr;

    public AssetsCharts(
            ObservableMap<String, String> settings,
            Periods periods,
            Item current,
            Item nonCurrent
    ) {
        super(periods.getPeriodArr());
        this.periodsArr = periods.getPeriodArr();
        this.settings = settings;
        this.periods = periods;
        this.valuesCurrent = current.getValues();
        this.valuesNonCurrent = nonCurrent.getValues();
    }

    private String chartTitle() {
        return "Chart 1. " + settings.get("company") +
                " Non-current and Current Assets between " +
                periods.getStart() + " - " + periods.getEnd() +
                " in " + settings.get("amount") + " " + settings.get("defaultCurrency");
    }

    public VBox get() {
        BarChart<String, Number> bc = getChart(chartTitle());
        bc.getData().addAll(
                getSeries("Current Assets", valuesCurrent),
                getSeries("Non Current Assets", valuesNonCurrent)
        );
        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(bc);
        return vBox;
    }

}
