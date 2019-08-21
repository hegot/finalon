package finalon.reportGeneration.interpreter.FormulaCalculation;

import finalon.entities.Formula;
import finalon.entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import finalon.reportGeneration.interpreter.ReusableComponents.interfaces.ParseDouble;
import finalon.reportGeneration.storage.FormulaStorage;
import finalon.reportGeneration.storage.ItemsStorage;
import finalon.reportGeneration.storage.Periods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FormulaCalculation implements ParseDouble {
    private ArrayList<String> periods;
    private ObservableList<Formula> formulas;

    public FormulaCalculation() {
        this.periods = Periods.getPeriodArr();
        this.formulas = FormulaStorage.getItems();
    }

    public void setFormulaValues() {
        Map<String, ObservableMap<String, Double>> values = obtainKeyValueArray();
        for (Formula formula : formulas) {
            if (formula.getValue().length() > 0) {
                ObservableMap<String, Double> formulaPeriods = FXCollections.observableHashMap();
                for (String period : periods) {
                    FormulaHandler formulaHahdler = new FormulaHandler(formula, values, period);
                    String res = formulaHahdler.getResult();
                    if (res != null && res.length() > 0) {
                        Double result = parseDouble(res);
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
            if (item.getValues().size() > 0) {
                values.put(item.getShortName(), item.getValues());
            }
        }
        return values;
    }
}
