package interpreter.AssetsReport.Outcomes;

import entities.Item;
import interpreter.JsCalcHelper;
import interpreter.ReusableComponents.GetVal;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class AssetStructureAnalyze extends GetVal implements JsCalcHelper{
    private Item parent;
    private String period;
    private Double totalVal;
    private Double currentVal;
    private Double nonCurrentVal;
    private ObservableList<Item> currentItems;
    private ObservableList<Item> nonCurrentItems;

    public AssetStructureAnalyze(
            Item parent,
            Item current,
            Item nonCurrent,
            ObservableList<Item> currentItems,
            ObservableList<Item> nonCurrentItems,
            String period
    ) {
        this.parent = parent;
        this.period = period;
        this.currentItems = currentItems;
        this.nonCurrentItems = nonCurrentItems;
        this.totalVal = getVal(parent);
        this.currentVal = getVal(current);
        this.nonCurrentVal = getVal(nonCurrent);
    }

    private Double getVal(Item item) {
        ObservableMap<String, Double> values = item.getValues();
        Double val = null;
        if (values.size() > 0) {
            val = values.get(period);
        }
        return val;
    }


    private Label loopItems(ObservableList<Item> items, Double totall, String title) {
        SortedMap<Double, String> treeMap = new TreeMap<>();
        for (Item item : items) {
            Double val = getVal(item);
            if (val != null) {
                Double part = part(val, totall);
                treeMap.put(part, item.getName());
            }
        }
        StringBuilder result = new StringBuilder("");
        for (Map.Entry<Double, String> entry : treeMap.entrySet()) {
            Double key = entry.getKey();
            System.out.println(entry.getValue());
            result.append(entry.getValue() + " (" + format(key) + " of total assets), ");
        }
        Label label = new Label(title + result + ", etc.");
        label.getStyleClass().add("report-text-small");
        label.setWrapText(true);
        return label;
    }

    public VBox get() {
        VBox vBox = new VBox(10);
        if (this.parent.getValues().size() > 1) {
            vBox.getChildren().add(firstMessage());
        }
        vBox.getChildren().addAll(
            loopItems(currentItems, currentVal, "Total Current assets composed mostly of "),
            loopItems(nonCurrentItems, nonCurrentVal, "The most significant items of the Non Current assets")
        );
        return vBox;
    }

    private Label firstMessage() {
        String str = "At the end of " + formatDate(period) + " the assets consisted of ";
        if(nonCurrentVal != null){
            str = str + partStr(nonCurrentVal, totalVal) + " non-current assets";
        }
        if(currentVal != null) {
            str = str + " and " + partStr(currentVal, totalVal) + " of current assets.";
        }
        Label label = new Label(str);
        label.getStyleClass().add("report-text-small");
        label.setWrapText(true);
        return label;
    }
}
