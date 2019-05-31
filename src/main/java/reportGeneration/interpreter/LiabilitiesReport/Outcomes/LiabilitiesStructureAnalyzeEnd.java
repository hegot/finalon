package reportGeneration.interpreter.LiabilitiesReport.Outcomes;

import entities.Item;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.interfaces.EquityShareAnalyze;
import reportGeneration.interpreter.ReusableComponents.interfaces.LabelWrap;
import reportGeneration.interpreter.ReusableComponents.interfaces.ParseDouble;
import reportGeneration.interpreter.ReusableComponents.interfaces.SrtuctureItemsLoop;
import reportGeneration.storage.IndexesStorage;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.SettingsStorage;

public class LiabilitiesStructureAnalyzeEnd implements SrtuctureItemsLoop, LabelWrap, EquityShareAnalyze, ParseDouble {
    private Item parent;
    private String period;
    private Double totalVal;
    private Double equityVal;
    private Double currentVal;
    private Double nonCurrentVal;
    private ObservableList<Item> equityItems;
    private ObservableList<Item> currentItems;
    private ObservableList<Item> nonCurrentItems;
    private Double assetsTotal;

    public LiabilitiesStructureAnalyzeEnd(String period) {
        Item EquityGeneral = IndexesStorage.get("EquityGeneral");
        Item CurrentLiabilities = IndexesStorage.get("CurrentLiabilities");
        Item NonCurrentLiabilities = IndexesStorage.get("NonCurrentLiabilities");
        this.parent = IndexesStorage.get("EquityAndLiabilities");
        this.period = period;
        this.equityItems = ItemsStorage.getInstance().getItems(EquityGeneral.getId());
        this.currentItems = ItemsStorage.getInstance().getItems(CurrentLiabilities.getId());
        this.nonCurrentItems = ItemsStorage.getInstance().getItems(NonCurrentLiabilities.getId());
        this.totalVal = getVal(parent, period);
        this.equityVal = getVal(EquityGeneral, period);
        this.currentVal = getVal(CurrentLiabilities, period);
        this.nonCurrentVal = getVal(NonCurrentLiabilities, period);
        String assetsStartValue = SettingsStorage.getSettings().get("assetsStartValue");
        this.assetsTotal = parseDouble(assetsStartValue);
    }


    public VBox get() {
        VBox vBox = new VBox(10);
        if (this.parent.getValues().size() > 1) {
            vBox.getChildren().add(firstMessage());
        }
        if (equityVal != null && equityVal > 0) {
            vBox.getChildren().add(
                    loopItems(equityItems,
                            equityVal,
                            "The total equity consisted mostly of ",
                            " etc.",
                            period)
            );
        }
        if (currentVal != null && currentVal > 0) {
            vBox.getChildren().add(
                    loopItems(currentItems,
                            currentVal,
                            "The following current liabilities had the highest values: ",
                            " etc.",
                            period)
            );
        }
        if (nonCurrentVal != null && nonCurrentVal > 0) {
            vBox.getChildren().add(
                    loopItems(nonCurrentItems,
                            nonCurrentVal,
                            "Noncurrent liabilities included: ",
                            " etc.",
                            period)
            );
        }
        return vBox;
    }

    private Label firstMessage() {
        String str = "At the end of " + formatDate(period) + " the sources of finance comprised ";
        if (equityVal != null) {
            str = str + partStr(equityVal, totalVal) + " shareholders' equity, ";
        }
        if (nonCurrentVal != null) {
            str = str + partStr(nonCurrentVal, totalVal) + " non-current liabilities ";
        }
        if (currentVal != null) {
            str = str + " and " + partStr(currentVal, totalVal) + " current liabilities. ";
        }
        if (assetsTotal != null) {
            Double share = (totalVal / assetsTotal) * 100;
            str = str + equityShareAnalyse(share, period);
        }
        return labelWrap(str);
    }
}
