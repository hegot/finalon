package finalon.reportGeneration.interpreter.ReusableComponents.interfaces;

import finalon.database.setting.DbSettingHandler;
import finalon.globalReusables.Setting;

public class CommaFormat {
    public static String format(Double value) {
        String strValue = Round.format(value);
        if (DbSettingHandler.getSetting(Setting.numberFormat).equals("comma")) {
            strValue = strValue.replace('.', ',');
        }
        return strValue;
    }
}
