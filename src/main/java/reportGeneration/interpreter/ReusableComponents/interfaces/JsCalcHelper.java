package reportGeneration.interpreter.ReusableComponents.interfaces;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public interface JsCalcHelper extends ParseDouble {
    default String getRelativeChange(Double start, Double end) {
        String val = "0";
        if (start != null && end != null) {
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");
            String formula = "(("
                    + "(" + Double.toString(end) + ")"
                    + "-"
                    + Double.toString(start)
                    + ")/"
                    + "(" + Double.toString(start) + ")"
                    + ") * 100";
            val = "";
            try {
                String result = engine.eval(formula).toString();
                if (result != null && result.length() > 0) {
                    val = String.format("%.1f", parseDouble(result));
                    if (val.equals("NaN")) val = "";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return val;
    }

    default String formatDate(String input) {
        if (input != null) {
            input = input.replace("\n", "");
            String[] parts = input.split("-");
            return (parts[1] != null) ? parts[1] : "";
        }
        return "";
    }
}
