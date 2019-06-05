package reportGeneration.interpreter.ReusableComponents.NormValsEvaluator;

import entities.Formula;
import javafx.collections.ObservableMap;
import reportGeneration.interpreter.ReusableComponents.interfaces.ParseDouble;
import reportGeneration.interpreter.ReusableComponents.interfaces.Round;
import reportGeneration.storage.Periods;
import reportGeneration.storage.SettingsStorage;


public class StrReplacer implements ParseDouble, Round {
    private Periods periods;
    private ObservableMap<String, String> settings;
    private String text;
    private Formula formula;

    public StrReplacer(String text, Formula formula) {
        this.periods = Periods.getInstance();
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
        String endVal = toString(formula.getLastVal());
        String startVal = toString(formula.getFirstVal());
        if (endVal != null) {
            text = text.replace("LASTVALUEPERCENT", round(parseDouble(endVal) * 100));
            text = text.replace("LASTVALUE", endVal);
        }

        if (startVal != null) {
            text = text.replace("STARTVALUEPERCENT", round(parseDouble(startVal) * 100));
            text = text.replace("STARTVALUE", startVal);
        }
        return text;
    }

}
