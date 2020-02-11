package reportGeneration.interpreter.FormulaCalculation;

import entities.Formula;
import entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import reportGeneration.interpreter.ReusableComponents.helpers.Formatter;
import reportGeneration.storage.FormulaStorage;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FormulaCalculation {
    private ArrayList<String> periods;
    private ObservableList<Formula> formulas;

    public FormulaCalculation() {
        this.periods = Periods.getPeriodArr();
        this.formulas = FormulaStorage.getItems();
    }

    public void setFormulaValues() {
        Map<String, ObservableMap<String, Double>> values = obtainKeyValueArray();
        FormulaHandler formulaHahdler;
        String res;
        Double result;
        ObservableMap<String, Double> formulaPeriods;
        for (Formula formula : formulas) {
            if (formula.getValue().length() > 0) {
                formulaPeriods = FXCollections.observableHashMap();
                for (String period : periods) {
                    formulaHahdler = new FormulaHandler(formula, values, period);
                    res = formulaHahdler.getResult();
                    if (res != null && res.length() > 0) {
                        result = Formatter.parseDouble(res);
                        if (result != null) {
                            formulaPeriods.put(period, result);
                        }
                    }

                }
                formula.setPeriods(formulaPeriods);
            }
        }
    }

    private Map<String, ObservableMap<String, Double>> obtainKeyValueArray() {
        Map<String, ObservableMap<String, Double>> values = new HashMap<>();
        for (Item item : ItemsStorage.getItems()) {
            if (item != null && item.getValues().size() > 0) {
                values.put(item.getShortName(), item.getValues());
            }
        }
        return values;
    }
}
