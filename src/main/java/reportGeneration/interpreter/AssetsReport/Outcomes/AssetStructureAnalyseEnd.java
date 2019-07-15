package reportGeneration.interpreter.AssetsReport.Outcomes;

import entities.Item;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.interfaces.LabelWrap;
import reportGeneration.interpreter.ReusableComponents.interfaces.SrtuctureItemsLoop;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.ResultsStorage;

public class AssetStructureAnalyseEnd implements SrtuctureItemsLoop, LabelWrap {
    private Item parent;
    private String period;
    private Double totalVal;
    private Double currentVal;
    private Double nonCurrentVal;
    private ObservableList<Item> currentItems;
    private ObservableList<Item> nonCurrentItems;

    public AssetStructureAnalyseEnd(
            ObservableList<Item> currentItems,
            ObservableList<Item> nonCurrentItems
    ) {
        Periods p = Periods.getInstance();
        ItemsStorage stor = ItemsStorage.getInstance();
        Item current = stor.get("GeneralCurrentAssets");
        Item nonCurrent = stor.get("NonCurrentAssets");
        this.parent = stor.get("AssetsGeneral");
        this.period = p.endKey();
        this.currentItems = currentItems;
        this.nonCurrentItems = nonCurrentItems;
        this.totalVal = parent.getVal(period);
        this.currentVal = current.getVal(period);
        this.nonCurrentVal = nonCurrent.getVal(period);
    }

    private Boolean assetsChanged() {
        Double assetsStart = parent.getFirstVal();
        Double assetsEnd = parent.getLastVal();
        return !assetsStart.equals(assetsEnd);
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
                    "Total Current assets composed mostly of ",
                    " etc.",
                    period);
        }
        if (nonCurrentVal != null && nonCurrentVal > 0) {
            str = loopItems(nonCurrentItems,
                    nonCurrentVal,
                    "The most significant items of the Non Current assets - ",
                    " etc.",
                    period);
        }
        ResultsStorage.addStr(18, "text", str);
        vBox.getChildren().add(labelWrap(str));
        return vBox;
    }

    private Label firstMessage() {
        String str = "";
        if (totalVal != null && totalVal != 0) {
            if (assetsChanged()) {
                str = "Over the period under review the assets structure changed. ";
            }
            str = str + "At the end of " + formatDate(period) + " the assets consisted of ";

            if (nonCurrentVal != null) {
                str = str + partStr(nonCurrentVal, totalVal) + " non-current assets";
            }
            if (currentVal != null) {
                str = str + " and " + partStr(currentVal, totalVal) + " of current assets.";
            }
        }
        ResultsStorage.addStr(17, "text", str);
        return labelWrap(str);
    }
}
