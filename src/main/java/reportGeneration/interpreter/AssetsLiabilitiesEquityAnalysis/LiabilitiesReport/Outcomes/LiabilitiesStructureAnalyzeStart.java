package reportGeneration.interpreter.AssetsLiabilitiesEquityAnalysis.LiabilitiesReport.Outcomes;

import entities.Item;
import globalReusables.LabelWrap;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.helpers.Calc;
import reportGeneration.interpreter.ReusableComponents.helpers.EquityShareAnalyze;
import reportGeneration.interpreter.ReusableComponents.helpers.Formatter;
import reportGeneration.interpreter.ReusableComponents.helpers.SrtuctureItemsLoop;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.SettingsStorage;

public class LiabilitiesStructureAnalyzeStart {
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
        Item EquityGeneral = ItemsStorage.get("EquityGeneral");
        Item CurrentLiabilities = ItemsStorage.get("CurrentLiabilities");
        Item NonCurrentLiabilities = ItemsStorage.get("NonCurrentLiabilities");
        this.parent = ItemsStorage.get("EquityAndLiabilities");
        this.period = Periods.startKey();
        this.equityItems = ItemsStorage.getItems(EquityGeneral.getId());
        this.currentItems = ItemsStorage.getItems(CurrentLiabilities.getId());
        this.nonCurrentItems = ItemsStorage.getItems(NonCurrentLiabilities.getId());
        this.totalVal = parent.getVal(period);
        this.equityVal = EquityGeneral.getVal(period);
        this.currentVal = CurrentLiabilities.getVal(period);
        this.nonCurrentVal = NonCurrentLiabilities.getVal(period);
        String assetsStartValue = SettingsStorage.get("assetsStartValue");
        this.assetsTotal = Formatter.parseDouble(assetsStartValue);
    }


    public VBox get(int weight) {
        String str = "";
        VBox vBox = new VBox(10);
        if (this.parent.getValues().size() > 1) {
            str += firstMessage();
        }

        if (equityVal != null && equityVal > 0) {
            str += SrtuctureItemsLoop.loop(equityItems,
                    equityVal,
                    "The total equity consisted mostly of ",
                    " etc. ",
                    period);
        }
        if (currentVal != null && currentVal > 0) {
            str += SrtuctureItemsLoop.loop(currentItems,
                    currentVal,
                    "The company's current liabilities included ",
                    " etc. ",
                    period);
        }
        if (nonCurrentVal != null && nonCurrentVal > 0) {
            str += SrtuctureItemsLoop.loop(nonCurrentItems,
                    nonCurrentVal,
                    "Non-current liabilities included: ",
                    " etc. ",
                    period);
        }
        ResultsStorage.addStr(weight, "text", str);
        vBox.getChildren().add(LabelWrap.wrap(str));
        return vBox;
    }

    private String firstMessage() {
        String str = "By looking at Table 4 it can be noticed that the sources of finance consisted of ";
        if (equityVal != null) {
            str = str + Calc.partStr(equityVal, totalVal) + " shareholders' equity, ";
        }
        if (nonCurrentVal != null) {
            str = str + Calc.partStr(nonCurrentVal, totalVal) + " non-current liabilities ";
        }
        if (currentVal != null) {
            str = str + " and " + Calc.partStr(currentVal, totalVal) + " current liabilities. ";
        }
        if (assetsTotal != null && equityVal != null) {
            Double share = (equityVal / assetsTotal) * 100;
            str = str + EquityShareAnalyze.analyse(share, period);
        }

        return str;
    }
}