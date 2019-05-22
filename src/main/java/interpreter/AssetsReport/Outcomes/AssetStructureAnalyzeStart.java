package interpreter.AssetsReport.Outcomes;

import entities.Item;
import interpreter.ReusableComponents.SrtuctureItemsLoop;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AssetStructureAnalyzeStart implements SrtuctureItemsLoop {
    private Item parent;
    private String period;
    private Double totalVal;
    private Double currentVal;
    private Double nonCurrentVal;
    private ObservableList<Item> currentItems;
    private ObservableList<Item> nonCurrentItems;

    public AssetStructureAnalyzeStart(
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
        this.totalVal = getVal(parent, period);
        this.currentVal = getVal(current, period);
        this.nonCurrentVal = getVal(nonCurrent, period);
    }


    public VBox get() {
        VBox vBox = new VBox(10);
        if (this.parent.getValues().size() > 1) {
            vBox.getChildren().add(firstMessage());
        }
        if (currentVal != null && currentVal > 0) {
            vBox.getChildren().add(
                    loopItems(currentItems,
                            currentVal,
                            "The most significant items of the current assets were ",
                            " etc.",
                            period)
            );
        }
        if (nonCurrentVal != null && nonCurrentVal > 0) {
            vBox.getChildren().add(
                    loopItems(nonCurrentItems,
                            nonCurrentVal,
                            "The following noncurrent assets had the highest values: ",
                            " while the other items did not play a significant role.",
                            period)
            );
        }
        return vBox;
    }

    private Label firstMessage() {
        String str = "At the end of " + formatDate(period) + " the assets consisted of ";
        if (nonCurrentVal != null) {
            str = str + partStr(nonCurrentVal, totalVal) + " non-current assets";
        }
        if (currentVal != null) {
            str = str + " and " + partStr(currentVal, totalVal) + " of current assets.";
        }
        Label label = new Label(str);
        label.getStyleClass().add("report-text-small");
        label.setWrapText(true);
        return label;
    }
}
