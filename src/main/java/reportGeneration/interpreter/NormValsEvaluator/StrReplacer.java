package reportGeneration.interpreter.NormValsEvaluator;

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
    private ArrayList<String> periodArr;

    public StrReplacer(String text, Formula formula) {
        this.periods = Periods.getInstance();
        this.periodArr = periods.getPeriodArr();
        this.settings = SettingsStorage.getSettings();
        this.text = text;
        this.formula = formula;
    }

    public String substitute() {
        text = text.replace("ENDDATE", periods.getEnd());
        text = text.replace("STARTDATE", periods.getStart());
        text = text.replace("COMPANYNAME", settings.get("company"));
        text = text.replace("CURRENCY", settings.get("defaultCurrency"));
        text = text.replace("AMOUNT", settings.get("amount"));
        String endVal = getVal(periodArr.get(periodArr.size() - 1));
        String startVal = getVal(periodArr.get(0));
        if (endVal != null) {
            text = text.replace("LASTVALUEPERCENT", round(parseDouble(endVal) * 100));
        }
        if (endVal != null) {
            text = text.replace("LASTVALUE", endVal);
        }

        if (startVal != null) {
            text = text.replace("STARTVALUE", startVal);
        }
        return text;
    }

    private String getVal(String period) {
        String val = formula.getPeriods().get(period);
        return val;
    }
}
