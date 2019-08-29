package reportGeneration.interpreter.ReusableComponents.helpers;


import javafx.beans.property.SimpleStringProperty;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class Calc {
    public static String partStr(Double val, Double total) {
        return format(part(val, total));
    }

    public static String format(Double input) {
        return Formatter.round(input) + '%';
    }

    public static Double part(Double val, Double total) {
        Double part = (val / total) * 100;
        String formatted = Formatter.round(part);
        return Double.parseDouble(formatted);
    }

    public static String getRelativeChange(Double start, Double end) {
        String val = "0";
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
            try {
                String result = engine.eval(formula).toString();
                if (result != null && result.length() > 0) {
                    val = String.format("%.1f", Formatter.parseDouble(result));
                    if (val.equals("NaN") || val.equals("Infinity") || val.equals("-Infinity")) val = "";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return val;
    }

    public static SimpleStringProperty diff(Double startVAl, Double endVal) {
        if (startVAl != null && endVal != null) {
            String absolute = Formatter.format(endVal - startVAl);
            return new SimpleStringProperty(absolute);
        }
        return null;
    }
}
