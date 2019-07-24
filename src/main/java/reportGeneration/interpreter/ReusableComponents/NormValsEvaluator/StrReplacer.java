package reportGeneration.interpreter.ReusableComponents.NormValsEvaluator;

import entities.Formula;
import javafx.collections.ObservableMap;
import reportGeneration.interpreter.ReusableComponents.interfaces.ParseDouble;
import reportGeneration.interpreter.ReusableComponents.interfaces.Round;
import reportGeneration.storage.Periods;
import reportGeneration.storage.SettingsStorage;

import java.util.ArrayList;


public class StrReplacer implements ParseDouble, Round {
    private Periods periods;
    private ObservableMap<String, String> settings;
    private String text;
    private Formula formula;

    public StrReplacer(String text, Formula formula) {
        this.periods = Periods.getInstance();
        this.settings = SettingsStorage.getInstance().getSettings();
        this.text = text;
        this.formula = formula;
    }

    public String substitute() {
        text = text.replace("ENDDATE", periods.getEnd());
        text = text.replace("AFTERSTART", periods.getAfterStart());
        text = text.replace("STARTDATE", periods.getStart());
        text = text.replace("PRE_END_DATE", periods.prePreEndKey());

        text = text.replace("COMPANYNAME", settings.get("company"));
        text = text.replace("CURRENCY", settings.get("defaultCurrency"));
        text = text.replace("AMOUNT", settings.get("amount"));
        String endVal = toString(formula.getLastVal());
        String startVal = toString(formula.getFirstVal());
        if (endVal != null) {
            if (text.contains("LASTVALUEPERCENT")) {
                text = text.replace("LASTVALUEPERCENT", round(parseDouble(endVal) * 100));
            }
            if (text.contains("LASTVALUE")) {
                text = text.replace("LASTVALUE", endVal);
            }
        }
        if (text.contains("PRE_END_VALUE")) {
            Double preEndValDob = formula.getVal(periods.prePreEndKey());
            if(preEndValDob != null){
                String preEndVal = toString(preEndValDob);
                text = text.replace("PRE_END_VALUE", preEndVal);
            }
        }


        if (startVal != null) {
            if (text.contains("STARTVALUEPERCENT")) {
                text = text.replace("STARTVALUEPERCENT", round(parseDouble(startVal) * 100));
            }
            if (text.contains("STARTVALUE")) {
                text = text.replace("STARTVALUE", startVal);
            }
        }
        if (text.contains("AVERAGEVALUE")) {
            text = text.replace("AVERAGEVALUE", getAverageVal());
        }
        return text;
    }

    private String getAverageVal() {
        ObservableMap<String, Double> vals = formula.getPeriods();
        Double sum = 0.0;
        ArrayList<String> arr = periods.getPeriodArr();
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
