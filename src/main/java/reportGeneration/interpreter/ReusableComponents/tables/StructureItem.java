package reportGeneration.interpreter.ReusableComponents.tables;

import entities.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import reportGeneration.storage.Periods;

import java.util.ArrayList;
import java.util.Map;

public class StructureItem {
    private String name;
    private ObservableMap<String, Double> structureValues;
    private ObservableMap<String, Double> itemValues;
    private Map<String, Double> totalVals;

    public StructureItem(
            Item item,
            Map<String, Double> totalVals
    ) {
        this.totalVals = totalVals;
        this.itemValues = item.getValues();
        this.name = item.getName();
        this.structureValues = calcStructureValues();
    }

    private ObservableMap<String, Double> calcStructureValues() {
        ArrayList<String> periodsArr = Periods.getPeriodArr();
        structureValues = FXCollections.observableHashMap();
        Double itemVal;
        Double totalVal;
        Double structure;
        for (String col : periodsArr) {
            itemVal = itemValues.get(col);
            totalVal = totalVals.get(col);
            if (itemVal != null && totalVal != null) {
                structure = (itemVal / totalVal) * 100;
                structureValues.put(col, structure);
            }
        }
        return structureValues;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ObservableMap<String, Double> getStructureValues() {
        return this.structureValues;
    }
}
