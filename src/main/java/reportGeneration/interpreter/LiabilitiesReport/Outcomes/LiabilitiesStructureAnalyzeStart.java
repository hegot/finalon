package reportGeneration.interpreter.LiabilitiesReport.Outcomes;

import entities.Item;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.interfaces.EquityShareAnalyze;
import globalReusables.LabelWrap;
import reportGeneration.interpreter.ReusableComponents.interfaces.ParseDouble;
import reportGeneration.interpreter.ReusableComponents.interfaces.SrtuctureItemsLoop;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.SettingsStorage;

public class LiabilitiesStructureAnalyzeStart implements SrtuctureItemsLoop, LabelWrap, EquityShareAnalyze, ParseDouble {
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

    public LiabilitiesStructureAnalyzeStart() {
        Periods p = Periods.getInstance();
        ItemsStorage stor = ItemsStorage.getInstance();
        Item EquityGeneral = stor.get("EquityGeneral");
        Item CurrentLiabilities = stor.get("CurrentLiabilities");
        Item NonCurrentLiabilities = stor.get("NonCurrentLiabilities");
        this.parent = stor.get("EquityAndLiabilities");
        this.period = p.startKey();
        this.equityItems = stor.getItems(EquityGeneral.getId());
        this.currentItems = stor.getItems(CurrentLiabilities.getId());
        this.nonCurrentItems = stor.getItems(NonCurrentLiabilities.getId());
        this.totalVal = parent.getVal(period);
        this.equityVal = EquityGeneral.getVal(period);
        this.currentVal = CurrentLiabilities.getVal(period);
        this.nonCurrentVal = NonCurrentLiabilities.getVal(period);
        String assetsStartValue = SettingsStorage.getInstance().getSettings().get("assetsStartValue");
        this.assetsTotal = parseDouble(assetsStartValue);
    }


    public VBox get() {
        VBox vBox = new VBox(10);
        if (this.parent.getValues().size() > 1) {
            String mess = firstMessage();
            vBox.getChildren().add(labelWrap(mess));
            ResultsStorage.addStr(28, "text", mess);
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
                    "The company's current liabilities included ",
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
        ResultsStorage.addStr(29, "text", str);
        vBox.getChildren().add(labelWrap(str));
        return vBox;
    }

    private String firstMessage() {
        String str = "By looking at Table 4 it can be noticed that the sources of finance consisted of ";
        if (equityVal != null) {
            str = str + partStr(equityVal, totalVal) + " shareholders' equity, ";
        }
        if (nonCurrentVal != null) {
            str = str + partStr(nonCurrentVal, totalVal) + " non-current liabilities ";
        }
        if (currentVal != null) {
            str = str + " and " + partStr(currentVal, totalVal) + " current liabilities. ";
        }
        if (assetsTotal != null && equityVal != null) {
            Double share = (equityVal / assetsTotal) * 100;
            str = str + equityShareAnalyse(share, period);
        }

        return str;
    }
}
