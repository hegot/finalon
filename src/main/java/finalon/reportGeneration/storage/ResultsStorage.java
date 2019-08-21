package finalon.reportGeneration.storage;

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

    public static void addTable(int weight, TwoDList val, String title) {
        TitledItem<TwoDList> titledItem = new TitledItem<>(title, val);
        ResultItem<TitledItem> item = new ResultItem<>(titledItem, "table");
        items.put(weight, item);
    }

    public static void addScaleTable(int weight, TwoDList val, String title) {
        TitledItem<TwoDList> titledItem = new TitledItem<>(title, val);
        ResultItem<TitledItem<TwoDList>> item = new ResultItem<TitledItem<TwoDList>>(titledItem, "scaleTable");
        items.put(weight, item);
    }

    public static void addBarChart(int weight, BarChart<String, Number> bc, String title) {
        TitledItem<BarChart<String, Number>> titledItem = new TitledItem<>(title, bc);
        ResultItem<TitledItem> item = new ResultItem<>(titledItem, "barchart");
        items.put(weight, item);
    }

    public static void addPieChart(int weight, PieChart ch, String title) {
        TitledItem<PieChart> titledItem = new TitledItem<>(title, ch);
        ResultItem<TitledItem> item = new ResultItem<>(titledItem, "piechart");
        items.put(weight, item);
    }

    public static void addImg(int weight, String val) {
        ResultItem<String> item = new ResultItem<String>(val, "img");
        items.put(weight, item);
    }

}


