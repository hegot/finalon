package reportGeneration.interpreter.AssetsLiabilitiesEquityAnalysis.AssetsReport.Outcomes;

import entities.Item;
import globalReusables.LabelWrap;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.helpers.Calc;
import reportGeneration.interpreter.ReusableComponents.helpers.Formatter;
import reportGeneration.interpreter.ReusableComponents.helpers.SrtuctureItemsLoop;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.ResultsStorage;

public class AssetStructureAnalyzeStart {
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


    public VBox get(int weight) {
        StringBuilder str = new StringBuilder();
        VBox vBox = new VBox(10);
        if (this.parent.getValues().size() > 1) {
            str.append(firstMessage());
        }
        if (currentVal != null && currentVal > 0) {
            str.append(SrtuctureItemsLoop.loop(currentItems,
                    currentVal,
                    "The most significant items of the current assets were: ",
                    "etc. ",
                    period));
        }
        if (nonCurrentVal != null && nonCurrentVal > 0) {
            str.append(SrtuctureItemsLoop.loop(nonCurrentItems,
                    nonCurrentVal,
                    "The following non-current assets had the highest values: ",
                    "while the other items did not play a significant role. ",
                    period));
        }
        vBox.getChildren().add(LabelWrap.wrap(str.toString()));
        ResultsStorage.addStr(weight, "text", str.toString());
        return vBox;
    }

    private String firstMessage() {
        StringBuilder str = new StringBuilder();
        str.append("At the end of ").append(Formatter.formatDate(period)).append(" the assets consisted of ");
        if (nonCurrentVal != null) {
            str.append(Calc.partStr(nonCurrentVal, totalVal)).append(" non-current assets");
        }
        if (currentVal != null) {
            str.append(" and ").append(Calc.partStr(currentVal, totalVal)).append(" of current assets. ");
        }
        return str.toString();
    }
}
