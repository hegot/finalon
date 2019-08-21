package finalon.reportGeneration.interpreter.FormulaCalculation;

import finalon.entities.Formula;
import finalon.finalonWindows.reusableComponents.autocomplete.ParserBase;
import javafx.collections.ObservableMap;
import finalon.reportGeneration.interpreter.ReusableComponents.interfaces.ParseDouble;
import finalon.reportGeneration.interpreter.ReusableComponents.interfaces.Round;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Arrays;
import java.util.Map;

public class FormulaHandler implements ParseDouble, Round {
    private Formula formula;
    private Map<String, ObservableMap<String, Double>> values;
    private String period;

    public FormulaHandler(Formula formula, Map<String, ObservableMap<String, Double>> values, String period) {
        this.formula = formula;
        this.values = values;
        this.period = period;
    }

    private boolean isNumeric(String str) {
        return parseDouble(str) != null;
    }

    String getResult() {
        if (!formula.getCategory().equals("industry") && !formula.getCategory().equals("section")) {
            String formulaUpdated = getValuesInPlace();
            if (formulaUpdated != null) {
                if (formulaUpdated.contains("/0.0")) return null;
                return evaluateFormula(formulaUpdated);
            }
        }
        return null;
    }

    private String getValuesInPlace() {
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
        String fperiod = "[0]";
        if (index.contains(fperiod)) {
            index = index.replace(fperiod, "");
            prevIndex = true;
        }
        String speriod = "[1]";
        if (speriod.contains(index)) {
            index = index.replace(speriod, "");
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
                Double val = map.get(period);
                return val != null ? val : 0.0;
            }
        } else {
            return 0.0;
        }
        return null;
    }

    private String[] getIndexes() {
        String formulaVal = formula.getValue();
        return new ParserBase().getChunks(formulaVal, true);
    }

    private String evaluateFormula(String value) {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        String res = "";
        try {
            res = engine.eval(value).toString();
            if (res != null && res.length() > 0) {
                Double doubleInt = parseDouble(res);
                String val = round(doubleInt);
                res = val;
                if (val.equals("NaN") || val.equals("Infinity") || val.equals("-Infinity")) res = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}