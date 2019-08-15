package reportGeneration.interpreter.AssetsReport.Outcomes;

import entities.Item;
import globalReusables.LabelWrap;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.interfaces.SrtuctureItemsLoop;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.ResultsStorage;

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
        String str = "";
        if (currentVal != null && currentVal > 0) {
            str = loopItems(currentItems,
                    currentVal,
                    "The most significant items of the current assets were ",
                    " etc.",
                    period);
        }
        if (nonCurrentVal != null && nonCurrentVal > 0) {
            str = loopItems(nonCurrentItems,
                    nonCurrentVal,
                    "The following non-current assets had the highest values: ",
                    " while the other items did not play a significant role. ",
                    period);
        }
        vBox.getChildren().add(labelWrap(str));
        ResultsStorage.addStr(14, "text", str);
        return vBox;
    }

    private Label firstMessage() {
        String str = "At the end of " + formatDate(period) + " the assets consisted of ";
        if (nonCurrentVal != null) {
            str = str + partStr(nonCurrentVal, totalVal) + " non-current assets";
        }
        if (currentVal != null) {
            str = str + " and " + partStr(currentVal, totalVal) + " of current assets. ";
        }
        ResultsStorage.addStr(13, "text", str);
        return labelWrap(str);
    }
}
