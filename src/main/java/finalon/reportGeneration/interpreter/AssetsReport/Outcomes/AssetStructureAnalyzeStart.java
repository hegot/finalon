package finalon.reportGeneration.interpreter.AssetsReport.Outcomes;

import finalon.entities.Item;
import finalon.globalReusables.LabelWrap;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import finalon.reportGeneration.interpreter.ReusableComponents.interfaces.SrtuctureItemsLoop;
import finalon.reportGeneration.storage.ItemsStorage;
import finalon.reportGeneration.storage.Periods;
import finalon.reportGeneration.storage.ResultsStorage;

public class AssetStructureAnalyzeStart implements SrtuctureItemsLoop, LabelWrap {
    private Item parent;
    private String period;
    private Double totalVal;
    private Double currentVal;
    private Double nonCurrentVal;
    private ObservableList<Item> currentItems;
    private ObservableList<Item> nonCurrentItems;

    public AssetStructureAnalyzeStart(
            ObservableList<Item> currentItems,
            ObservableList<Item> nonCurrentItems
    ) {
        Item current = ItemsStorage.get("GeneralCurrentAssets");
        Item nonCurrent = ItemsStorage.get("NonCurrentAssets");
        this.parent = ItemsStorage.get("AssetsGeneral");
        this.period = Periods.startKey();
        this.currentItems = currentItems;
        this.nonCurrentItems = nonCurrentItems;
        this.totalVal = parent.getVal(period);
        this.currentVal = current.getVal(period);
        this.nonCurrentVal = nonCurrent.getVal(period);
    }


    public VBox get() {
        VBox vBox = new VBox(10);
        if (this.parent.getValues().size() > 1) {
            vBox.getChildren().add(firstMessage());
        }
        StringBuilder str = new StringBuilder();
        if (currentVal != null && currentVal > 0) {
            str.append(loopItems(currentItems,
                    currentVal,
                    "The most significant items of the current assets were ",
                    " etc.",
                    period));
        }
        if (nonCurrentVal != null && nonCurrentVal > 0) {
            str.append(loopItems(nonCurrentItems,
                    nonCurrentVal,
                    "The following non-current assets had the highest values: ",
                    " while the other items did not play a significant role. ",
                    period));
        }
        vBox.getChildren().add(labelWrap(str.toString()));
        ResultsStorage.addStr(14, "text", str.toString());
        return vBox;
    }

    private Label firstMessage() {
        StringBuilder str = new StringBuilder();
        str.append("At the end of " + formatDate(period) + " the assets consisted of ");
        if (nonCurrentVal != null) {
            str.append(partStr(nonCurrentVal, totalVal) + " non-current assets");
        }
        if (currentVal != null) {
            str.append(" and " + partStr(currentVal, totalVal) + " of current assets. ");
        }
        ResultsStorage.addStr(13, "text", str.toString());
        return labelWrap(str.toString());
    }
}
