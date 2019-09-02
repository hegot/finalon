package reportGeneration.interpreter.FinancialResults.Outcomes;

import entities.Item;
import globalReusables.LabelWrap;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.helpers.Formatter;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.ResultsStorage;
import reportGeneration.storage.SettingsStorage;

import java.util.ArrayList;

public class CostOfGoods {
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
        this.currency = SettingsStorage.get("defaultCurrency");
        this.amount = SettingsStorage.get("amount");
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
            hbox.getChildren().add(LabelWrap.wrap(output));
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
                        + Formatter.formatDate(end) + ", " + Formatter.format(change) + "% " + inner + " than in " + Formatter.formatDate(start) + ". ");
            }
        }
        return out.toString();
    }

    private String atTheEnd() {
        return "At the end of " + endDate + " the cost of goods and services totaled " + currency + " " + last + " " + amount + ". ";
    }

    private String increase(Double change) {
        return atTheEnd() + "This has resulted in an increase in the gross profit by " + Formatter.format(change) + "%.";
    }

    private String decrease(Double change) {
        return atTheEnd() + "This has resulted in decrease in the gross profit by " + Formatter.format(change) + "%.";
    }

    private String stable() {
        return "This has resulted in stable gross profit during evaluation period";
    }
}
