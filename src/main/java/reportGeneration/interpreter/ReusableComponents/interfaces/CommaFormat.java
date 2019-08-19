package reportGeneration.interpreter.ReusableComponents.interfaces;

import database.setting.DbSettingHandler;
import globalReusables.Setting;

public interface CommaFormat extends Round {
    default String commaFormat(Double value) {
        String strValue = round(value);
        if (DbSettingHandler.getSetting(Setting.numberFormat).equals("comma")) {
            strValue = strValue.replace('.', ',');
        }
        return strValue;
    }
}
