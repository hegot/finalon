package interpreter;

import entities.Formula;
import finalonWindows.reusableComponents.autocomplete.ParserBase;
import javafx.collections.ObservableMap;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Map;

class FormulaHahdler {
    private Formula formula;
    private Map<String, ObservableMap<String, Double>> values;
    private String period;

    public FormulaHahdler(Formula formula, Map<String, ObservableMap<String, Double>> values, String period) {
        this.formula = formula;
        this.values = values;
        this.period = period;
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
        for (String index : getIndexes()) {
            Double val = searchInValues(index);
            if (val != null) {
                String indexVal = Double.toString(val);
                formulaVal = formulaVal.replace(index, indexVal);
            } else {
                return null;
            }
        }
        return formulaVal;
    }

    private Double searchInValues(String index) {
        ObservableMap<String, Double> map = values.get(index);
        if (map != null) {
            return map.get(period);
        }
        return null;
    }


    private String[] getIndexes() {
        String[] chunks = new ParserBase().getChunks(formula.getValue());
        return chunks;
    }


    private String evaluateFormula(String value) {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        String res = "";
        try {
            res = engine.eval(value).toString();
            System.out.println(res);
        } catch (Exception e) {

        }

        return res;
    }

}