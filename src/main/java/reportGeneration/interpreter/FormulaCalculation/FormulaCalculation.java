package reportGeneration.interpreter.FormulaCalculation;

import entities.Formula;
import entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import reportGeneration.interpreter.ReusableComponents.interfaces.ParseDouble;
import reportGeneration.storage.FormulaStorage;
import reportGeneration.storage.ItemsStorage;
import reportGeneration.storage.Periods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FormulaCalculation implements ParseDouble {
    private ArrayList<String> periods;
    private ObservableList<Formula> formulas;

    public FormulaCalculation() {
        FormulaStorage storage = FormulaStorage.getInstance();
        this.periods = Periods.getInstance().getPeriodArr();
        this.formulas = storage.getItems();
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
