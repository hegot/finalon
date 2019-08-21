package finalon.reportGeneration.interpreter.FinancialResults.Outcomes;

import finalon.entities.Item;
import finalon.globalReusables.LabelWrap;
import javafx.collections.ObservableMap;
import javafx.scene.layout.VBox;
import finalon.reportGeneration.interpreter.ReusableComponents.interfaces.JsCalcHelper;
import finalon.reportGeneration.interpreter.ReusableComponents.interfaces.Round;
import finalon.reportGeneration.storage.ItemsStorage;
import finalon.reportGeneration.storage.Periods;
import finalon.reportGeneration.storage.ResultsStorage;
import finalon.reportGeneration.storage.SettingsStorage;

import java.util.ArrayList;

public class CostOfGoods implements LabelWrap, JsCalcHelper, Round {
    private Double last;
    private String endDate;
    private ArrayList<String> periodsArr;
    private String currency;
    private String amount;
    private Item costOfSales;

    public CostOfGoods() {
        this.costOfSales = ItemsStorage.get("CostOfSales");
        this.last = costOfSales.getLastVal();
        this.endDate = Periods.getEnd();
        this.periodsArr = Periods.getPeriodArr();
        ObservableMap<String, String> settings = SettingsStorage.getSettings();
        this.currency = settings.get("defaultCurrency");
        this.amount = settings.get("amount");
    }

    public VBox get(int weight) {
        VBox hbox = new VBox(10);
        if (last != null) {
            String output = "";

            output += compareEach();
            Item grossProfit = ItemsStorage.get("GrossProfit");

            String trend = grossProfit.trend();
            Double change = grossProfit.getChange();
            if (change != null) {
                if (trend.equals("INCREASED")) {
                    output += increase(change);
                } else if (trend.equals("DECREASED")) {
                    output += decrease(change);
                } else {
                    output += stable();
                }
            }
            hbox.getChildren().add(labelWrap(output));
            ResultsStorage.addStr(weight, "text", output);
        }
        return hbox;
    }


    private String compareEach() {
        StringBuilder out = new StringBuilder();
        for (int j = 0; j < periodsArr.size() - 1; j++) {
            String start = periodsArr.get(j);
            String end = periodsArr.get(j + 1);
            Double val1 = costOfSales.getVal(start);
            Double val2 = costOfSales.getVal(end);
            Double change = ((val2 - val1) / val1) * 100;
            String inner = change > 0 ? "more" : "less";
            if (val1 != null && val2 != null) {
                out.append("The cost of goods and services totaled "
                        + currency + " " + val2 + " " + amount + " in "
                        + formatDate(end) + ", " + round(change) + "% " + inner + " than in " + formatDate(start) + ". ");
            }
        }
        return out.toString();
    }

    private String atTheEnd() {
        return "At the end of " + endDate + " the cost of goods and services totaled " + currency + " " + last + " " + amount + ". ";
    }

    private String increase(Double change) {
        return atTheEnd() + "This has resulted in an increase in the gross profit by " + round(change) + "%.";
    }

    private String decrease(Double change) {
        return atTheEnd() + "This has resulted in decrease in the gross profit by " + round(change) + "%.";
    }

    private String stable() {
        return "This has resulted in stable gross profit during evaluation period";
    }
}
