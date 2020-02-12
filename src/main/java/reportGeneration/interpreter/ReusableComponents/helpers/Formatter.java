package reportGeneration.interpreter.ReusableComponents.helpers;

import reportGeneration.storage.SettingsStorage;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class Formatter {

    public static String round(Double input) {
        return (input != null) ? String.format("%.2f", input) : null;
    }

    public static String finalNumberFormat(Double value) {
        String val = localeFormat(value);
        String format = SettingsStorage.get("numberFormat");
        if (format.equals("comma")) {
            val = val.replace('.', ',');
        }
        return val;
    }

    public static String commaFormat(Double value) {
        String val = value.toString();
        String format = SettingsStorage.get("numberFormat");
        if (format.equals("comma")) {
            val = val.replace('.', ',');
        }
        return val;
    }

    public static String stringCommaFormat(String value) {
        if (SettingsStorage.get("numberFormat").equals("comma")) {
            value = value.replace('.', ',');
        }
        return value;
    }

    public static String percentFormat(String value) {
        Double val = parseDouble(value);
        value = finalNumberFormat(val);
        return value;
    }

    public static String localeFormat(Double value) {
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        symbols.setGroupingSeparator(' ');
        formatter.setDecimalFormatSymbols(symbols);
        return formatter.format(value);
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
            str = str.replaceAll(",", ".");
            str = str.replaceAll(" ", "");
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
