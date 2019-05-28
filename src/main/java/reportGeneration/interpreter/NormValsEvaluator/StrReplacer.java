package reportGeneration.interpreter.NormValsEvaluator;

import entities.Formula;
import javafx.collections.ObservableMap;
import reportGeneration.storage.Periods;
import reportGeneration.storage.SettingsStorage;

import java.util.ArrayList;

public class StrReplacer {
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
        String endVal = getVal(periodArr.get(periodArr.size() - 1));
        String startVal = getVal(periodArr.get(0));
        if(endVal != null){
            text = text.replace("LASTVALUEPERCENT", Double.toString(Double.parseDouble(endVal) * 100));
        }
        if(endVal != null){
            text = text.replace("LASTVALUE", endVal);
        }

        if(startVal != null) {
            text = text.replace("STARTVALUE", startVal);
        }
        return text;
    }

    private String getVal(String period) {
        String val = formula.getPeriods().get(period);
        return val;
    }
}