package interpreter.ReusableComponents;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public interface JsCalcHelper {
    default String getRelativeChange(Double start, Double end) {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        String formula = "(("
                + Double.toString(end)
                + "-"
                + Double.toString(start)
                + ")/"
                + Double.toString(start)
                + ") * 100";
        String val = "";
        try {
            String result = engine.eval(formula).toString();
            if (result != null && result.length() > 0) {
                Double doubleInt = Double.parseDouble(result);
                val = String.format("%.1f", doubleInt);
                if (val.equals("NaN")) val = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return val;
    }

    default String formatDate(String input) {
        input = input.replace("\n", "");
        String[] parts = input.split("-");
        return (parts[1] != null) ? parts[1] : "";
    }
}
