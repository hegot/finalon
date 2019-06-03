package reportGeneration.interpreter.ReusableComponents;

import entities.Item;
import javafx.collections.ObservableMap;
import reportGeneration.interpreter.ReusableComponents.interfaces.OutcomeBase;
import reportGeneration.storage.Periods;
import reportGeneration.storage.SettingsStorage;

import java.util.ArrayList;

public class ItemInterpreter implements OutcomeBase {
    private Double first;
    private Double last;
    private ObservableMap<String, String> settings;
    private String startDate;
    private String endDate;
    private ObservableMap<String, Double> values;
    private ArrayList<String> periodsArr;
    private String currency;
    private String amount;

    public ItemInterpreter(Item item) {
        this.values = item.getValues();
        if (values.size() > 1) {
            this.first = getFirstVal(values);
            this.last = getLastVal(values);
        }
        this.settings = SettingsStorage.getSettings();
        this.startDate = Periods.getInstance().getStart();
        this.endDate = Periods.getInstance().getEnd();
        this.periodsArr = Periods.getInstance().getPeriodArr();
        this.currency = settings.get("defaultCurrency");
        this.amount = settings.get("amount");
    }

    public Double getChange() {
        if (last != null && first != null) {
            return ((last - first) / first) * 100;
        }
        return null;
    }

    public String trend() {
        if (last != null && first != null) {
            if ((last - first) > 0) {
                return "INCREASED";
            }
            if ((last - first) < 0) {
                return "DECREASED";
            }
            if ((last - first) == 0) {
                return "STABLE";
            }
        }
        return "STABLE";
    }


    private Double getValAt(String period) {
        Double val = null;
        if (period != null) {
            val = values.get(period);
        }
        return val;
    }

}
