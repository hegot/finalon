package reportGeneration.interpreter.ReusableComponents.NormValsEvaluator;

import entities.Formula;
import reportGeneration.interpreter.ReusableComponents.helpers.Formatter;
import reportGeneration.storage.Periods;
import reportGeneration.storage.SettingsStorage;
import javafx.collections.ObservableMap;

import java.util.ArrayList;


public class StrReplacer {
    private String text;
    private Formula formula;

    public StrReplacer(String text, Formula formula) {
        this.text = text;
        this.formula = formula;
    }

    public String substitute() {
        text = text.replace("ENDDATE", Periods.getEnd());
        text = text.replace("AFTERSTART", Periods.getAfterStart());
        text = text.replace("STARTDATE", Periods.getStart());
        text = text.replace("COMPANYNAME", "“" + SettingsStorage.get("company") + "”");
        text = text.replace("CURRENCY", SettingsStorage.get("defaultCurrency"));
        text = text.replace("AMOUNT", SettingsStorage.get("amount"));
        if (text.contains("AVERAGEVALUE")) {
            text = text.replace("AVERAGEVALUE", getAverageVal());
        }
        if (text.contains("PRE_END_DATE")) {
            text = text.replace("PRE_END_DATE", Periods.prePreEndKey());
        }
        return text;
    }

    private String getAverageVal() {
        ObservableMap<String, Double> vals = formula.getPeriods();
        Double sum = 0.0;
        ArrayList<String> arr = Periods.getPeriodArr();
        for (String period : arr) {
            Double val = vals.get(period);
            if (val != null) {
                sum += val;
            }
        }
        int size = arr.size() - 1;
        return Formatter.format(sum / size);
    }


}
