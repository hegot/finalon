package reportGeneration.interpreter.ReusableComponents.NormValsEvaluator;

import database.setting.DbSettingHandler;
import entities.Formula;
import globalReusables.Setting;
import javafx.collections.ObservableMap;
import reportGeneration.interpreter.ReusableComponents.helpers.Formatter;
import reportGeneration.storage.Periods;
import reportGeneration.storage.SettingsStorage;

import java.util.ArrayList;


public class StrReplacer {


    public static String substitute(String text, Formula formula) {
        text = text.replace("ENDDATE", Periods.getEnd());
        text = text.replace("AFTERSTART", Periods.getAfterStart());
        text = text.replace("STARTDATE", Periods.getStart());
        text = text.replace("COMPANYNAME", "“" + SettingsStorage.get("company") + "”");
        text = text.replace("COMPANYS", "“" + SettingsStorage.get("company") + "`s”");
        text = text.replace("CURRENCY", SettingsStorage.get("defaultCurrency"));
        text = text.replace("AMOUNT", SettingsStorage.get("amount"));
        if (text.contains("AVERAGEVALUE")) {
            text = text.replace("AVERAGEVALUE", getAverageVal(formula));
        }
        if (text.contains("PRE_END_DATE")) {
            text = text.replace("PRE_END_DATE", Periods.prePreEndKey());
        }
        if (DbSettingHandler.getSetting(Setting.numberFormat).equals("comma")) {
            text = text.replaceAll("(\\d+)\\.(\\d+)", "$1,$2");
        }

        return text;
    }

    private static String getAverageVal(Formula formula) {
        ObservableMap<String, Double> vals = formula.getPeriods();
        Double sum = 0.0;
        ArrayList<String> arr = Periods.getPeriodArr();
        Double val;
        for (String period : arr) {
            val = vals.get(period);
            if (val != null) {
                sum += val;
            }
        }
        int size = arr.size() - 1;
        return Formatter.doubleCommaFormat(sum / size);
    }


}
