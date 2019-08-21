package finalon.reportGeneration.interpreter.ReusableComponents.NormValsEvaluator;

import finalon.entities.Formula;
import javafx.collections.ObservableMap;
import finalon.reportGeneration.interpreter.ReusableComponents.interfaces.ParseDouble;
import finalon.reportGeneration.interpreter.ReusableComponents.interfaces.Round;
import finalon.reportGeneration.storage.Periods;
import finalon.reportGeneration.storage.SettingsStorage;

import java.util.ArrayList;


public class StrReplacer implements ParseDouble, Round {
    private ObservableMap<String, String> settings;
    private String text;
    private Formula formula;

    public StrReplacer(String text, Formula formula) {
        this.settings = SettingsStorage.getSettings();
        this.text = text;
        this.formula = formula;
    }

    public String substitute() {
        text = text.replace("ENDDATE", Periods.getEnd());
        text = text.replace("AFTERSTART", Periods.getAfterStart());
        text = text.replace("STARTDATE", Periods.getStart());
        text = text.replace("COMPANYNAME", "“" + settings.get("company") + "”");
        text = text.replace("CURRENCY", settings.get("defaultCurrency"));
        text = text.replace("AMOUNT", settings.get("amount"));
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
        return round(sum / size);
    }


}
