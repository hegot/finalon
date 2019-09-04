package reportGeneration.interpreter.ReusableComponents.helpers;

import database.setting.DbSettingHandler;
import globalReusables.Setting;

public class Formatter {

    public static String round(Double input) {
        return (input != null) ? String.format("%.2f", input) : null;
    }

    public static String doubleCommaFormat(Double value) {
        String strValue = round(value);
        if (DbSettingHandler.getSetting(Setting.numberFormat).equals("comma")) {
            strValue = strValue.replace('.', ',');
        }
        return strValue;
    }

    public static String stringCommaFormat(String value) {
        if (DbSettingHandler.getSetting(Setting.numberFormat).equals("comma")) {
            value = value.replace('.', ',');
        }
        return value;
    }

    public static String formatDate(String input) {
        if (input != null) {
            input = input.replace("\n", "");
            String[] parts = input.split("-");
            return (parts[1] != null) ? parts[1] : "";
        }
        return "";
    }

    public static Double parseDouble(String str) {
        if (str == null) return null;
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
