package reportGeneration.interpreter.LiabilitiesReport.Outcomes;

import entities.Item;
import globalReusables.LabelWrap;
import globalReusables.NotEmpty;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.interfaces.EquityShareAnalyze;
import reportGeneration.interpreter.ReusableComponents.interfaces.ParseDouble;
import reportGeneration.interpreter.ReusableComponents.interfaces.SrtuctureItemsLoop;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.SettingsStorage;

public class LiabilitiesStructureAnalyzeEnd implements SrtuctureItemsLoop, LabelWrap, EquityShareAnalyze, ParseDouble, NotEmpty {
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

    public LiabilitiesStructureAnalyzeEnd() {
        Item EquityGeneral = ItemsStorage.get("EquityGeneral");
        Item CurrentLiabilities = ItemsStorage.get("CurrentLiabilities");
        Item NonCurrentLiabilities = ItemsStorage.get("NonCurrentLiabilities");
        this.parent = ItemsStorage.get("EquityAndLiabilities");
        this.period = Periods.endKey();
        this.equityItems = ItemsStorage.getItems(EquityGeneral.getId());
        this.currentItems = ItemsStorage.getItems(CurrentLiabilities.getId());
        this.nonCurrentItems = ItemsStorage.getItems(NonCurrentLiabilities.getId());
        this.totalVal = parent.getVal(period);
        this.equityVal = EquityGeneral.getVal(period);
        this.currentVal = CurrentLiabilities.getVal(period);
        this.nonCurrentVal = NonCurrentLiabilities.getVal(period);
        String assetsStartValue = SettingsStorage.getSettings().get("assetsStartValue");
        this.assetsTotal = parseDouble(assetsStartValue);
    }


    public VBox get() {
        VBox vBox = new VBox(10);
        if (this.parent.getValues().size() > 1) {
            String mess = firstMessage();
            vBox.getChildren().add(labelWrap(mess));
            ResultsStorage.addStr(32, "text", mess);
        }
        String str = "";
        if (equityVal != null && equityVal > 0) {
            str = loopItems(equityItems,
                    equityVal,
                    "The total equity consisted mostly of ",
                    " etc.",
                    period);
        }
        if (currentVal != null && currentVal > 0) {
            str = loopItems(currentItems,
                    currentVal,
                    "The following current liabilities had the highest values: ",
                    " etc.",
                    period);
        }
        if (nonCurrentVal != null && nonCurrentVal > 0) {
            str = loopItems(nonCurrentItems,
                    nonCurrentVal,
                    "Non-current liabilities included: ",
                    " etc.",
                    period);
        }
        vBox.getChildren().add(labelWrap(str));
        ResultsStorage.addStr(33, "text", str);
        return vBox;
    }

    private String firstMessage() {
        String str = "At the end of " + formatDate(period) + " the sources of finance comprised ";
        if (notZero(equityVal)) {
            str = str + partStr(equityVal, totalVal) + " shareholders' equity, ";
        }
        if (notZero(nonCurrentVal)) {
            str = str + partStr(nonCurrentVal, totalVal) + " non-current liabilities ";
        }
        if (notZero(currentVal)) {
            str = str + " and " + partStr(currentVal, totalVal) + " current liabilities. ";
        }
        if (notZero(assetsTotal) && notZero(equityVal)) {
            Double share = (equityVal / assetsTotal) * 100;
            str = str + equityShareAnalyse(share, period);
        }
        return str;
    }

}
