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
    private String currency;
    private String amount;
    private Item costOfSales;

    public CostOfGoods() {
        this.costOfSales = ItemsStorage.get("CostOfSales");
        this.last = costOfSales.getLastVal();
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
        ArrayList<String> periodsArr = Periods.getPeriodArr();
        StringBuilder out = new StringBuilder();
        String start;
        String end;
        Double val1;
        Double val2;
        Double change;
        String inner;
        for (int j = 0; j < periodsArr.size() - 1; j++) {
            start = periodsArr.get(j);
            end = periodsArr.get(j + 1);
            val1 = costOfSales.getVal(start);
            val2 = costOfSales.getVal(end);
            change = ((val2 - val1) / val1) * 100;
            inner = change > 0 ? "more" : "less";
            if (val1 != null && val2 != null && val1 != 0) {
                out.append("The cost of goods and services totaled "
                        + currency + " " + Formatter.finalNumberFormat(val2) + " " + amount + " in "
                        + Formatter.formatDate(end) + ", " + Formatter.finalNumberFormat(change) + "% " + inner + " than in " + Formatter.formatDate(start) + ". ");
            }
        }
        return out.toString();
    }

    private String atTheEnd() {
        return "At the end of " + Periods.getEnd() + " the cost of goods and services totaled " + currency + " " + Formatter.finalNumberFormat(last) + " " + amount + ". ";
    }

    private String increase(Double change) {
        return atTheEnd() + "This has resulted in an increase in the gross profit by " + Formatter.finalNumberFormat(change) + "%.";
    }

    private String decrease(Double change) {
        return atTheEnd() + "This has resulted in decrease in the gross profit by " + Formatter.finalNumberFormat(change) + "%.";
    }

    private String stable() {
        return "This has resulted in stable gross profit during evaluation period";
    }
}
