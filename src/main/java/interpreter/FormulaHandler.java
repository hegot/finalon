package interpreter;

import entities.Formula;
import finalonWindows.reusableComponents.autocomplete.ParserBase;
import javafx.collections.ObservableMap;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Arrays;
import java.util.Map;

class FormulaHahdler {
    private Formula formula;
    private Map<String, ObservableMap<String, Double>> values;
    private String period;
    private String Fperiod = "[0]";
    private String Speriod = "[1]";

    public FormulaHahdler(Formula formula, Map<String, ObservableMap<String, Double>> values, String period) {
        this.formula = formula;
        this.values = values;
        this.period = period;
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String getResult() {
        String formulaUpdated = getValuesInPlace();
        if (formulaUpdated != null) {
            return evaluateFormula(formulaUpdated);
        } else {
            return null;
        }
    }

    public String getValuesInPlace() {
        String formulaVal = formula.getValue();
        String[] indexes = getIndexes();
        for (String index : indexes) {
            if (!isNumeric(index)) {
                Double val = searchInValues(index);
                if (val != null) {
                    String indexVal = Double.toString(val);
                    formulaVal = formulaVal.replace(index, indexVal);
                } else {
                    return null;
                }
            }
        }
        return formulaVal;
    }

    private Double searchInValues(String index) {
        boolean prevIndex = false;
        if (index.indexOf(Fperiod) > -1) {
            index = index.replace(Fperiod, "");
            prevIndex = true;
        }
        if (index.indexOf(Speriod) > -1) {
            index = index.replace(Speriod, "");
        }
        ObservableMap<String, Double> map = values.get(index);
        if (map != null) {
            if (prevIndex) {
                Object[] set = map.keySet().toArray();
                Arrays.sort(set);
                for (int i = 0; i < set.length; i++) {
                    String key = (String) set[i];
                    if (key.equals(period)) {
                        int i2 = i - 1;
                        if (i2 >= 0) {
                            String prevPeriod = (String) set[i2];
                            return map.get(prevPeriod);
                        }
                    }
                }
            } else {
                return map.get(period);
            }
        }
        return null;
    }

    private String[] getIndexes() {
        String formulaVal = formula.getValue();
        String[] chunks = new ParserBase().getChunks(formulaVal, true);
        return chunks;
    }

    private String evaluateFormula(String value) {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        String res = "";
        try {
            res = engine.eval(value).toString();
            if (res != null && res.length() > 0) {
                Double doubleInt = Double.parseDouble(res);
                res = value + " = " + String.format("%.2f", doubleInt);
            } else {
                res = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}