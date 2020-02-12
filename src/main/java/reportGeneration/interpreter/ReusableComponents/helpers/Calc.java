package reportGeneration.interpreter.ReusableComponents.helpers;


import javafx.beans.property.SimpleStringProperty;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class Calc {
    public static String partStr(Double val, Double total) {
        String str = "";
        try {
            str = format(part(val, total));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return str;
    }

    public static String format(Double input) {
        String str = "";
        try {
            String val = Formatter.round(input);
            str = Formatter.stringCommaFormat(val) + '%';
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return str;
    }

    public static Double part(Double val, Double total) {
        Double dob = 0.0;
        try {
            Double part = (val / total) * 100;
            String formatted = Formatter.round(part);
            formatted = formatted.replaceAll(",", ".");
            dob = Double.parseDouble(formatted);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return dob;
    }

    public static String getRelativeChange(Double start, Double end) {
        String val = "0";
        try {
            if (start != null && end != null) {
                ScriptEngineManager mgr = new ScriptEngineManager();
                ScriptEngine engine = mgr.getEngineByName("JavaScript");
                String formula = "(("
                        + "(" + Double.toString(end) + ")"
                        + "-"
                        + "(" + Double.toString(start) + ")"
                        + ")/"
                        + "(" + Double.toString(start) + ")"
                        + ") * 100";
                val = "";
                String result = engine.eval(formula).toString();
                if (result != null && result.length() > 0) {
                    val = Formatter.percentFormat(result);
                    if (val.equals("NaN") || val.equals("Infinity") || val.equals("-Infinity")|| val.equals("∞")) val = "";
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return val;
    }

    public static SimpleStringProperty diff(Double startVAl, Double endVal) {
        try {
            if (startVAl != null && endVal != null) {
                String absolute = Formatter.finalNumberFormat(endVal - startVAl);
                return new SimpleStringProperty(absolute);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
