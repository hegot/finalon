package reportGeneration.interpreter.FormulaCalculation;

import entities.Formula;
import finalonWindows.reusableComponents.autocomplete.ParserBase;
import javafx.collections.ObservableMap;
import reportGeneration.interpreter.ReusableComponents.helpers.Formatter;
import services.Logger;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.*;

public class FormulaHandler {
    private Formula formula;
    private Map<String, ObservableMap<String, Double>> values;
    private String period;

    public FormulaHandler(Formula formula, Map<String, ObservableMap<String, Double>> values, String period) {
        this.formula = formula;
        this.values = values;
        this.period = period;
    }

    private boolean isNumeric(String str) {
        return Formatter.parseDouble(str) != null;
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
        try {
            ArrayList<String> indexes = getIndexes();
            Double val;
            String indexVal;
            for (String index : indexes) {
                if (!isNumeric(index)) {
                    val = searchInValues(index);
                    if (val != null) {
                        indexVal = Double.toString(val);
                        formulaVal = formulaVal.replace(index, indexVal);
                    } else {
                        return null;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.log("Replacement in formula string  failed");
        }
        return formulaVal;
    }

    private Double searchInValues(String index) {
        boolean prevIndex = false;
        try {
            String fperiod = "[0]";
            if (index.contains(fperiod)) {
                index = index.replace(fperiod, "");
                prevIndex = true;
            }
            String speriod = "[1]";
            if (index.contains(speriod)) {
                index = index.replace(speriod, "");
            }
            ObservableMap<String, Double> map = values.get(index);
            if (map != null) {
                if (prevIndex) {
                    Object[] set = map.keySet().toArray();
                    Arrays.sort(set);
                    String key;
                    for (int i = 0; i < set.length; i++) {
                        key = (String) set[i];
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
        } catch (Exception e) {
            e.printStackTrace();
            Logger.log("Search In Values formula calculation failed");
        }
        return null;
    }

    private ArrayList<String> getIndexes() {
        String formulaVal = formula.getValue();
        String[] array = new ParserBase().getChunks(formulaVal, true);
        ArrayList<String> list = new ArrayList<>(Arrays.asList(array));
        Comparator<String> comparator = Comparator.comparing(String::length);
        Collections.sort(list, comparator.reversed());
        return list;
    }

    private String evaluateFormula(String value) {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        String res = "";
        try {
            res = engine.eval(value).toString();
            if (res != null && res.length() > 0) {
                Double doubleInt = Formatter.parseDouble(res);
                String val = Formatter.round(doubleInt);
                res = val;
                if (val.equals("NaN") || val.equals("Infinity") || val.equals("-Infinity")) res = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.log("JavaScript engeen formula evaluation failed");
        }
        return res;
    }
}