package reportGeneration.interpreter.AssetsReport.Outcomes;

import entities.Item;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.interfaces.LabelWrap;
import reportGeneration.interpreter.ReusableComponents.interfaces.SrtuctureItemsLoop;
import reportGeneration.storage.IndexesStorage;

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
            ObservableList<Item> nonCurrentItems,
            String period
    ) {
        Item current = IndexesStorage.get("GeneralCurrentAssets");
        Item nonCurrent = IndexesStorage.get("NonCurrentAssets");
        this.parent = IndexesStorage.get("AssetsGeneral");
        this.period = period;
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
        if (currentVal != null && currentVal > 0) {
            vBox.getChildren().add(
                    loopItems(currentItems,
                            currentVal,
                            "Total Current assets composed mostly of ",
                            " etc.",
                            period)
            );
        }
        if (nonCurrentVal != null && nonCurrentVal > 0) {
            vBox.getChildren().add(
                    loopItems(nonCurrentItems,
                            nonCurrentVal,
                            "The most significant items of the Non Current assets - ",
                            " etc.",
                            period)
            );
        }
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
        return labelWrap(str);
    }
}
