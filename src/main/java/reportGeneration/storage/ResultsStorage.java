package reportGeneration.storage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;

public class ResultsStorage {
    private static ObservableMap<Integer, ResultItem> items = FXCollections.observableHashMap();

    public static ObservableMap<Integer, ResultItem> getItems() {
        return items;
    }


    public static void addStr(int weight, String type, String val) {
        ResultItem<String> item = new ResultItem<>(val, type);
        items.put(weight, item);
    }

    public static void addTable(int weight, TwoDList val) {
        ResultItem<TwoDList> item = new ResultItem<TwoDList>(val, "table");
        items.put(weight, item);
    }

    public static void addScaleTable(int weight, TwoDList val) {
        ResultItem<TwoDList> item = new ResultItem<TwoDList>(val, "scaleTable");
        items.put(weight, item);
    }

    public static void addBarChart(int weight, BarChart<String, Number> bc) {
        ResultItem<BarChart> item = new ResultItem<BarChart>(bc, "barchart");
        items.put(weight, item);
    }

    public static void addPieChart(int weight, PieChart ch) {
        ResultItem<PieChart> item = new ResultItem<PieChart>(ch, "piechart");
        items.put(weight, item);
    }

    public static void addImg(int weight, String val) {
        ResultItem<String> item = new ResultItem<String>(val, "img");
        items.put(weight, item);
    }

}


