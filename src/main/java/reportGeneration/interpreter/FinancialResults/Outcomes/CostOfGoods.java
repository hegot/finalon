package reportGeneration.interpreter.FinancialResults.Outcomes;

import entities.Item;
import javafx.collections.ObservableMap;
import javafx.scene.layout.VBox;
import reportGeneration.interpreter.ReusableComponents.ItemInterpreter;
import reportGeneration.interpreter.ReusableComponents.interfaces.JsCalcHelper;
import reportGeneration.interpreter.ReusableComponents.interfaces.LabelWrap;
import reportGeneration.interpreter.ReusableComponents.interfaces.OutcomeBase;
import reportGeneration.interpreter.ReusableComponents.interfaces.Round;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;
import reportGeneration.storage.SettingsStorage;

import java.util.ArrayList;

public class CostOfGoods implements LabelWrap, OutcomeBase, JsCalcHelper, Round {
    private Double last;
    private String endDate;
    private ObservableMap<String, Double> values;
    private ArrayList<String> periodsArr;
    private String currency;
    private String amount;
    private ItemsStorage stor = ItemsStorage.getInstance();

    public CostOfGoods() {
        Item costOfSales = stor.getItemByCode("CostOfSales");
        this.values = costOfSales.getValues();
        if (values.size() > 1) {
            this.last = getLastVal(values);
        }
        this.endDate = Periods.getInstance().getEnd();
        this.periodsArr = Periods.getInstance().getPeriodArr();
        ObservableMap<String, String> settings = SettingsStorage.getSettings();
        this.currency = settings.get("defaultCurrency");
        this.amount = settings.get("amount");


    }

    public VBox get() {
        VBox hbox = new VBox(10);
        if (last != null) {
            String output = "";

            output += compareEach();
            output += atTheEnd();

            Item grossProfit = stor.getItemByCode("GrossProfit");
            ItemInterpreter itemInterpreter = new ItemInterpreter(grossProfit);

            if (itemInterpreter.trend().equals("INCREASED")) {
                output += increase(itemInterpreter.getChange());
            } else if (itemInterpreter.trend().equals("DECREASED")) {
                output += decrease(itemInterpreter.getChange());
            } else {
                output += stable();
            }
            hbox.getChildren().add(labelWrap(output));
        }
        return hbox;
    }

    private Double getVal(String period) {
        Double val = null;
        if (period != null) {
            val = values.get(period);
        }
        return val;
    }

    private String compareEach() {
        StringBuilder out = new StringBuilder();
        for (int j = 0; j < periodsArr.size() - 1; j++) {
            String start = periodsArr.get(j);
            String end = periodsArr.get(j + 1);
            Double val1 = getVal(start);
            Double val2 = getVal(end);
            Double change = ((val2 - val1) / val1) * 100;
            String inner = change > 0 ? "more" : "less";
            if (val1 != null && val2 != null) {
                out.append("The cost of goods and services totalled "
                        + currency + " " + val2 + " " + amount + " in "
                        + formatDate(end) + ", " + round(change) + "% " + inner + " than in " + formatDate(start) + ". ");
            }
        }
        return out.toString();
    }

    private String atTheEnd() {
        return "At the end of " + endDate + " the cost of goods and services totalled " + currency + " " + last + " " + amount + ". ";
    }

    private String increase(Double change) {
        return "This has resulted in an increase in the gross profit by " + round(change) + "%.";
    }

    private String decrease(Double change) {
        return "This has resulted in decrease in the gross profit by " + round(change) + "%.";
    }

    private String stable() {
        return "This has resulted in stable gross profit during evaluation period";
    }
}
