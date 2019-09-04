package reportGeneration.interpreter.ReusableComponents.NormValsEvaluator;

import database.setting.DbSettingHandler;
import entities.Formula;
import globalReusables.Setting;
import javafx.collections.ObservableMap;
import reportGeneration.interpreter.ReusableComponents.helpers.Formatter;
import reportGeneration.storage.Periods;
import reportGeneration.storage.SettingsStorage;

import java.util.ArrayList;
import java.util.regex.Pattern;


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
        if (DbSettingHandler.getSetting(Setting.numberFormat).equals("comma")) {
            text = text.replaceAll("(\\d+)\\.(\\d+)", "$1,$2");
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
        return Formatter.doubleCommaFormat(sum / size);
    }


}
