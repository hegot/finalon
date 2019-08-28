package finalon.reportGeneration.interpreter.ReusableComponents.NormValsEvaluator;

import finalon.entities.Formula;
import finalon.reportGeneration.interpreter.ReusableComponents.interfaces.CommaFormat;
import finalon.reportGeneration.interpreter.ReusableComponents.interfaces.ParseDouble;
import finalon.reportGeneration.storage.Periods;
import finalon.reportGeneration.storage.SettingsStorage;
import javafx.collections.ObservableMap;

import java.util.ArrayList;


public class StrReplacer implements ParseDouble {
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
        return CommaFormat.format(sum / size);
    }


}
