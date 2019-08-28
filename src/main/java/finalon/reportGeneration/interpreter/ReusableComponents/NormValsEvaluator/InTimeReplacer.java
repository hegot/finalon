package finalon.reportGeneration.interpreter.ReusableComponents.NormValsEvaluator;

import finalon.entities.Formula;
import finalon.reportGeneration.storage.Periods;

public class InTimeReplacer {
    public static String getVal(String text, Formula formula) {
        Double endVal = formula.getLastVal();
        if (endVal != null) {
            text = replaceVal("LASTVALUEPERCENT", text, endVal * 100);
        }
        text = replaceVal("LASTVALUE", text, endVal);

        Double preEndVal = formula.getVal(Periods.prePreEndKey());
        if (preEndVal != null) {
            text = replaceVal("PRE_END_VALUE_PERCENT", text, preEndVal * 100);
        }
        text = replaceVal("PRE_END_VALUE", text, preEndVal);

        Double startVal = formula.getFirstVal();
        if (startVal != null) {
            text = replaceVal("STARTVALUEPERCENT", text, startVal * 100);
        }
        text = replaceVal("STARTVALUE", text, startVal);
        return text;
    }

    static String replaceVal(String key, String text, Double val) {
        if (text.contains(key)) {
            if (val == null) {
                return "";
            } else {
                return text.replace("LASTVALUE", round(val));
            }
        }
        return text;
    }

    static String round(Double input) {
        return (input != null) ? String.format("%.2f", input) : null;
    }
}
