package reportGeneration.interpreter.LiabilitiesReport.Outcomes;

import entities.Item;
import globalReusables.LabelWrap;
import globalReusables.NotEmpty;
import reportGeneration.interpreter.ReusableComponents.helpers.Calc;
import reportGeneration.interpreter.ReusableComponents.helpers.EquityShareAnalyze;
import reportGeneration.interpreter.ReusableComponents.helpers.Formatter;
import reportGeneration.interpreter.ReusableComponents.helpers.SrtuctureItemsLoop;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.SettingsStorage;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;


public class LiabilitiesStructureAnalyzeEnd {
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
        String assetsStartValue = SettingsStorage.get("assetsStartValue");
        this.assetsTotal = Formatter.parseDouble(assetsStartValue);
    }


    public VBox get() {
        VBox vBox = new VBox(10);
        if (this.parent.getValues().size() > 1) {
            String mess = firstMessage();
            vBox.getChildren().add(LabelWrap.wrap(mess));
            ResultsStorage.addStr(32, "text", mess);
        }
        String str = "";
        if (equityVal != null && equityVal > 0) {
            str = SrtuctureItemsLoop.loop(equityItems,
                    equityVal,
                    "The total equity consisted mostly of ",
                    " etc.",
                    period);
        }
        if (currentVal != null && currentVal > 0) {
            str = SrtuctureItemsLoop.loop(currentItems,
                    currentVal,
                    "The following current liabilities had the highest values: ",
                    " etc.",
                    period);
        }
        if (nonCurrentVal != null && nonCurrentVal > 0) {
            str = SrtuctureItemsLoop.loop(nonCurrentItems,
                    nonCurrentVal,
                    "Non-current liabilities included: ",
                    " etc.",
                    period);
        }
        vBox.getChildren().add(LabelWrap.wrap(str));
        ResultsStorage.addStr(33, "text", str);
        return vBox;
    }

    private String firstMessage() {
        String str = "At the end of " + Formatter.formatDate(period) + " the sources of finance comprised ";
        if (NotEmpty.notZero(equityVal)) {
            str = str + Calc.partStr(equityVal, totalVal) + " shareholders' equity, ";
        }
        if (NotEmpty.notZero(nonCurrentVal)) {
            str = str + Calc.partStr(nonCurrentVal, totalVal) + " non-current liabilities ";
        }
        if (NotEmpty.notZero(currentVal)) {
            str = str + " and " + Calc.partStr(currentVal, totalVal) + " current liabilities. ";
        }
        if (NotEmpty.notZero(assetsTotal) && NotEmpty.notZero(equityVal)) {
            Double share = (equityVal / assetsTotal) * 100;
            str = str + EquityShareAnalyze.analyse(share, period);
        }
        return str;
    }

}
