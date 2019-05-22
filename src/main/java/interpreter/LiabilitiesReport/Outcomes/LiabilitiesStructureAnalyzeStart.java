package interpreter.LiabilitiesReport.Outcomes;

import entities.Item;
import interpreter.ReusableComponents.EquityShareAnalyze;
import interpreter.ReusableComponents.LabelWrap;
import interpreter.ReusableComponents.SrtuctureItemsLoop;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class LiabilitiesStructureAnalyzeStart implements SrtuctureItemsLoop, LabelWrap, EquityShareAnalyze {
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

    public LiabilitiesStructureAnalyzeStart(
            ObservableMap<String, String> settings,
            Item parent,
            Item equity,
            Item current,
            Item nonCurrent,
            ObservableList<Item> equityItems,
            ObservableList<Item> currentItems,
            ObservableList<Item> nonCurrentItems,
            String period
    ) {
        this.parent = parent;
        this.period = period;
        this.equityItems = equityItems;
        this.currentItems = currentItems;
        this.nonCurrentItems = nonCurrentItems;
        this.totalVal = getVal(parent, period);
        this.equityVal = getVal(equity, period);
        this.currentVal = getVal(current, period);
        this.nonCurrentVal = getVal(nonCurrent, period);
        this.assetsTotal = Double.parseDouble(settings.get("assetsStartValue"));
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
                    "The company's current liabilities included ",
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
        if(assetsTotal != null){
            Double share =(totalVal/assetsTotal) * 100;
            str = str + equityShareAnalyse(share, period);
        }

        return labelWrap(str);
    }
}
