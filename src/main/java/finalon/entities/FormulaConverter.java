package finalon.entities;

import javafx.util.StringConverter;

import java.util.HashMap;
import java.util.Map;

public class FormulaConverter extends StringConverter<Formula> {
    private Map<String, Formula> formulaMap = new HashMap<String, Formula>();

    @Override
    public String toString(Formula formula) {
        return formula == null ? null : formula.getName();
    }

    // Method to convert a String to a Person-Object
    @Override
    public Formula fromString(String name) {
        return formulaMap.get(name);
    }
}